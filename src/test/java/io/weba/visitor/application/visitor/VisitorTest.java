package io.weba.visitor.application.visitor;

import io.weba.eventor.application.eventor.ApplicationEventor;
import io.weba.eventor.application.eventor.ApplicationEventorFactory;
import io.weba.eventor.domain.event.Event;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.visitor.application.factory.visitor.VisitorFactory;
import io.weba.visitor.application.factory.visitor.VisitorFactoryException;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class VisitorTest {
    @Test
    public void itCreatesVisitBasedOnEvent() throws IOException, EventorException, VisitorFactoryException, VisitorException {
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("event.json")).useDelimiter("\\A");
        String eventJson = scanner.hasNext() ? scanner.next() : "";

        ApplicationEventor applicationEventor = (ApplicationEventor) new ApplicationEventorFactory().create();
        Event event = applicationEventor.exploitFromLog(eventJson);
        event.dates.serverDate.setTime(new Date().getTime());
        event.dates.browserDate.setTime(new Date().getTime() - 10);

        String elasticsearchTestHost = System.getenv("ELASTICSEARCH_TEST_HOST");
        Integer elasticsearchTestPort = Integer.valueOf(System.getenv("ELASTICSEARCH_TEST_PORT"));
        String redisTestHost = System.getenv("REDIS_TEST_HOST");
        Integer redisTestPort = Integer.valueOf(System.getenv("REDIS_TEST_PORT"));

        Visitor visitor = new VisitorFactory().create(
                redisTestHost,
                redisTestPort,
                elasticsearchTestHost,
                elasticsearchTestPort
        );

        visitor.resolveVisit(event);
        visitor.resolveVisit(event);
    }
}
