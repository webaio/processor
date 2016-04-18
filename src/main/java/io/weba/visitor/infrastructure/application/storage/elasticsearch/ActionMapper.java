package io.weba.visitor.infrastructure.application.storage.elasticsearch;

import io.weba.visitor.application.serializer.Serializer;
import io.weba.visitor.application.storage.Mapper;
import io.weba.visitor.domain.action.Action;

import java.util.HashMap;
import java.util.Map;

public class ActionMapper implements Mapper<Action> {
    private final Serializer serializer;

    public ActionMapper(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public Map<String, String> mapFields(Action object) {
        Map<String, String> data = new HashMap<>();
        data.put("object", this.serializer.serialize(object));

        return data;
    }

    @Override
    public Action reconstituteFromData(Map<String, String> data) {
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
    public Long getExpiresAt(Action object) {
        return null;
    }

    @Override
    public String getIdentifier(Action object) {
        return object.id.toString();
    }
}
