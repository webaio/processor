package io.weba.eventor.domain.device.features;

public class Types {
    public boolean isMobile = false;
    public boolean isDesktop = false;
    public boolean isBot = false;
    public boolean isTouch = false;
    public boolean isCarBrowser = false;
    public boolean isConsole = false;
    public boolean isPhablet = false;
    public boolean isTablet = false;
    public boolean isTV = false;
    public boolean isPhone = false;
    public boolean isSmartPhone = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Types)) return false;

        Types types = (Types) o;

        if (isMobile != types.isMobile) return false;
        if (isDesktop != types.isDesktop) return false;
        if (isBot != types.isBot) return false;
        if (isTouch != types.isTouch) return false;
        if (isCarBrowser != types.isCarBrowser) return false;
        if (isConsole != types.isConsole) return false;
        if (isPhablet != types.isPhablet) return false;
        if (isTablet != types.isTablet) return false;
        if (isTV != types.isTV) return false;
        if (isPhone != types.isPhone) return false;
        return isSmartPhone == types.isSmartPhone;

    }

    @Override
    public int hashCode() {
        int result = (isMobile ? 1 : 0);
        result = 31 * result + (isDesktop ? 1 : 0);
        result = 31 * result + (isBot ? 1 : 0);
        result = 31 * result + (isTouch ? 1 : 0);
        result = 31 * result + (isCarBrowser ? 1 : 0);
        result = 31 * result + (isConsole ? 1 : 0);
        result = 31 * result + (isPhablet ? 1 : 0);
        result = 31 * result + (isTablet ? 1 : 0);
        result = 31 * result + (isTV ? 1 : 0);
        result = 31 * result + (isPhone ? 1 : 0);
        result = 31 * result + (isSmartPhone ? 1 : 0);
        return result;
    }
}
