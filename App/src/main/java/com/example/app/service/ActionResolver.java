package com.example.app.service;

import com.example.app.entity.Action;
import com.example.app.entity.Country;
import com.example.app.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionResolver {

    public static int resolveAction(Action action, Team team, Country country) {
       return 0;
    }
}
