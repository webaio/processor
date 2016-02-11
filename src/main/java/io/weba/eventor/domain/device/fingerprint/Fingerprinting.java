package io.weba.eventor.domain.device.fingerprint;

import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.log.HttpLog;

public class Fingerprinting {
    public final HttpLog httpLog;
    public final Features features;

    public Fingerprinting(HttpLog httpLog, Features features) {
        this.httpLog = httpLog;
        this.features = features;
    }
}
