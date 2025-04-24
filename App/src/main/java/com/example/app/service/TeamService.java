package com.example.app.service;

import com.example.app.dto.TeamDTO;
import com.example.app.entity.Team;
import com.example.app.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    // get all game teams
    public List<TeamDTO> getAllTeamsFromGame(Long gameId) {
        return teamRepository.findAllByGameId(gameId).stream().map(this::teamToDTO).toList();
    }

    // get team by id
    public TeamDTO getTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        return teamToDTO(team);
    }


    private TeamDTO teamToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setColor(team.getColor());
        teamDTO.setColorHex(team.getColorHex());
        teamDTO.setManipulation(team.getManipulation());
        teamDTO.setGameId(team.getGame().getId());
        return teamDTO;
    }
}
