package io.weba.eventor.domain.localization;

public class Localization {
    public final String country;
    public final String region;
    public final String city;
    public final String postalCode;
    public final String continent;
    public final String latitude;
    public final String longitude;

    public Localization(String country, String region, String city, String postalCode, String continent, String latitude, String longitude) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.postalCode = postalCode;
        this.continent = continent;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
