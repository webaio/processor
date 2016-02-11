package io.weba.eventor.infrastructure.device.fingerprint;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Component;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;
import io.weba.eventor.domain.device.fingerprint.Fingerprinting;
import io.weba.eventor.domain.device.fingerprint.Generator;
import io.weba.eventor.domain.log.Entry;

import java.util.TreeMap;

public class ComponentsGenerator implements Generator {
    public Fingerprint generate(Fingerprinting fingerprinting) {
        Entry entry = fingerprinting.entry;
        Features features = fingerprinting.features;
        String userAgent = entry.request.headers.getUserAgent();

        return new Fingerprint(
                prepareFingerprintHash(entry, features, userAgent),
                prepareComponents(entry, features, userAgent)
        );
    }

    private String prepareFingerprintHash(Entry entry, Features features, String userAgent) {
        HashFunction hashFunction = Hashing.sha256();
        return hashFunction.newHasher()
                .putBytes(userAgent.getBytes())
                .putBytes(entry.request.IP.getBytes())
                .putInt(features.hashCode())
                .hash()
                .toString();
    }

    private TreeMap<String, Component> prepareComponents(Entry entry, Features features, String userAgent) {
        TreeMap<String, Component> components = new TreeMap<String, Component>();
        components.put("user-agent", new Component<String>(userAgent));
        components.put("ip", new Component<String>(entry.request.IP));
        components.put("features", new Component<Integer>(features.hashCode()));

        return components;
    }
}
