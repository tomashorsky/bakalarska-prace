package com.example.app.controller;

import com.example.app.service.ActionService;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ActionController {
    private final ActionService actionService;

    @GetMapping("/teams/{teamId}/actions")
    public String getActions(@PathVariable Long teamId, Model model) {
        // TODO: Implement action execution logic
        return "actions/list";
    }

    @GetMapping("/teams/{teamId}/actions/execute")
    public String executeAction(@PathVariable Long teamId, @RequestParam String actionName, Model model) {
        // TODO: Implement action execution logic
        return "redirect:/teams/" + teamId;
    }
}
