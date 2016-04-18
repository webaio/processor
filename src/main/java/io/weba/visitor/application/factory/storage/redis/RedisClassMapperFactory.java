package io.weba.visitor.application.factory.storage.redis;

import io.weba.visitor.application.storage.ClassMapper;
import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.infrastructure.application.serializer.GsonSerializerImpl;
import io.weba.visitor.infrastructure.application.storage.redis.VisitMapper;

class RedisClassMapperFactory {
    public ClassMapper create() throws MapperException {
        ClassMapper classMapper = new ClassMapper();
        classMapper.addMapper(Visit.class.getName(), new VisitMapper(new GsonSerializerImpl()));

        return classMapper;
    }
}
