package emt.labs.labEmt.service;

import emt.labs.labEmt.model.Country;
import emt.labs.labEmt.model.dto.CreateCountryDto;

public interface CountryService {
    Country deleteById(Long id);

    Country createCountry(CreateCountryDto createCountryDto);
}
