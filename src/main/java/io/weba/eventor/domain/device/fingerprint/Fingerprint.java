package io.weba.eventor.domain.device.fingerprint;

import java.util.Map;

public class Fingerprint {
    public final String fingerprint;
    public final Map<String, Component> components;
    public final int quality;

    public Fingerprint(String fingerprint, Map<String, Component> components) {
        this.fingerprint = fingerprint;
        this.components = components;
        this.quality = this.components.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fingerprint)) return false;

        Fingerprint that = (Fingerprint) o;

        return fingerprint.equals(that.fingerprint);

    }

    @Override
    public int hashCode() {
        return fingerprint.hashCode();
    }

    @Override
    public String toString() {
        return fingerprint;
    }
}
