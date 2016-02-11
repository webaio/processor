package io.weba.eventor.infrastructure.device.detector;

import fiftyone.mobile.detection.Match;
import fiftyone.mobile.detection.Provider;
import io.weba.eventor.domain.device.LogEntryDetector;
import io.weba.eventor.domain.device.features.Features;
import io.weba.eventor.domain.exception.EventorException;
import io.weba.eventor.domain.log.Entry;

import java.io.IOException;

public class Degress51EntryDetector extends LogEntryDetector {
    private Provider provider;

    public Degress51EntryDetector(Provider provider) {
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
        features.osName = match.getValues("PlatformName").toString();
        features.osVendor = match.getValues("PlatformVendor").toString();
        features.osVersion = match.getValues("PlatformVersion").toString();
    }

    private void detectBrowserFeatures(Features features, Match match) throws IOException {
        features.browserName = match.getValues("BrowserName").toString();
        features.browserVendor = match.getValues("BrowserVendor").toString();
        features.browserVersion = match.getValues("BrowserVersion").toString();
    }

    private void detectBrandFeatures(Features features, Match match) throws IOException {
        features.brandName = match.getValues("HardwareName").toString();
        features.brandModel = match.getValues("HardwareModel").toString();
        features.brandVendor = match.getValues("HardwareVendor").toString();
    }

    private void detectBooleanFeatures(Features features, Match match) throws IOException {
        features.isTablet = match.getValues("IsTablet").toBool();
        features.isConsole = match.getValues("IsConsole").toBool();
        features.isMobile = match.getValues("IsMobile").toBool();
        features.isDesktop = !features.isMobile;
        features.isSmartPhone = match.getValues("IsSmartPhone").toBool();
        features.isSmartWatch = match.getValues("IsSmartWatch").toBool();
        features.isSmallScreen = match.getValues("IsSmallScreen").toBool();
        features.isReader = match.getValues("IsEReader").toBool();
        features.isTV = match.getValues("IsTv").toBool();
        features.isCrawler = match.getValues("IsCrawler").toBool();
    }
}
