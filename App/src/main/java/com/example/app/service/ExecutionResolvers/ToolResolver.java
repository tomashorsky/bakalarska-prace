package com.example.app.service.ExecutionResolvers;

import com.example.app.entity.*;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.OpinionSupportRepository;
import com.example.app.repository.ToolUsageRepository;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ToolResolver extends AbstractResolver<Tool, ToolUsage> {
    private static final int COOLDOWN = 4;
    private final ToolUsageRepository toolUsageRepository;

    public ToolResolver(CountryRepository countryRepository, OpinionSupportRepository opinionSupportRepository, ToolUsageRepository toolUsageRepository) {
        super(countryRepository, opinionSupportRepository);
        this.toolUsageRepository = toolUsageRepository;
    }

    public int resolveExecution(Tool tool, Team team, Country country) {
        tool.setCanBeUsedAt(LocalDateTime.now().plusMinutes(COOLDOWN));
        int impact = calculateImpact(tool, country);

        ToolUsage toolUsage = new ToolUsage();
        toolUsage.setName(tool.getType().getName());
        toolUsage.setImpact(impact);
        toolUsage.setTool(tool);
        toolUsage.setCountry(country);
        toolUsage.setTrustInStateImpact(calculateTrustInStateImpact(toolUsage, country));
        toolUsageRepository.save(toolUsage);

        recordImpact(country, toolUsage, team);
        return toolUsage.getImpact();
    }

    protected int calculateImpact(Tool tool, Country country) {
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


}
