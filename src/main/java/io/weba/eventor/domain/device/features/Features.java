package io.weba.eventor.domain.device.features;

public class Features {
    public String brandName;
    public String brandModel;
    public String brandVendor;
    public String browserName;
    public String browserVersion;
    public String browserVendor;
    public String osName;
    public String osVersion;
    public String osVendor;

    public int width = 0;
    public int height = 0;
    public int availableWidth = 0;
    public int availableHeight = 0;

    public boolean isCookie = false;
    public boolean isJavaScript = false;
    public boolean isFlash = false;
    public boolean isPdf = false;
    public boolean isJava = false;
    public boolean isQuickTime = false;
    public boolean isRealPlayer = false;
    public boolean isSilverLight = false;
    public boolean isWindowsMedia = false;
    public boolean isSessionStorage = false;
    public boolean isLocalStorage = false;
    public boolean isCanvas = false;
    public boolean isAdBlock = false;

    public boolean isMobile = false;
    public boolean isDesktop = false;
    public boolean isCrawler = false;
    public boolean isTouch = false;
    public boolean isConsole = false;
    public boolean isTablet = false;
    public boolean isTV = false;
    public boolean isSmartWatch = false;
    public boolean isSmartPhone = false;
    public boolean isSmallScreen = false;
    public boolean isReader = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Features)) return false;

        Features features = (Features) o;

        if (width != features.width) return false;
        if (height != features.height) return false;
        if (availableWidth != features.availableWidth) return false;
        if (availableHeight != features.availableHeight) return false;
        if (isCookie != features.isCookie) return false;
        if (isJavaScript != features.isJavaScript) return false;
        if (isFlash != features.isFlash) return false;
        if (isPdf != features.isPdf) return false;
        if (isJava != features.isJava) return false;
        if (isQuickTime != features.isQuickTime) return false;
        if (isRealPlayer != features.isRealPlayer) return false;
        if (isSilverLight != features.isSilverLight) return false;
        if (isWindowsMedia != features.isWindowsMedia) return false;
        if (isSessionStorage != features.isSessionStorage) return false;
        if (isLocalStorage != features.isLocalStorage) return false;
        if (isCanvas != features.isCanvas) return false;
        if (isAdBlock != features.isAdBlock) return false;
        if (isMobile != features.isMobile) return false;
        if (isDesktop != features.isDesktop) return false;
        if (isCrawler != features.isCrawler) return false;
        if (isTouch != features.isTouch) return false;
        if (isConsole != features.isConsole) return false;
        if (isTablet != features.isTablet) return false;
        if (isTV != features.isTV) return false;
        if (isSmartWatch != features.isSmartWatch) return false;
        if (isSmartPhone != features.isSmartPhone) return false;
        if (isSmallScreen != features.isSmallScreen) return false;
        if (isReader != features.isReader) return false;
        if (!brandName.equals(features.brandName)) return false;
        if (!brandModel.equals(features.brandModel)) return false;
        if (!brandVendor.equals(features.brandVendor)) return false;
        if (!browserName.equals(features.browserName)) return false;
        if (!browserVersion.equals(features.browserVersion)) return false;
        if (!browserVendor.equals(features.browserVendor)) return false;
        if (!osName.equals(features.osName)) return false;
        if (!osVersion.equals(features.osVersion)) return false;
        return osVendor.equals(features.osVendor);

    }

    @Override
    public int hashCode() {
        int result = brandName.hashCode();
        result = 31 * result + brandModel.hashCode();
        result = 31 * result + brandVendor.hashCode();
        result = 31 * result + browserName.hashCode();
        result = 31 * result + browserVersion.hashCode();
        result = 31 * result + browserVendor.hashCode();
        result = 31 * result + osName.hashCode();
        result = 31 * result + osVersion.hashCode();
        result = 31 * result + osVendor.hashCode();
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + availableWidth;
        result = 31 * result + availableHeight;
        result = 31 * result + (isCookie ? 1 : 0);
        result = 31 * result + (isJavaScript ? 1 : 0);
        result = 31 * result + (isFlash ? 1 : 0);
        result = 31 * result + (isPdf ? 1 : 0);
        result = 31 * result + (isJava ? 1 : 0);
        result = 31 * result + (isQuickTime ? 1 : 0);
        result = 31 * result + (isRealPlayer ? 1 : 0);
        result = 31 * result + (isSilverLight ? 1 : 0);
        result = 31 * result + (isWindowsMedia ? 1 : 0);
        result = 31 * result + (isSessionStorage ? 1 : 0);
        result = 31 * result + (isLocalStorage ? 1 : 0);
        result = 31 * result + (isCanvas ? 1 : 0);
        result = 31 * result + (isAdBlock ? 1 : 0);
        result = 31 * result + (isMobile ? 1 : 0);
        result = 31 * result + (isDesktop ? 1 : 0);
        result = 31 * result + (isCrawler ? 1 : 0);
        result = 31 * result + (isTouch ? 1 : 0);
        result = 31 * result + (isConsole ? 1 : 0);
        result = 31 * result + (isTablet ? 1 : 0);
        result = 31 * result + (isTV ? 1 : 0);
        result = 31 * result + (isSmartWatch ? 1 : 0);
        result = 31 * result + (isSmartPhone ? 1 : 0);
        result = 31 * result + (isSmallScreen ? 1 : 0);
        result = 31 * result + (isReader ? 1 : 0);
        return result;
    }
}
