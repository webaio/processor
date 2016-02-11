package io.weba.eventor.infrastructure.device.fingerprint;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Component;
import io.weba.eventor.domain.device.fingerprint.Fingerprint;
import io.weba.eventor.domain.device.fingerprint.Fingerprinting;
import io.weba.eventor.domain.device.fingerprint.Generator;
import io.weba.eventor.domain.log.HttpLog;

import java.util.TreeMap;

public class ComponentsGenerator implements Generator {
    public Fingerprint generate(Fingerprinting fingerprinting) {
        HttpLog httpLog = fingerprinting.httpLog;
        Features features = fingerprinting.features;
        TreeMap<String, Component> components = new TreeMap<String, Component>();

        components.put("user-agent", new Component<String>(getUserAgent(httpLog)));
        components.put("ip", new Component<String>(httpLog.request.IP));
        components.put("features", new Component<Integer>(features.hashCode()));

        HashFunction hashFunction = Hashing.sha256();
        HashCode hashCode = hashFunction.newHasher()
                .putBytes(getUserAgent(httpLog).getBytes())
                .putBytes(httpLog.request.IP.getBytes())
                .putInt(features.hashCode())
                .hash();

        return new Fingerprint(hashCode.toString(), components);
    }

    private String getUserAgent(HttpLog httpLog) {
        String userAgent = "";
        if (httpLog.request.headers.bag.containsKey("user-agent")) {
            userAgent = httpLog.request.headers.bag.get("user-agent").value;
        }
        return userAgent;
    }
}
