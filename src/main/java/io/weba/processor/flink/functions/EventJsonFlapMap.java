/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.processor.flink.functions;

import com.google.gson.Gson;
import io.weba.eventor.accesslog.AccessLogEventorImpl;
import io.weba.eventor.accesslog.matcher.MatcherFactory;
import io.weba.eventor.core.event.Event;
import io.weba.eventor.core.eventor.Eventor;
import io.weba.processor.flink.event.gson.factory.EventFactory;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventJsonFlapMap extends RichFlatMapFunction<String, Tuple2<String, String>> {
    private transient static Logger logger = LoggerFactory.getLogger(EventJsonFlapMap.class);
    private transient Eventor eventor;
    private transient Gson gson;

    @Override
    public void open(Configuration parameters) throws Exception {
        eventor = new AccessLogEventorImpl(new MatcherFactory().create());
        gson = new EventFactory().create();
        logger = LoggerFactory.getLogger(EventJsonFlapMap.class);
    }

    @Override
    public void flatMap(String value, Collector<Tuple2<String, String>> collector) {
        Event event = null;
        try {
            event = eventor.exploitEvent(value);
        } catch (Exception exception) {
            logger.error(
                    String.format("Cannot exploit event from string: %s, %s.", value, exception.toString()),
                    exception
            );
        }

        if (event != null) {
            collector.collect(new Tuple2<>(event.id.toString(), gson.toJson(event)));
        }
    }
}
