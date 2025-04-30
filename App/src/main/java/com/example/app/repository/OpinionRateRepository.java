package com.example.app.repository;

import com.example.app.entity.OpinionSupporters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OpinionRateRepository extends JpaRepository<OpinionSupporters, Long> {
}
