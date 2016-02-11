package io.weba.eventor.domain.http.parameters;

public interface Bag {
    String getTrackerId();

    int getEventType();

    String getUrl();

    int getWidth();

    int getHeight();

    int getAvailableWidth();

    int getAvailableHeight();

    boolean isTouch();

    boolean isCookie();

    boolean isJavaScript();

    boolean isFlash();

    boolean isPdf();

    boolean isJava();

    boolean isQuickTime();

    boolean isRealPlayer();

    boolean isSilverLight();

    boolean isWindowsMedia();

    boolean isSessionStorage();

    boolean isLocalStorage();

    boolean isCanvas();

    boolean isAdBlock();

    String getRequestDate();
}
