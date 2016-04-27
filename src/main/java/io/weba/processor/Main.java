package io.weba.processor;

import io.weba.processor.flink.EventCollectorSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        ArgumentParser argumentParser = new ArgumentParser(args);
        Configuration configuration = argumentParser.configuration;

        StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", configuration.kafkaHost + ":" + configuration.kafkaPort);

        streamExecutionEnvironment
                .addSource(new FlinkKafkaConsumer09<>(configuration.kafkaTopic, new SimpleStringSchema(), properties))
                .addSink(new EventCollectorSink<String>(
                        configuration.memsqlHost,
                        configuration.memsqlPort,
                        configuration.memsqlDatabase,
                        configuration.memsqlUser,
                        configuration.memsqlPassword,
                        configuration.deviceDetectorDabasePath
                ));

        streamExecutionEnvironment.execute("Execute event collector (kafka -> memsql)");
    }
}
