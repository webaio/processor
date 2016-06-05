package io.weba.processor.flink;

import io.weba.collector.application.collector.Collector;
import io.weba.eventor.application.eventor.ApplicationEventor;
import io.weba.eventor.application.eventor.ApplicationEventorFactory;
import io.weba.collector.application.factory.memsql.CollectorFactory;
import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.exception.EventorException;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

public class EventCollectorSink<T> extends RichSinkFunction<T> {
    private final String host;
    private final Integer port;
    private final String databaseName;
    private final String user;
    private final String password;
    private final String deviceDetectorDbPath;
    private ApplicationEventor applicationEventor;
    private Collector resolver;

    public EventCollectorSink(String host, Integer port, String databaseName, String user, String password, String deviceDetectorDbPath) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
        this.deviceDetectorDbPath = deviceDetectorDbPath;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        this.applicationEventor = (ApplicationEventor) new ApplicationEventorFactory().create(this.deviceDetectorDbPath);
        this.resolver = new CollectorFactory().create(this.host, this.port, this.databaseName, this.user, this.password);
    }

    @Override
    public void invoke(T value) throws Exception {
        Event event = null;

        try {
            event = applicationEventor.exploitFromLog((String) value);
        } catch (EventorException e) {
            e.printStackTrace();
        }

        if (event != null) {
            this.resolver.collect(event);
        }
    }
}
