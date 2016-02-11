package io.weba.eventor.domain.event;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.event.payload.Payload;
import io.weba.eventor.domain.localization.Localization;

import java.util.Objects;

public class Event {
    public final ID id;
    public final Type type;
    public final Payload payload;
    public final Device device;
    public final Localization localization;
    public final Dates dates;

    public Event(ID id, Type type, Payload payload, Device device, Localization localization, Dates dates) {
        this.id = Objects.requireNonNull(id);
        this.type = Objects.requireNonNull(type);
        this.payload = Objects.requireNonNull(payload);
        this.device = Objects.requireNonNull(device);
        this.localization = Objects.requireNonNull(localization);
        this.dates = Objects.requireNonNull(dates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        return id.equals(event.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
