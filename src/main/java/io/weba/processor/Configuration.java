package io.weba.processor;

import java.util.Map;

public class Configuration {
    public final String kafkaHost;
    public final Integer kafkaPort;
    public final String kafkaTopic;
    public final String memsqlHost;
    public final Integer memsqlPort;
    public final String memsqlDatabase;
    public final String memsqlUser;
    public final String memsqlPassword;
    public final String deviceDetectorDabasePath;

    public Configuration(Map<String, String> configuration) {
        this.kafkaHost = configuration.getOrDefault("kafkaHost", "kafka");
        this.kafkaPort = Integer.valueOf(configuration.getOrDefault("kafkaPort", "9092"));
        this.kafkaTopic = configuration.getOrDefault("kafkaTopic", "eventor_webserver");
        this.memsqlHost = configuration.getOrDefault("memsqlHost", "memsql");
        this.memsqlPort = Integer.valueOf(configuration.getOrDefault("memsqlPort", "3306"));
        this.memsqlDatabase = configuration.getOrDefault("memsqlDatabase", "webaio");
        this.memsqlUser = configuration.getOrDefault("memsqlUser", "root");
        this.memsqlPassword = configuration.getOrDefault("memsqlPassword", "");
        this.deviceDetectorDabasePath = configuration.getOrDefault("deviceDetectorDabasePath", "/51degrees.dat");
    }
}
