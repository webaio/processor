/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.event;

public enum Type {
    PAGE_VIEW("pageView");

    private String type;

    private Type(String type) {
        this.type = type;
    }

    public static Type recognizeType(String type) {
        for (Type allowedType : Type.values()) {
            if (allowedType.type.equals(type)) {
                return allowedType;
            }
        }

        return Type.PAGE_VIEW;
    }

    public String toString() {
        return this.type;
    }
}
