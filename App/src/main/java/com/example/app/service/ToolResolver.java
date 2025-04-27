package com.example.app.service;

import com.example.app.entity.Country;
import com.example.app.entity.Team;
import com.example.app.entity.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToolResolver {
    public static int resolveToolExecution(Tool tool, Team team, Country country) {
        //TODO: Implement
        return 0;
    }
}
