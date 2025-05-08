package com.example.app.service;

import com.example.app.config.GameMessageProperties;
import com.example.app.dto.ToolDTO;
import com.example.app.entity.Country;
import com.example.app.entity.Team;
import com.example.app.entity.Tool;
import com.example.app.enums.ToolType;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.TeamRepository;
import com.example.app.repository.ToolRepository;
import com.example.app.service.ExecutionResolvers.ToolResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;
    private final TeamRepository teamRepository;
    private final CountryRepository countryRepository;
    private final ToolResolver toolResolver;
    private final GameMessageProperties gameMessages;

    public List<ToolDTO> getToolsByTeamId(Long teamId) {
        return toolRepository.findAllByTeamIdOrderByType(teamId).stream().map(this::toolToDTO).toList();
    }

    public String addTool(Long teamId, String toolType) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        Tool tool = new Tool();
        tool.setType(ToolType.valueOf(toolType));
        tool.setTeam(team);
        toolRepository.save(tool);
        return MessageFormat.format(gameMessages.getToolAdded(), tool.getType().getLabel());
    }

    public String executeTool(Long teamId, Long toolId, Long countryId) {
        Tool tool = toolRepository.findById(toolId).orElseThrow(() -> new IllegalArgumentException("Tool not found"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new IllegalArgumentException("Country not found"));

        int result = toolResolver.resolveExecution(tool, team, country);
        return  MessageFormat.format(gameMessages.getToolSuccess(), country.getName(), result);
    }

    public List<ToolType> getUnownedTools(Long teamId) {
        List<ToolType> ownedTools = toolRepository.findAllByTeamIdOrderByType(teamId).stream()
                .map(Tool::getType)
                .toList();
        List<ToolType> allTools = new ArrayList<>(List.of(ToolType.values()));
        allTools.removeAll(ownedTools);
        return allTools;
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
