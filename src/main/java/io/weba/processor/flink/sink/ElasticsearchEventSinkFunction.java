/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.sink;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.elasticsearch2.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch2.RequestIndexer;
import org.elasticsearch.action.WriteConsistencyLevel;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

public class ElasticsearchEventSinkFunction implements ElasticsearchSinkFunction<Tuple2<String, String>> {
    private String index;
    private String type;

    public ElasticsearchEventSinkFunction(String index, String type) {
        this.index = index;
        this.type = type;
    }

    public IndexRequest createIndexRequest(Tuple2<String, String> tuple) {
        return Requests.indexRequest()
                .consistencyLevel(WriteConsistencyLevel.QUORUM)
                .index(index)
                .type(type)
                .id(tuple.f0)
                .source(tuple.f1);
    }

    @Override
    public void process(Tuple2<String, String> tuple, RuntimeContext ctx, RequestIndexer indexer) {
        indexer.add(createIndexRequest(tuple));
    }
}
