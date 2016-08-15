/*
 * This file is part of the Weba.IO package.
 *
 * Copyright (c) 2016 Damian Zientalak, Marcin Nitschke, Michał Sikora
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package io.weba.eventor.core.enriched.device.fingerprint;

public class Fingerprint {
    public final String fingerprint;
    public final int quality;

    public Fingerprint(String fingerprint, int quality) {
        this.fingerprint = fingerprint;
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fingerprint)) return false;

        Fingerprint that = (Fingerprint) o;

        return fingerprint.equals(that.fingerprint);

    }

    @Override
    public int hashCode() {
        return fingerprint.hashCode();
    }

    @Override
    public String toString() {
        return fingerprint;
    }
}
