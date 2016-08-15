/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.parameters;

public enum Output {
    REMOTE_ADDR("remoteAddr"),
    TRACKER_ID("trackerId"),
    URL_PARTS("urlParts"),
    URL_FULL("urlFull"),
    USER_AGENT("userAgent"),
    USER_LANGUAGE("userLanguage"),
    DOCUMENT_ENCODING("documentEncoding"),
    DOCUMENT_TITLE("documentTitle"),
    COLOR_DEPTH("colorDepth"),
    AVAILABLE_WIDTH("availableWidth"),
    AVAILABLE_HEIGHT("availableHeight"),
    WIDTH("width"),
    HEIGHT("height");

    public final String name;

    private Output(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
