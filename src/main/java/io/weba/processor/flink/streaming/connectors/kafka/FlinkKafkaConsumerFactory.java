/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.streaming.connectors.kafka;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

public class FlinkKafkaConsumerFactory {
    private final ParameterTool parameters;

    public FlinkKafkaConsumerFactory(ParameterTool parameters) {
        this.parameters = parameters;
    }

    public FlinkKafkaConsumer09<String> createConsumer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", parameters.getRequired("kafka-bootstrap-servers"));
        properties.setProperty("group.id", parameters.getRequired("kafka-group-id"));

        return new FlinkKafkaConsumer09<String>(
                parameters.getRequired("kafka-topic"),
                new SimpleStringSchema(),
                properties
        );
    }
}
