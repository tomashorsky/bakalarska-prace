package com.example.app.controller;

import com.example.app.dto.TeamDTO;
import com.example.app.dto.ToolDTO;
import com.example.app.service.TeamService;
import com.example.app.service.ToolService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ToolController {
    private final ToolService toolService;
    private final TeamService teamService;

    @GetMapping("/teams/{teamId}/tools")
    public String getTools(Model model, @PathVariable Long teamId) {
        List<ToolDTO> tools = toolService.getToolsByTeamId(teamId);
        TeamDTO team = teamService.getTeam(teamId);

        model.addAttribute("tools", tools);
        model.addAttribute("team", team);
        return "tools/list";
    }

    @PostMapping("/teams/{teamId}/tools/execute")
    public String executeTool(
            @PathVariable Long teamId,
            @RequestParam Long toolId,
            @RequestParam Long countryId,
            RedirectAttributes redirectAttributes
    ) {
        String message = toolService.executeTool(teamId, toolId, countryId);

        redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/teams/" + teamId;
    }

    @GetMapping("/teams/{teamId}/tools/add")
    public String addTool(Model model, @PathVariable Long teamId) {
        model.addAttribute("tool", new ToolDTO());
        return "tools/add";
    }

    @PostMapping("/teams/{teamId}/tools/add")
    public String addTool(
            @PathVariable Long teamId,
            @RequestParam String toolType,
            RedirectAttributes redirectAttributes
    ) {
        String message = toolService.addTool(teamId, toolType);

        redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/teams/"+teamId+"/tools";
    }


}
