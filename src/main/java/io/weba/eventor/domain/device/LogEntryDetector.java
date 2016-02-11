package io.weba.eventor.domain.device;

import io.weba.eventor.domain.device.detector.Detector;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Fingerprinting;
import io.weba.eventor.domain.device.fingerprint.Generator;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.Entry;

public abstract class LogEntryDetector implements Detector {
    protected Generator generator;

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public Device detect(Entry log) throws EventorException {
        Features features = detectFeatures(log);

        return new Device(
                generator.generate(new Fingerprinting(log, features)),
                features
        );
    }

    protected Features detectFeatures(Entry log) throws EventorException {
        Features features = new Features();

        features.availableHeight = log.parameters.getAvailableHeight();
        features.availableWidth = log.parameters.getAvailableWidth();
        features.width = log.parameters.getWidth();
        features.height = log.parameters.getHeight();

        features.isAdBlock = log.parameters.isAdBlock();
        features.isCanvas = log.parameters.isCanvas();
        features.isCookie = log.parameters.isCookie();
        features.isFlash = log.parameters.isFlash();
        features.isJava = log.parameters.isJava();
        features.isJavaScript = log.parameters.isJavaScript();
        features.isPdf = log.parameters.isPdf();
        features.isLocalStorage = log.parameters.isLocalStorage();
        features.isSessionStorage = log.parameters.isSessionStorage();
        features.isQuickTime = log.parameters.isQuickTime();
        features.isRealPlayer = log.parameters.isRealPlayer();
        features.isSilverLight = log.parameters.isSilverLight();
        features.isWindowsMedia = log.parameters.isWindowsMedia();
        features.isTouch = log.parameters.isTouch();

        return features;
    }
}
