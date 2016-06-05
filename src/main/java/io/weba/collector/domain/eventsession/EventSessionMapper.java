package io.weba.collector.domain.eventsession;

import java.util.Map;

public interface EventSessionMapper {
    Map<Integer, Object> mapForInsert(EventSession eventSession);
}
