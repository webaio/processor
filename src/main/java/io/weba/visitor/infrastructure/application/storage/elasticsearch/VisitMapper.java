package io.weba.visitor.infrastructure.application.storage.elasticsearch;

import io.weba.visitor.application.serializer.Serializer;
import io.weba.visitor.application.storage.Mapper;
import io.weba.visitor.domain.visit.Visit;

import java.util.HashMap;
import java.util.Map;

public class VisitMapper implements Mapper<Visit> {
    private final Serializer serializer;

    public VisitMapper(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public Map<String, String> mapFields(Visit object) {
        Map<String, String> data = new HashMap<>();
        data.put("object", this.serializer.serialize(object));

        return data;
    }

    @Override
    public Visit reconstituteFromData(Map<String, String> data) {
        return null;
    }

    @Override
    public String[] getFields() {
        return new String[]{"object"};
    }

    @Override
    public String getIncrementField() {
        return null;
    }

    @Override
    public Long getExpiresAt(Visit object) {
        return null;
    }

    @Override
    public String getIdentifier(Visit object) {
        return object.id.toString();
    }
}
