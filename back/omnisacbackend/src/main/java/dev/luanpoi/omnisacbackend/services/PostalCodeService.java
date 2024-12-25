package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturn;

public interface PostalCodeService {
    ViaCEPReturn validatePostalCode(String postalCode) throws Exception;
}
