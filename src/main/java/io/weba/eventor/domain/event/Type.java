package io.weba.eventor.domain.event;

public enum Type {

    PAGE_VIEW("page_view");

    private String type;

    public String toString() {
        return this.type;
    }

    private Type(String type) {
        this.type = type;
    }

    public static Type recognizeType(int type) {
        for (Type allowedType : Type.values()) {
            if (allowedType.ordinal() == type) {
                return allowedType;
            }
        }

        return Type.PAGE_VIEW;
    }
}
