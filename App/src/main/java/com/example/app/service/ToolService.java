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
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    // get all game tools
    public List<ToolDTO> getToolsByTeamId(Long teamId) {
        return toolRepository.findAllByTeamIdOrderByType(teamId).stream().map(this::toolToDTO).toList();
    }

    /**
     * Adds a new tool to a team.
     *
     * @param teamId   the ID of the team
     * @param toolType the type of tool to add
     * @return a message indicating the result of the operation
     */
    public String addTool(Long teamId, String toolType) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        Tool tool = new Tool();
        tool.setType(ToolType.valueOf(toolType));
        tool.setTeam(team);
        tool.setCanBeUsedAt(LocalDateTime.now());
        toolRepository.save(tool);
        return MessageFormat.format(gameMessages.getToolAdded(), tool.getType().getLabel());
    }
    /**
     * Executes a tool for a given team and country, using ToolResolver.
     *
     * @param teamId    the ID of the team
     * @param toolId    the ID of the tool
     * @param countryId the ID of the country
     * @return a message indicating the result of the tool execution
     */
    public String executeTool(Long teamId, Long toolId, Long countryId) {
        Tool tool = toolRepository.findById(toolId).orElseThrow(() -> new IllegalArgumentException("Tool not found"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new IllegalArgumentException("Country not found"));

        int result = toolResolver.resolveExecution(tool, team, country);
        return  MessageFormat.format(gameMessages.getToolSuccess(), country.getName(), result);
    }

    /**
     * Gets the list of ToolTypes that are not owned by a team.
     *
     * @param teamId the ID of the team
     * @return list of unowned ToolTypes
     */
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
                .canBeUsedAtInMillis(tool.getCanBeUsedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .canBeUsedAt(tool.getCanBeUsedAt())
                .build();
    }
}
