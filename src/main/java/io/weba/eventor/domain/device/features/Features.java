package io.weba.eventor.domain.device.features;

public class Features {
    public Brand brand = new Brand("", "");
    public Browser browser = new Browser("", "");
    public OperatingSystem operatingSystem = new OperatingSystem("", "");
    public BrowserCapabilities browserCapabilities = new BrowserCapabilities();
    public Resolutions resolutions = new Resolutions();
    public Types types = new Types();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Features)) return false;

        Features features = (Features) o;

        if (!brand.equals(features.brand)) return false;
        if (!browser.equals(features.browser)) return false;
        if (!operatingSystem.equals(features.operatingSystem)) return false;
        if (!browserCapabilities.equals(features.browserCapabilities)) return false;
        if (!resolutions.equals(features.resolutions)) return false;
        return types.equals(features.types);

    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + browser.hashCode();
        result = 31 * result + operatingSystem.hashCode();
        result = 31 * result + browserCapabilities.hashCode();
        result = 31 * result + resolutions.hashCode();
        result = 31 * result + types.hashCode();
        return result;
    }
}
