package io.weba.eventor.domain.device;

import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;

public class Device {
    public final Fingerprint fingerprint;
    public final Features features;

    public Device(Fingerprint fingerprint, Features features) {
        this.fingerprint = fingerprint;
        this.features = features;
    }
}
