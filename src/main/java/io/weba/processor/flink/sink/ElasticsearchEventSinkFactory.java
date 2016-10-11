/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.sink;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.hadoop.shaded.com.google.common.collect.Maps;
import org.apache.flink.streaming.connectors.elasticsearch2.ElasticsearchSink;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ElasticsearchEventSinkFactory {
    private final ParameterTool parameters;

    public ElasticsearchEventSinkFactory(ParameterTool parameters) {
        this.parameters = parameters;
    }

    public ElasticsearchSink<Tuple2<String, String>> create() throws UnknownHostException {
        return new ElasticsearchSink<Tuple2<String, String>>(
                prepareConfiguration(),
                prepareTransports(),
                new ElasticsearchEventSinkFunction(
                        parameters.getRequired("es-index"),
                        parameters.getRequired("es-type"))
        );
    }

    private List<InetSocketAddress> prepareTransports() throws UnknownHostException {
        List<InetSocketAddress> transports = new ArrayList<>();
        String[] esServers = parameters.getRequired("es-servers").split(Pattern.quote(","));
        for (String esServer : esServers) {
            int colonIndexOf = esServer.indexOf(":");
            if (colonIndexOf != -1) {
                transports.add(new InetSocketAddress(
                        InetAddress.getByName(esServer.substring(0, colonIndexOf)),
                        Integer.parseInt(esServer.substring(colonIndexOf + 1, esServer.length()))
                ));
            }
        }

        return transports;
    }

    private Map<String, String> prepareConfiguration() {
        Map<String, String> config = Maps.newHashMap();
        config.put(ElasticsearchSink.CONFIG_KEY_BULK_FLUSH_MAX_ACTIONS, parameters.get("es-bulk-flush-max-actions", "100"));
        config.put(ElasticsearchSink.CONFIG_KEY_BULK_FLUSH_MAX_SIZE_MB, parameters.get("es-bulk-flush-max-size-mb", "1"));
        config.put(ElasticsearchSink.CONFIG_KEY_BULK_FLUSH_INTERVAL_MS, parameters.get("es-bulk-flush-interval-ms", "5000"));
        config.put("cluster.name", parameters.getRequired("es-cluster-name"));

        return config;
    }
}
