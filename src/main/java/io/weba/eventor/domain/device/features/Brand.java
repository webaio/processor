package io.weba.eventor.domain.device.features;

public class Brand {
    public final String name;
    public final String model;

    public Brand(String name, String model) {
        this.name = name;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand)) return false;

        Brand brand = (Brand) o;

        if (!name.equals(brand.name)) return false;
        return model.equals(brand.model);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }
}
