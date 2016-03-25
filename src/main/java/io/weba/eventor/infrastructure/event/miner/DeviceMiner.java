package io.weba.eventor.infrastructure.event.miner;

import io.weba.eventor.domain.device.detector.Detector;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.infrastructure.event.mine.HttpContext;

public class DeviceMiner implements Miner {
    private Detector detector;

    public DeviceMiner(Detector detector) {
        this.detector = detector;
    }

    public void exploit(HttpContext httpContext) throws EventorException {
        httpContext.eventBuilder.device = this.detector.detect(httpContext.entry);
    }
}
