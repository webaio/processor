package io.weba.eventor.domain.device.features;

public class Resolutions {
    public int width = 0;
    public int height = 0;
    public int availableWidth = 0;
    public int availableHeight = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resolutions)) return false;

        Resolutions that = (Resolutions) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        if (availableWidth != that.availableWidth) return false;
        return availableHeight == that.availableHeight;

    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + availableWidth;
        result = 31 * result + availableHeight;
        return result;
    }
}
