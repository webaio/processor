package io.weba.visitor.application.storage;

import io.weba.visitor.application.storage.exception.MapperException;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.infrastructure.application.serializer.GsonSerializerImpl;
import io.weba.visitor.infrastructure.application.storage.elasticsearch.VisitMapper;
import org.junit.Test;

public class ClassMapperTest {
    @Test(expected = MapperException.class)
    public void itThrowsExceptionDuringRegisteringTheSameMapper() throws MapperException {
        ClassMapper classMapper = new ClassMapper();
        VisitMapper visitMapper = new VisitMapper(new GsonSerializerImpl());
        classMapper.addMapper(Visit.class.getName(), visitMapper);
        classMapper.addMapper(Visit.class.getName(), visitMapper);
    }

    @Test(expected = MapperException.class)
    public void itThrowsExceptionDuringFetchingNonExistingMapper() throws MapperException {
        ClassMapper classMapper = new ClassMapper();
        classMapper.findMapper(Visit.class.getName());
    }
}
