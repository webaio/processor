package io.weba.eventor.domain.device.features;

public class Browser {
    public String name;
    public String version;

    public Browser(String name, String version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Browser)) return false;

        Browser browser = (Browser) o;

        if (!name.equals(browser.name)) return false;
        return version.equals(browser.version);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + version.hashCode();
        return result;
    }
}
