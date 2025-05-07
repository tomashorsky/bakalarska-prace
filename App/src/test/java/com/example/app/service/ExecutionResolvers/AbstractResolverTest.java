package com.example.app.service.ExecutionResolvers;

import com.example.app.entity.Country;
import com.example.app.entity.OpinionSupport;
import com.example.app.entity.Team;
import com.example.app.repository.CountryRepository;
import com.example.app.repository.OpinionSupportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AbstractResolverTest {

    @Mock
    private CountryRepository countryRepository;
    @Mock
    private OpinionSupportRepository opinionSupportRepository;

    private TestResolver resolver;

    @BeforeEach
    void setUp() {
        resolver = new TestResolver(countryRepository, opinionSupportRepository);
    }

    @Test
    void calculateImpact_shouldDecreaseWithStatDecrease() {
        int result1 = resolver.callCalculateImpact(100, 100);
        int result2 = resolver.callCalculateImpact(100, 72);
        int result3 = resolver.callCalculateImpact(100, 49);
        int result4 = resolver.callCalculateImpact(100, 23);
        int result5 = resolver.callCalculateImpact(100, 0);

        assertTrue(result1 > result2);
        assertTrue(result2 > result3);
        assertTrue(result3 > result4);
        assertTrue(result4 > result5);
    }

    @Test
    void calculateImpact_shouldDecreaseWithOpinionSupport() {
        int result1 = resolver.callCalculateImpact(0, 50);
        int result2 = resolver.callCalculateImpact(400, 50);
        int result3 = resolver.callCalculateImpact(1000, 50);

        assertTrue(result1 > result2);
        assertTrue(result2 > result3);
    }

    @Test
    void calculateTrustInStateImpact_valueShouldDecreaseWithEachCall() {
        Country country = new Country();
        country.setTrustInState(50f);
        float result1 = resolver.callCalculateTrustInStateImpact(country);
        country.setTrustInState(country.getTrustInState() - result1);
        float result2 = resolver.callCalculateTrustInStateImpact(country);
        country.setTrustInState(country.getTrustInState() - result2);
        float result3 = resolver.callCalculateTrustInStateImpact(country);

        assertTrue(result1 > result2);
        assertTrue(result2 > result3);
    }

    @Test
    void getNormalizedTrustInState_shouldIncreaseValue() {
        Country country = new Country();
        country.setTrustInState(71f);
        assertTrue(71 < resolver.callGetNormalizedTrustInState(country));

        country.setTrustInState(95f);
        assertTrue(95 < resolver.callGetNormalizedTrustInState(country));

        country.setTrustInState(5f);
        assertTrue(5 < resolver.callGetNormalizedTrustInState(country));
    }

    @Test
    void getNormalizedOpinionSupporters_shouldDecreaseValue() {
        Country country = new Country();
        country.setId(1L);
        Team team = new Team();
        team.setId(1L);
        OpinionSupport support = new OpinionSupport();

        when(opinionSupportRepository.findByCountryIdAndTeamId(1L, 1L))
                .thenReturn(support);

        support.setSupportersCount(50);
        assertTrue(50 > resolver.callGetNormalizedOpinionSupporters(country, team));

        support.setSupportersCount(101);
        assertTrue(100 > resolver.callGetNormalizedOpinionSupporters(country, team));

        support.setSupportersCount(500);
        assertTrue(100 >= resolver.callGetNormalizedOpinionSupporters(country, team));
    }

}
