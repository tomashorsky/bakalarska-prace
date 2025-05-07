package com.example.app.service.ExecutionResolvers;

import com.example.app.entity.*;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.OpinionSupportRepository;
import com.example.app.repository.ToolUsageRepository;
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
        int impact = calculateImpactByType(tool, team, country);

        ToolUsage toolUsage = new ToolUsage();
        toolUsage.setName(tool.getType().getLabel());
        toolUsage.setImpact(impact);
        toolUsage.setTool(tool);
        toolUsage.setCountry(country);
        toolUsage.setTrustInStateImpact(calculateTrustInStateImpact(country));
        toolUsageRepository.save(toolUsage);

        recordImpact(country, toolUsage, team);
        return toolUsage.getImpact();
    }

    protected int calculateImpactByType(Tool tool, Team team, Country country) {
        int mediaLiteracy = country.getMediaLiteracy().getValue();
        int trustInState = getNormalizedTrustInState(country);
        int opinionSupport = opinionSupportRepository.findByCountryIdAndTeamId(country.getId(), team.getId()).getSupportersCount();
        int opinionSupportNorm = getNormalizedOpinionSupporters(country, team);

        return switch (tool.getType()) {
            case TROLL_FARM -> calculateImpact(opinionSupport, opinionSupportNorm);
            case DEEPFAKE_MAKER -> calculateImpactNegative(opinionSupport, trustInState);
            case FAKE_NEWS_SITE -> calculateImpact(opinionSupport, mediaLiteracy);
        };
    }
}
