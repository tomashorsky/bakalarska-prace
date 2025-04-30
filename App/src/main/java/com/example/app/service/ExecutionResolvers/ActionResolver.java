package com.example.app.service.ExecutionResolvers;

import com.example.app.entity.Action;
import com.example.app.entity.Country;
import com.example.app.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionResolver {

    public static int resolveActionExecution(Action action, Team team, Country country) {
        //TODO: Implement
       return 0;
    }
}
