/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.streaming.api.environment;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironmentFactory;

public class ParametersExecutionEnvironmentFactory implements StreamExecutionEnvironmentFactory{
    private final ParameterTool parameters;

    public ParametersExecutionEnvironmentFactory(ParameterTool parameters) {
        this.parameters = parameters;
    }

    public StreamExecutionEnvironment createExecutionEnvironment() {
        Boolean runLocally = parameters.getBoolean("run-locally", false);

        StreamExecutionEnvironment environment = runLocally
                ? StreamExecutionEnvironment.createLocalEnvironment()
                : StreamExecutionEnvironment.getExecutionEnvironment();

        environment.enableCheckpointing(parameters.getLong("flink-checkpoint-interval", 10000));
        environment.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        environment.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        environment.getCheckpointConfig().setCheckpointTimeout(parameters.getLong("flink-checkpoint-timeout", 60000));

        return environment;
    }
}
