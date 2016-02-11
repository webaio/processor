package io.weba.eventor.domain.event;

import java.util.Objects;

public class URL {
    public final String uri;

    public URL(String uri) {
        this.uri = Objects.requireNonNull(uri);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof URL)) return false;

        URL URL1 = (URL) o;

        return uri.equals(URL1.uri);

    }

    @Override
    public int hashCode() {
        return uri.hashCode();
    }

    @Override
    public String toString() {
        return uri;
    }
}
