package cz.cvut.fit.presentation.controller;

import cz.cvut.fit.service.dto.CountryDTO;
import cz.cvut.fit.service.dto.TeamDTO;
import cz.cvut.fit.service.dto.ToolDTO;
import cz.cvut.fit.data.enums.ToolType;
import cz.cvut.fit.service.CountryService;
import cz.cvut.fit.service.GameService;
import cz.cvut.fit.service.TeamService;
import cz.cvut.fit.service.ToolService;
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
    private final CountryService countryService;
    private final GameService gameService;

    @GetMapping("/teams/{teamId}/tools")
    public String getTools(Model model, @PathVariable Long teamId) {
        List<ToolDTO> tools = toolService.getToolsByTeamId(teamId);
        TeamDTO team = teamService.getTeam(teamId);
        List<CountryDTO> countries = countryService.getAllCountriesFromGame(team.getGameId());
        Boolean gameInProgress = gameService.isGameInProgress(team.getGameId());

        model.addAttribute("tools", tools);
        model.addAttribute("team", team);
        model.addAttribute("countries", countries);
        model.addAttribute("gameInProgress", gameInProgress);
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
        TeamDTO team = teamService.getTeam(teamId);
        List<ToolType> unownedTools = toolService.getUnownedTools(teamId);
        boolean gameInProgress = gameService.isGameInProgress(team.getGameId());

        model.addAttribute("tool", new ToolDTO());
        model.addAttribute("team", team);
        model.addAttribute("unownedTools", unownedTools);
        model.addAttribute("gameInProgress", gameInProgress);
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
        return "redirect:/teams/" + teamId;
    }


}
