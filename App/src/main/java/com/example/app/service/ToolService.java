package com.example.app.service;

import com.example.app.dto.ToolDTO;
import com.example.app.entity.Country;
import com.example.app.entity.Team;
import com.example.app.entity.Tool;
import com.example.app.enums.ToolType;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.TeamRepository;
import com.example.app.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;
    private final TeamRepository teamRepository;
    private final CountryRepository countryRepository;

    public List<ToolDTO> getToolsByTeamId(Long teamId) {
        return toolRepository.findAllByTeamId(teamId).stream().map(this::toolToDTO).toList();
    }

    public String addTool(Long teamId, String toolType) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        Tool tool = new Tool();
        tool.setType(ToolType.valueOf(toolType));
        tool.setTeam(team);
        toolRepository.save(tool);
        return "Manipulační nástroj " + tool.getType().name() + " byl úspěšně přidán";
    }

    public String executeTool(Long teamId, Long toolId, Long countryId) {
        Tool tool = toolRepository.findById(toolId).orElseThrow(() -> new IllegalArgumentException("Tool not found"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new IllegalArgumentException("Country not found"));

        int result = ToolResolver.resolveToolExecution(tool, team, country);
        return  "Použitím manipulační nástroje na stát " + country.getName() + " jste získali jste <strong>" + result +
                "</strong>  tisíc nových zmanipulovaných.";
    }

    private ToolDTO toolToDTO(Tool tool) {
        return ToolDTO.builder()
                .id(tool.getId())
                .type(tool.getType())
                .teamId(tool.getTeam().getId())
                .canBeUsedAt(tool.getCanBeUsedAt())
                .build();
    }
}
