package io.weba.eventor.domain.http.parameters;

import java.util.Map;

import static io.weba.eventor.domain.http.parameters.Identity.*;

public class MapBag implements Bag {
    private Map<String, String> bag;

    public MapBag(Map<String, String> bag) {
        this.bag = bag;
    }

    public String getTrackerId() {
        return bag.getOrDefault(TRACKER_ID.identity, "");
    }

    public int getEventType() {
        return Integer.parseInt(bag.getOrDefault(EVENT.identity, "0"));
    }

    public String getUrl() {
        return bag.getOrDefault(URL.identity, null);
    }

    public int getWidth() {
        return Integer.parseInt(bag.getOrDefault(WIDTH.identity, "0"));
    }

    public int getHeight() {
        return Integer.parseInt(bag.getOrDefault(HEIGHT.identity, "0"));
    }

    public int getAvailableWidth() {
        return Integer.parseInt(bag.getOrDefault(AVAILABLE_WIDTH.identity, "0"));
    }

    public int getAvailableHeight() {
        return Integer.parseInt(bag.getOrDefault(AVAILABLE_HEIGHT.identity, "0"));
    }

    public boolean isTouch() {
        return Integer.parseInt(bag.getOrDefault(IS_TOUCH.identity, "0")) != 0;
    }

    public boolean isCookie() {
        return Integer.parseInt(bag.getOrDefault(IS_COOKIE.identity, "0")) != 0;
    }

    public boolean isJavaScript() {
        return Integer.parseInt(bag.getOrDefault(IS_JAVASCRIPT.identity, "0")) != 0;
    }

    public boolean isFlash() {
        return Integer.parseInt(bag.getOrDefault(IS_FLASH.identity, "0")) != 0;
    }

    public boolean isPdf() {
        return Integer.parseInt(bag.getOrDefault(IS_PDF.identity, "0")) != 0;
    }

    public boolean isJava() {
        return Integer.parseInt(bag.getOrDefault(IS_JAVA.identity, "0")) != 0;
    }

    public boolean isQuickTime() {
        return Integer.parseInt(bag.getOrDefault(IS_QUICKTIME.identity, "0")) != 0;
    }

    public boolean isRealPlayer() {
        return Integer.parseInt(bag.getOrDefault(IS_REALPLAYER.identity, "0")) != 0;
    }

    public boolean isSilverLight() {
        return Integer.parseInt(bag.getOrDefault(IS_SILVERLIGHT.identity, "0")) != 0;
    }

    public boolean isWindowsMedia() {
        return Integer.parseInt(bag.getOrDefault(IS_WINDOWSMEDIA.identity, "0")) != 0;
    }

    public boolean isSessionStorage() {
        return Integer.parseInt(bag.getOrDefault(IS_SESSION_STORAGE.identity, "0")) != 0;
    }

    public boolean isLocalStorage() {
        return Integer.parseInt(bag.getOrDefault(IS_LOCAL_STORAGE.identity, "0")) != 0;
    }

    public boolean isCanvas() {
        return Integer.parseInt(bag.getOrDefault(IS_CANVAS.identity, "0")) != 0;
    }

    public boolean isAdBlock() {
        return Integer.parseInt(bag.getOrDefault(IS_ADBLOCK.identity, "0")) != 0;
    }

    public String getRequestDate() {
        return bag.get(REQUEST_DATE.identity);
    }
}
