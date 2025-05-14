package cz.cvut.fit.data.repository;

import cz.cvut.fit.data.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByGameIdOrderById(Long gameId);
}
