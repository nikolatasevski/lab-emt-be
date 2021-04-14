package emt.labs.labEmt.web;

import emt.labs.labEmt.model.Country;
import emt.labs.labEmt.model.dto.CreateCountryDto;
import emt.labs.labEmt.service.CountryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public Country createCountry(@RequestBody CreateCountryDto createCountryDto){
        return this.countryService.createCountry(createCountryDto);
    }

    @DeleteMapping("/{id}")
    public Country deleteCountry(@PathVariable Long id){
        return this.countryService.deleteById(id);
    }
}
