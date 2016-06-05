package io.weba.collector.infrastructure.domain.memsql.eventaggregator;

import io.weba.collector.domain.eventaggregator.*;
import io.weba.collector.domain.session.*;
import io.weba.eventor.domain.event.VisitorIdentity;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

public class EventAggregatorMapperImplTest {

    @Test
    public void testMapForInsert() throws Exception {
        EventAggregatorMapperImpl eventAggregatorMapper = new EventAggregatorMapperImpl();
        Map<Integer, Object> mapped = eventAggregatorMapper.mapForInsert(new EventAggregator(
                new EventAggregatorId(EventAggregatorId.generate()),
                new Session(new SessionId(SessionId.generate()), new Date(), new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9")),
                new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9")
        ));

        assertEquals(mapped.size(), 3);
    }

    @Test
    public void testMapForUpdate() throws Exception {
        EventAggregatorMapperImpl eventAggregatorMapper = new EventAggregatorMapperImpl();
        Map<Integer, Object> mapped = eventAggregatorMapper.mapForUpdate(
                new SessionId(SessionId.generate()),
                new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9")
        );

        assertEquals(mapped.size(), 2);
    }
}
