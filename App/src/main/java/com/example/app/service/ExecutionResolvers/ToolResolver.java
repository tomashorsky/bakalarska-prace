package com.example.app.service.ExecutionResolvers;

import com.example.app.entity.*;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.OpinionSupportRepository;
import com.example.app.repository.ToolUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ToolResolver  {
    private static final int COOLDOWN = 4;
    private final ToolUsageRepository toolUsageRepository;
    private final OpinionSupportRepository opinionSupportRepository;
    private final CountryRepository countryRepository;

    public int resolveToolExecution(Tool tool, Team team, Country country) {
        tool.setCanBeUsedAt(LocalDateTime.now().plusMinutes(COOLDOWN));
        int impact = calculateImpact(tool, country);

        ToolUsage toolUsage = new ToolUsage();
        toolUsage.setName(tool.getType().getName());
        toolUsage.setImpact(impact);
        toolUsage.setTool(tool);
        toolUsage.setCountry(country);
        toolUsage.setTrustInStateImpact(calculateTrustInStateImpact(toolUsage, country));
        toolUsageRepository.save(toolUsage);


        return toolUsage.getImpact();
    }

    private int calculateImpact(Tool tool, Country country) {
        return switch (tool.getType()) {
            case TROLL_FARM -> calculateTrollFarmImpact(country);
            case DEEPFAKE_MAKER -> calculateDeepfakeMakerImpact(country);
            case FAKE_NEWS_SITE -> calculateFakeNewsSiteImpact(country);
        };
    }


    private int calculateTrollFarmImpact(Country country) {
        return 1;
    }

    private int calculateDeepfakeMakerImpact(Country country) {
        return 2;
    }

    private int calculateFakeNewsSiteImpact(Country country) {
        return 3;
    }

     //TODO: Use abstract class for this?
    public void recordImpact(Country country, ToolUsage operation, Team team) {
        OpinionSupport support =  country.getOpinionSupportByTeamId(team.getId());

        support.setSupportersCount(support.getSupportersCount() + operation.getImpact());
        opinionSupportRepository.save(support);

        country.setTrustInState(country.getTrustInState() - operation.getTrustInStateImpact());
        countryRepository.save(country);
    }

    public float calculateTrustInStateImpact(ToolUsage operation, Country country) {
        return operation.getImpact() / 100f;
    }

}
