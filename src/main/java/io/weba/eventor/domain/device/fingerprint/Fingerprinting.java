package io.weba.eventor.domain.device.fingerprint;

import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.log.Entry;

public class Fingerprinting {
    public final Entry entry;
    public final Features features;

    public Fingerprinting(Entry entry, Features features) {
        this.entry = entry;
        this.features = features;
    }
}
