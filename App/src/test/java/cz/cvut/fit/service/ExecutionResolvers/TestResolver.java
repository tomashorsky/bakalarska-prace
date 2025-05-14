package cz.cvut.fit.service.ExecutionResolvers;

import cz.cvut.fit.data.entity.Country;
import cz.cvut.fit.data.entity.InfluenceOperation;
import cz.cvut.fit.data.entity.Team;
import cz.cvut.fit.data.repository.CountryRepository;
import cz.cvut.fit.data.repository.OpinionSupportRepository;

/**
 * Fake implementation of AbstractResolver for testing purposes.
 * call methods are needed to test protected methods.
 */
class TestResolver extends AbstractResolver<InfluenceOperation, InfluenceOperation> {
    public TestResolver(CountryRepository countryRepository, OpinionSupportRepository opinionSupportRepository) {
        super(countryRepository, opinionSupportRepository);
    }

    @Override
    public int resolveExecution(InfluenceOperation operation, Team team, Country country) {
        return 0;
    }

    @Override
    protected int calculateImpactByType(InfluenceOperation operation, Team team, Country country) {
        return 0;
    }

    public int callCalculateImpact(int support, int stat) {
        return calculateImpact(support, stat);
    }

    public int callGetNormalizedTrustInState(Country country) {
        return getNormalizedTrustInState(country);
    }

    public int callGetNormalizedOpinionSupporters(Country country, Team team) {
        return getNormalizedOpinionSupporters(country, team);
    }

    public float callCalculateTrustInStateImpact(Country country) {
        return calculateTrustInStateImpact(country);
    }
}