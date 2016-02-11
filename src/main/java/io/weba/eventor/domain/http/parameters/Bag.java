package io.weba.eventor.domain.http.parameters;

public interface Bag {
    public String getTrackerId();

    public int getEventType();

    public String getUrl();

    public int getWidth();

    public int getHeight();

    public int getAvailableWidth();

    public int getAvailableHeight();

    public boolean isTouch();

    public boolean isCookie();

    public boolean isJavaScript();

    public boolean isFlash();

    public boolean isPdf();

    public boolean isJava();

    public boolean isQuickTime();

    public boolean isRealPlayer();

    public boolean isSilverLight();

    public boolean isWindowsMedia();

    public boolean isSessionStorage();

    public boolean isLocalStorage();

    public boolean isCanvas();

    public boolean isAdBlock();

    public String getRequestDate();
}
