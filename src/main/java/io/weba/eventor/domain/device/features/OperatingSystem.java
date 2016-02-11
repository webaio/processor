package io.weba.eventor.domain.device.features;

public class OperatingSystem {
    public final String name;
    public final String version;

    public OperatingSystem(String name, String version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperatingSystem)) return false;

        OperatingSystem that = (OperatingSystem) o;

        if (!name.equals(that.name)) return false;
        return version.equals(that.version);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + version.hashCode();
        return result;
    }
}
