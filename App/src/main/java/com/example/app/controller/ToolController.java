package com.example.app.controller;

import com.example.app.dto.ToolDTO;
import com.example.app.service.ToolService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ToolController {
    private final ToolService toolService;

    @GetMapping("/teams/{teamId}/tools")
    public String getTools(Model model, @PathVariable Long teamId) {
        List<ToolDTO> tools = toolService.getToolsByTeamId(teamId);

        model.addAttribute("tools", tools);
        return "tools/list";
    }

    @GetMapping("/teams/{teamId}/tools/add")
    public String addTool(Model model, @PathVariable Long teamId) {
        model.addAttribute("tool", new ToolDTO());
        return "tools/add";
    }

    @PostMapping("/teams/{teamId}/tools")
    public String addTool(@PathVariable Long teamId, @RequestParam String toolName, Model model) {
        // TODO: Implement
        return "redirect:/tools";
    }
}
