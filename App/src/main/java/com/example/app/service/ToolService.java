package com.example.app.service;

import com.example.app.dto.ToolDTO;
import com.example.app.entity.Tool;
import com.example.app.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;

    public List<ToolDTO> getToolsByTeamId(Long teamId) {
        return toolRepository.findAllByTeamId(teamId).stream().map(this::toolToDTO).toList();
    }

    private ToolDTO toolToDTO(Tool tool) {
        return ToolDTO.builder()
                .id(tool.getId())
                .type(tool.getType())
                .level(tool.getLevel())
                .teamId(tool.getTeam().getId())
                .build();
    }
}
