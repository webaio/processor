package io.weba.eventor.infrastructure.event.enrichment;

import io.weba.eventor.domain.device.detector.Detector;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public class DeviceEnrichment implements Enrichment {
    private Detector detector;

    public DeviceEnrichment(Detector detector) {
        this.detector = detector;
    }

    public void enrich(HttpContext httpContext) throws EventorException {
        httpContext.eventBuilder.device = this.detector.detect(httpContext.entry);
    }
}
