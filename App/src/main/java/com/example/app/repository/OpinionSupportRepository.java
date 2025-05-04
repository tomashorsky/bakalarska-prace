package com.example.app.repository;

import com.example.app.entity.OpinionSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OpinionSupportRepository extends JpaRepository<OpinionSupport, Long> {

    OpinionSupport findByCountryIdAndTeamId(Long countryId, Long teamId);
}
