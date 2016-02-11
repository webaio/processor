package io.weba.eventor.domain.http;

public class Header {

    public final String key;
    public final String value;

    public Header(String key, String value) {
        this.key = key.trim().toLowerCase();
        this.value = value.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Header)) return false;

        Header header = (Header) o;

        if (!key.equals(header.key)) return false;
        return value.equals(header.value);

    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return value;
    }
}
