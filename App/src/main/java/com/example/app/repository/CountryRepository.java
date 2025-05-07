package com.example.app.repository;

import com.example.app.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findAllByGameIdOrderByName(Long gameId);
}
