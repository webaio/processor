package io.weba.eventor.domain.device.fingerprint;

public interface Generator {
    Fingerprint generate(Fingerprinting fingerprinting);
}
