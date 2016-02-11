package io.weba.eventor.domain.event;

import io.weba.eventor.domain.device.Device;
import io.weba.eventor.domain.event.payload.Payload;
import io.weba.eventor.domain.localization.Localization;

public class EventBuilder {
    public Id id;
    public Type type = Type.PAGE_VIEW;
    public Payload payload;
    public Device device;
    public Localization localization;
    public Dates dates;

    public Event build() {
        return new Event(id, type, payload, device, localization, dates);
    }

    public void clean() {
        id = null;
        type = Type.PAGE_VIEW;
        payload = null;
        device = null;
        localization = null;
        dates = null;
    }

}
