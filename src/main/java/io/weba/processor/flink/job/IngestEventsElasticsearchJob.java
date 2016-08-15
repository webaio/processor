/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.job;

import io.weba.processor.flink.functions.EventJsonFlapMap;
import io.weba.processor.flink.sink.ElasticsearchEventSinkFactory;
import io.weba.processor.flink.streaming.api.environment.ParametersExecutionEnvironmentFactory;
import io.weba.processor.flink.streaming.connectors.kafka.FlinkKafkaConsumerFactory;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch2.ElasticsearchSink;

public class IngestEventsElasticsearchJob {
    public static void main(String[] args) throws Exception {
        ParameterTool parameters = ParameterTool.fromArgs(args);

        StreamExecutionEnvironment environment = new ParametersExecutionEnvironmentFactory(parameters)
                .createExecutionEnvironment();

        DataStream<String> stream = environment
                .addSource(new FlinkKafkaConsumerFactory(parameters).createConsumer())
                .name("Consume messages from Kafka.")
                .rebalance();

        DataStream<Tuple2<String, String>> events = stream
                .flatMap(new EventJsonFlapMap())
                .name("Run Eventor, serialize event and produce tuple.");

        ElasticsearchSink sink = new ElasticsearchEventSinkFactory(parameters).create();

        events.addSink(sink)
                .name("Push event to Elasticsearch.");

        environment.execute("Ingest events from Kafka and index in Elasticsearch.");
    }
}
