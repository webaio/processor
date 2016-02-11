package io.weba.eventor.domain.device.features;

public class BrowserCapabilities {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrowserCapabilities)) return false;

        BrowserCapabilities that = (BrowserCapabilities) o;

        if (isCookie != that.isCookie) return false;
        if (isJavaScript != that.isJavaScript) return false;
        if (isFlash != that.isFlash) return false;
        if (isPdf != that.isPdf) return false;
        if (isJava != that.isJava) return false;
        if (isQuickTime != that.isQuickTime) return false;
        if (isRealPlayer != that.isRealPlayer) return false;
        if (isSilverLight != that.isSilverLight) return false;
        if (isWindowsMedia != that.isWindowsMedia) return false;
        if (isSessionStorage != that.isSessionStorage) return false;
        if (isLocalStorage != that.isLocalStorage) return false;
        if (isCanvas != that.isCanvas) return false;
        return isAdBlock == that.isAdBlock;

    }

    @Override
    public int hashCode() {
        int result = (isCookie ? 1 : 0);
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
        return result;
    }
}
