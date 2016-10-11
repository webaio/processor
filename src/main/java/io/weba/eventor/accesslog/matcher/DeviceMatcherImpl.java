/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.matcher;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import io.weba.eventor.accesslog.AccessLog;
import io.weba.eventor.accesslog.device.features.Recognizer;
import io.weba.eventor.accesslog.parameters.Output;
import io.weba.eventor.core.enriched.device.Device;
import io.weba.eventor.core.enriched.device.fingerprint.Fingerprint;
import io.weba.eventor.core.exception.DropEventException;
import io.weba.eventor.core.exception.EventorException;

import java.util.Map;
import java.util.TreeMap;

public class DeviceMatcherImpl implements Matcher {
    private final Recognizer recognizer;

    public DeviceMatcherImpl(Recognizer recognizer) {
        this.recognizer = recognizer;
    }

    @Override
    public void match(AccessLog accessLog) throws EventorException {
        Map<String, Object> features = matchDeviceFeatures(accessLog);

        accessLog.builder.enriched.put("device", new Device(
                computeFingerprint(features, accessLog),
                features
        ));
    }

    private Fingerprint computeFingerprint(Map<String, Object> features, AccessLog accessLog) {
        Map<String, Object> featuresToFingerprinting = new TreeMap<>(features);
        featuresToFingerprinting.remove(Output.AVAILABLE_WIDTH.name);
        featuresToFingerprinting.remove(Output.AVAILABLE_HEIGHT.name);

        HashFunction hashFunction = Hashing.sha256();

        String fingerprint =  hashFunction.newHasher()
                .putBytes(((String)accessLog.requestHeaders.get("user-agent")).getBytes())
                .putInt(featuresToFingerprinting.hashCode())
                .hash()
                .toString();

        return new Fingerprint(fingerprint, featuresToFingerprinting.size() + 1);
    }

    private Map<String, Object> matchDeviceFeatures(AccessLog accessLog) throws DropEventException {
        Map<String, Object> features = new TreeMap<>();
        recognizer.recognize(accessLog, features);

        return features;
    }
}
