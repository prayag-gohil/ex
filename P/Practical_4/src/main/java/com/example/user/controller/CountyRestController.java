package com.example.user.controller;


import com.example.user.entity.Country;
import com.example.user.repository.CountryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/country")
public class CountyRestController {

    @Autowired
    private CountryRepository countryRepository;

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country country)
    {
        Country saveCountry = countryRepository.save(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCountry);
    }

    @GetMapping("/{countryId}")
    public Optional<Country> showCountry(@PathVariable Integer countryId)
    {
        return countryRepository.findById(countryId);
    }

    @PutMapping("/{countryId}")
    public Country updateCountry(@PathVariable Integer countryId,@RequestBody Country country)
    {
        country.setCountryId(countryId);
        return countryRepository.save(country);
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer countryId)
    {
        countryRepository.deleteById(countryId);
        return ResponseEntity.noContent().build();
    }
}
