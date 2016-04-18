package io.weba.visitor.infrastructure.application.storage.redis;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.localization.Localization;
import io.weba.visitor.application.serializer.Serializer;
import io.weba.visitor.application.storage.Mapper;
import io.weba.visitor.domain.visit.Visit;
import io.weba.visitor.domain.visit.VisitId;
import io.weba.visitor.domain.visit.visitor.Visitor;
import io.weba.visitor.domain.visit.visitor.VisitorId;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VisitMapper implements Mapper<Visit> {
    private final Serializer serializer;

    public VisitMapper(Serializer serializer) {
        this.serializer = serializer;
    }

    public Map<String, String> mapFields(Visit visit) {
        Map<String, String> mappedFields = new HashMap<>();
        mappedFields.put("id", visit.id.toString());
        mappedFields.put("visitorId", visit.visitor.visitorId.toString());
        mappedFields.put("numberOfActions", visit.visitor.numberOfActions.toString());
        mappedFields.put("firstVisitAt", String.valueOf(visit.visitor.firstVisitAt.getTime()));
        mappedFields.put("lastVisitAt", String.valueOf(visit.visitor.lastVisitAt.getTime()));
        mappedFields.put("serverFirstVisitAt", String.valueOf(visit.visitor.serverFirstVisitAt.getTime()));
        mappedFields.put("serverLastVisitAt", String.valueOf(visit.visitor.serverLastVisitAt.getTime()));
        mappedFields.put("sessionEndedAt", String.valueOf(visit.visitor.sessionEndedAt.getTime()));
        mappedFields.put("localization", this.serializer.serialize(visit.localization));
        mappedFields.put("device", this.serializer.serialize(visit.device));

        return mappedFields;
    }

    public Visit reconstituteFromData(Map<String, String> data) {
        return new Visit(
                new VisitId(VisitId.fromString(data.get("id"))),
                Visitor.loadFromStorage(
                        new VisitorId(VisitorId.fromString(data.get("visitorId"))),
                        Integer.valueOf(data.get("numberOfActions")),
                        this.getDate(Long.valueOf(data.get("firstVisitAt"))),
                        this.getDate(Long.valueOf(data.get("lastVisitAt"))),
                        this.getDate(Long.valueOf(data.get("serverFirstVisitAt"))),
                        this.getDate(Long.valueOf(data.get("serverLastVisitAt"))),
                        this.getDate(Long.valueOf(data.get("sessionEndedAt")))
                ),
                (Device) this.serializer.deserialize(data.get("device"), Device.class),
                (Localization) this.serializer.deserialize(data.get("localization"), Localization.class)
        );
    }

    public String[] getFields() {
        return new String[]{"id", "visitorId", "numberOfActions", "firstVisitAt", "lastVisitAt", "serverFirstVisitAt", "serverLastVisitAt", "sessionEndedAt", "localization", "device"};
    }

    public String getIncrementField() {
        return "numberOfActions";
    }

    public Long getExpiresAt(Visit object) {
        return object.visitor.sessionEndedAt.getTime();
    }

    @Override
    public String getIdentifier(Visit object) {
        return object.visitor.visitorId.toString();
    }

    private Date getDate(Long millisecond) {
        return new Date(millisecond);
    }
}
