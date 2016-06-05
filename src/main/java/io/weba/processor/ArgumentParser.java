package io.weba.processor;

import java.util.HashMap;

public class ArgumentParser {
    public final Configuration configuration;

    public ArgumentParser(String[] args) throws Exception {
        configuration = new Configuration(this.parseArguments(args));
    }

    private HashMap<String, String> parseArguments(String[] args) throws Exception {
        HashMap<String, String> configurationItems = new HashMap<>();
        String[] split;

        for (String arg : args) {
            split = arg.split("=");

            if (split.length == 2) {
                configurationItems.put(split[0].replace("--", ""), split[1]);
            } else {
                throw new Exception("Invalid argument " + split[0]);
            }
        }

        return configurationItems;
    }
}
