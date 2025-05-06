package com.example.app.service;

import com.example.app.dto.CountryDTO;
import com.example.app.dto.OpinionSupportDTO;
import com.example.app.entity.Country;
import com.example.app.entity.OpinionSupport;
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

    // get all game countries
    public List<CountryDTO> getAllCountriesDetailFromGame(Long gameId) {
        return countryRepository.findAllByGameId(gameId).stream().map(this::countryDetailToDTO).toList();
    }

    // get country by id
    public CountryDTO getCountry(Long id) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Country not found"));
        return countryToDTO(country);
    }

    // get country by id without opinion support
    private CountryDTO countryToDTO(Country country) {
        return countryToDTO(country, false);
    }

    // get country by id without opinion support
    private CountryDTO countryDetailToDTO(Country country) {
        return countryToDTO(country, true);
    }

    // TODO: is this ok to have OpinionSupport in CountryDTO?
    private CountryDTO countryToDTO(Country country, boolean isDetail) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());
        countryDTO.setMediaLiteracy(country.getMediaLiteracy());
        countryDTO.setTrustInState(country.getTrustInState());
        countryDTO.setEconomy(country.getEconomy());
        countryDTO.setGameId(country.getGame().getId());
        if (isDetail) {
            countryDTO.setOpinionSupports(country.getOpinionSupports().stream().map(this::opinionSupportToDTO).toList());
        }
        return countryDTO;
    }

    private OpinionSupportDTO opinionSupportToDTO(OpinionSupport opinionSupport) {
        OpinionSupportDTO opinionSupportDTO = new OpinionSupportDTO();
        opinionSupportDTO.setId(opinionSupport.getId());
        opinionSupportDTO.setSupportersCount(opinionSupport.getSupportersCount());
        opinionSupportDTO.setOpinion(opinionSupport.getTeam().getOpinion());
        opinionSupportDTO.setTeamName(opinionSupport.getTeam().getName());
        opinionSupportDTO.setTeamColor(opinionSupport.getTeam().getColorHex());
        return opinionSupportDTO;
    }

}
