package emt.labs.labEmt.service.impl;

import emt.labs.labEmt.model.Country;
import emt.labs.labEmt.model.dto.CreateCountryDto;
import emt.labs.labEmt.model.exceptions.CountryNotFoundException;
import emt.labs.labEmt.repository.CountryRepository;
import emt.labs.labEmt.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country deleteById(Long id) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        countryRepository.delete(country);

        return country;
    }

    @Override
    public Country createCountry(CreateCountryDto createCountryDto) {
        Country country = new Country();

        country.setName(createCountryDto.getName());
        country.setContinent(createCountryDto.getContinent());

        countryRepository.save(country);

        return country;
    }
}
