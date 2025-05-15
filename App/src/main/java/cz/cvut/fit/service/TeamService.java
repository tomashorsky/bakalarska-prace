package cz.cvut.fit.service;

import cz.cvut.fit.service.dto.TeamDTO;
import cz.cvut.fit.data.entity.Team;
import cz.cvut.fit.data.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    // get all game teams
    public List<TeamDTO> getAllTeamsFromGame(Long gameId) {
        return teamRepository.findAllByGameIdOrderById(gameId).stream().map(this::teamToDTO).toList();
    }

    // get team by id
    public TeamDTO getTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        return teamToDTO(team);
    }

    private TeamDTO teamToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setColorHex(team.getColorHex());
        teamDTO.setOpinion(team.getOpinion());
        teamDTO.setGameId(team.getGame().getId());
        teamDTO.setNumberOfTools(team.getTools().size());
        return teamDTO;
    }
}
