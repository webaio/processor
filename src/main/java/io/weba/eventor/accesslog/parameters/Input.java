/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.accesslog.parameters;

public enum Input {
    EVENT("e"),
    LANGUAGE("l"),
    TRACKER_ID("t"),
    DOCUMENT_ENCODING("de"),
    URL("dl"),
    WIDTH_AND_HEIGHT("sr"),
    AVAILABLE_WIDTH_AND_HEIGHT("sv"),
    COLOR_DEPTH("cd"),
    DEVICE_INFO("di"),
    DOCUMENT_TITLE("dt"),
    VISITOR_IDENTITY("vid"),
    SESSION_IDENTITY("sid"),
    VISITOR_TIMESTAMPS("st");

    public final String name;

    private Input(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
