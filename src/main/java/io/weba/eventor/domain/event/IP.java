package io.weba.eventor.domain.event;

public class IP {
    public final String ip;

    public IP(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IP)) return false;

        IP ip1 = (IP) o;

        return ip.equals(ip1.ip);

    }

    @Override
    public int hashCode() {
        return ip.hashCode();
    }

    @Override
    public String toString() {
        return ip;
    }
}
