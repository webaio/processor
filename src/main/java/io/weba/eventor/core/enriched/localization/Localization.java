/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.enriched.localization;

public class Localization {
    public final String country;
    public final String region;
    public final String city;
    public final String postalCode;
    public final String continent;
    public final Geo geo;

    public Localization(String country, String region, String city, String postalCode, String continent, double latitude, double longitude) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.postalCode = postalCode;
        this.continent = continent;
        this.geo = new Geo(latitude, longitude);
    }

    public final class Geo {
        public final double lat;
        public final double lon;

        public Geo(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    }
}
