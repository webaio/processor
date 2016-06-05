package io.weba.processor;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArgumentParserTest {

    @Test
    public void itInitializeConfiguration() throws Exception {
        String[] arguments = new String[] {"--kafkaHost=kafka-host", "--kafkaPort=9999"};
        ArgumentParser argumentParser = new ArgumentParser(arguments);

        assertEquals(argumentParser.configuration.kafkaHost, "kafka-host");
        assertEquals(argumentParser.configuration.kafkaPort.longValue(), 9999);
    }

    @Test(expected = Exception.class)
    public void itDetectsWrongArguments() throws Exception {
        String[] arguments = new String[] {"wrongArgument"};
        new ArgumentParser(arguments);
    }
}
