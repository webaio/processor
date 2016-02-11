package io.weba.eventor.domain.device;

import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.device.fingerprint.Fingerprinting;
import io.weba.eventor.domain.device.fingerprint.Generator;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.HttpLog;

public abstract class BaseDetector implements Detector {
    protected Generator generator;

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public Device detect(HttpLog log) throws EventorException {
        Features features = detectFeatures(log);

        return new Device(
                generator.generate(new Fingerprinting(log, features)),
                features
        );
    }

    protected Features detectFeatures(HttpLog log) {
        Features features = new Features();

        features.resolutions.availableHeight = log.parameters.getAvailableHeight();
        features.resolutions.availableWidth = log.parameters.getAvailableWidth();
        features.resolutions.width = log.parameters.getWidth();
        features.resolutions.height = log.parameters.getHeight();

        features.browserCapabilities.isAdBlock = log.parameters.isAdBlock();
        features.browserCapabilities.isCanvas = log.parameters.isCanvas();
        features.browserCapabilities.isCookie = log.parameters.isCookie();
        features.browserCapabilities.isFlash = log.parameters.isFlash();
        features.browserCapabilities.isJava = log.parameters.isJava();
        features.browserCapabilities.isJavaScript = log.parameters.isJavaScript();
        features.browserCapabilities.isPdf = log.parameters.isPdf();
        features.browserCapabilities.isLocalStorage = log.parameters.isLocalStorage();
        features.browserCapabilities.isSessionStorage = log.parameters.isSessionStorage();
        features.browserCapabilities.isQuickTime = log.parameters.isQuickTime();
        features.browserCapabilities.isRealPlayer = log.parameters.isRealPlayer();
        features.browserCapabilities.isSilverLight = log.parameters.isSilverLight();
        features.browserCapabilities.isWindowsMedia = log.parameters.isWindowsMedia();
        features.types.isTouch = log.parameters.isTouch();

        return features;
    }
}
