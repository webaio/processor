package io.weba.eventor.domain.http.parameters;

public enum Identity {
    TRACKER_ID("t"),
    EVENT("e"),
    URL("u"),
    WIDTH("w"),
    HEIGHT("h"),
    AVAILABLE_WIDTH("aw"),
    AVAILABLE_HEIGHT("ah"),
    IS_TOUCH("d1"),
    IS_COOKIE("d2"),
    IS_JAVASCRIPT("d3"),
    IS_FLASH("d4"),
    IS_PDF("d5"),
    IS_JAVA("d6"),
    IS_QUICKTIME("d7"),
    IS_REALPLAYER("d8"),
    IS_SILVERLIGHT("d9"),
    IS_WINDOWSMEDIA("d10"),
    IS_SESSION_STORAGE("d11"),
    IS_LOCAL_STORAGE("d12"),
    IS_CANVAS("d13"),
    IS_ADBLOCK("d14"),
    REQUEST_DATE("rd");

    public final String identity;

    private Identity(String identity) {
        this.identity = identity;
    }
}
