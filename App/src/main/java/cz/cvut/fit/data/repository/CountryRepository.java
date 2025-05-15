package cz.cvut.fit.data.repository;

import cz.cvut.fit.data.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findAllByGameIdOrderByName(Long gameId);
}
