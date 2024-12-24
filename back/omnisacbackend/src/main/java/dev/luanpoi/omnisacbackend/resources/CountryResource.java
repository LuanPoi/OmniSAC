package dev.luanpoi.omnisacbackend.resources;

import dev.luanpoi.omnisacbackend.dtos.CountryDto;
import dev.luanpoi.omnisacbackend.models.Country;
import dev.luanpoi.omnisacbackend.services.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/countries")
public class CountryResource {
    @Autowired
    CountryService countryService;

    @GetMapping()
    public ResponseEntity<List<CountryDto>> getCountries(){
        ModelMapper mapper = new ModelMapper();
        List<Country> countries = this.countryService.getCountries();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(countries
                        .stream()
                        .map((country -> mapper.map(country, CountryDto.class)))
                        .toList());
    }
}
