package com.example.app.service;

import com.example.app.dto.CountryDTO;
import com.example.app.entity.Country;
import com.example.app.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    // get all game countries
    public List<CountryDTO> getAllCountriesFromGame(Long gameId) {
        return countryRepository.findAllByGameId(gameId).stream().map(this::countryToDTO).toList();
    }


    // get country by id
    public CountryDTO getCountry(Long id) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Country not found"));
        return countryToDTO(country);
    }

    private CountryDTO countryToDTO(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());
        countryDTO.setPolarization(country.getPolarization());
        countryDTO.setTrustInState(country.getTrustInState());
        countryDTO.setEconomy(country.getEconomy());
        countryDTO.setGameId(country.getGame().getId());
        return countryDTO;
    }

}
