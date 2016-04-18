package io.weba.visitor.application.factory.storage.elasticsearch;

import io.weba.visitor.application.storage.ClassMapper;
import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.action.Action;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.infrastructure.application.serializer.GsonSerializerImpl;
import io.weba.visitor.infrastructure.application.storage.elasticsearch.ActionMapper;
import io.weba.visitor.infrastructure.application.storage.elasticsearch.VisitMapper;

class ElasticsearchClassMapperFactory {
    public ClassMapper create() throws MapperException {
        ClassMapper classMapper = new ClassMapper();
        classMapper.addMapper(Visit.class.getName(), new VisitMapper(new GsonSerializerImpl()));
        classMapper.addMapper(Action.class.getName(), new ActionMapper(new GsonSerializerImpl()));

        return classMapper;
    }
}
