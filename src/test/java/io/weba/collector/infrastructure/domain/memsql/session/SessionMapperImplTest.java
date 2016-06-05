package io.weba.collector.infrastructure.domain.memsql.session;

import io.weba.collector.domain.session.*;
import io.weba.eventor.domain.event.VisitorIdentity;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

public class SessionMapperImplTest {

    @Test
    public void testMapForInsert() throws Exception {
        SessionMapperImpl sessionMapper = new SessionMapperImpl();
        Map<Integer, Object> mapped = sessionMapper.mapForInsert(new Session(
                new SessionId(SessionId.generate()),
                new Date(),
                new Date(),
                new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9")
        ));

        assertEquals(mapped.size(), 5);
    }

    @Test
    public void testMapForUpdate() throws Exception {
        SessionMapperImpl sessionMapper = new SessionMapperImpl();
        Map<Integer, Object> mapped = sessionMapper.mapForUpdate(new Date(), new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9"));

        assertEquals(mapped.size(), 5);
    }

    @Test
    public void testMapForSelect() throws Exception {
        SessionMapperImpl sessionMapper = new SessionMapperImpl();
        Map<Integer, Object> mapped = sessionMapper.mapForSelect(new Date(), new VisitorIdentity("8e2d80e3-897d-4bc8-99a8-031b9cc55eb9"));

        assertEquals(mapped.size(), 3);
    }
}
