package cz.cvut.fit.service;

import cz.cvut.fit.service.dto.CountryDTO;
import cz.cvut.fit.service.dto.OpinionSupportDTO;
import cz.cvut.fit.data.entity.Country;
import cz.cvut.fit.data.entity.OpinionSupport;
import cz.cvut.fit.data.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    // get all game countries
    public List<CountryDTO> getAllCountriesFromGame(Long gameId) {
        return countryRepository.findAllByGameIdOrderByName(gameId).stream().map(this::countryToDTO).toList();
    }

    // get all game countries
    public List<CountryDTO> getAllCountriesDetailFromGame(Long gameId) {
        return countryRepository.findAllByGameIdOrderByName(gameId).stream().map(this::countryDetailToDTO).toList();
    }

    // get country by id without opinion support
    private CountryDTO countryToDTO(Country country) {
        return countryToDTO(country, false);
    }

    // get country by id without opinion support
    private CountryDTO countryDetailToDTO(Country country) {
        return countryToDTO(country, true);
    }

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
        opinionSupportDTO.setTeamName(opinionSupport.getTeam().getName());
        opinionSupportDTO.setTeamColor(opinionSupport.getTeam().getColorHex());
        return opinionSupportDTO;
    }

}
