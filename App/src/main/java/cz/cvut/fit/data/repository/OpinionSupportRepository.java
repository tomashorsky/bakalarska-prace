package cz.cvut.fit.data.repository;

import cz.cvut.fit.data.entity.OpinionSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OpinionSupportRepository extends JpaRepository<OpinionSupport, Long> {

    OpinionSupport findByCountryIdAndTeamId(Long countryId, Long teamId);

    List<OpinionSupport> findAllByCountryIdOrderById(Long countryId);
}
