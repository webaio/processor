package io.weba.eventor.domain.device;

import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Component;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;
import org.junit.Test;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class DeviceTest {
    @Test
    public void itContainsFingerprintAndFeatures() {
        Features features = new Features();
        Map<String, Component> components = new TreeMap<String, Component>();
        Fingerprint fingerprint = new Fingerprint(new Date().toString(), components);

        Device device = new Device(fingerprint, features);

        assertSame(fingerprint, device.fingerprint);
        assertSame(features, device.features);
    }
}