package io.weba.eventor.domain.event;

import com.google.common.net.InetAddresses;
import io.weba.eventor.domain.exception.EventorException;

public class IP {
    public final String ip;

    public IP(String ip) throws EventorException {
        if (!InetAddresses.isInetAddress(ip)) {
            throw new EventorException("IP ".concat(ip).concat(" is not valid inet address."));
        }
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
