package io.weba.eventor.infrastructure.device.detector;

import fiftyone.mobile.detection.Match;
import fiftyone.mobile.detection.Provider;
import fiftyone.mobile.detection.entities.Values;
import io.weba.eventor.domain.device.BaseWebTrackerDetector;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.Entry;

import java.io.IOException;

public class Degress51Detector extends BaseWebTrackerDetector {
    private Provider provider;

    public Degress51Detector(Provider provider) {
        this.provider = provider;
    }

    protected Features detectFeatures(Entry log) throws EventorException {
        Features features = super.detectFeatures(log);

        try {
            Match match = this.provider.match(log.request.headers.getUserAgent());

            detectBooleanFeatures(features, match);
            detectBrandFeatures(features, match);
            detectBrowserFeatures(features, match);
            detectOSFeatures(features, match);

            return features;
        } catch (IOException e) {
            throw new EventorException(e.toString());
        }
    }

    private void detectOSFeatures(Features features, Match match) throws IOException {
        features.osName = getStringFeature("PlatformName", match);
        features.osVendor = getStringFeature("PlatformVendor", match);
        features.osVersion = getStringFeature("PlatformVersion", match);
    }

    private void detectBrowserFeatures(Features features, Match match) throws IOException {
        features.browserName = getStringFeature("BrowserName", match);
        features.browserVendor = getStringFeature("BrowserVendor", match);
        features.browserVersion = getStringFeature("BrowserVersion", match);
    }

    private void detectBrandFeatures(Features features, Match match) throws IOException {
        features.brandName = getStringFeature("HardwareName", match);
        features.brandModel = getStringFeature("HardwareModel", match);
        features.brandVendor = getStringFeature("HardwareVendor", match);
    }

    private void detectBooleanFeatures(Features features, Match match) throws IOException {

        features.isTablet = getBooleanFeature("IsTablet", match);
        features.isConsole = getBooleanFeature("IsConsole", match);
        features.isMobile = getBooleanFeature("IsMobile", match);
        features.isDesktop = !features.isMobile;
        features.isSmartPhone = getBooleanFeature("IsSmartPhone", match);
        features.isSmartWatch = getBooleanFeature("IsSmartWatch", match);
        features.isSmallScreen = getBooleanFeature("IsSmallScreen", match);
        features.isReader = getBooleanFeature("IsEReader", match);
        features.isTV = getBooleanFeature("IsTv", match);
        features.isCrawler = getBooleanFeature("IsCrawler", match);
    }

    private boolean getBooleanFeature(String property, Match match) throws IOException {
        Values values = match.getValues(property);
        if (values != null) {
            return values.toBool();
        }

        return false;
    }

    private String getStringFeature(String property, Match match) throws IOException {
        Values values = match.getValues(property);
        if (values != null) {
            return values.toString();
        }

        return "";
    }
}
