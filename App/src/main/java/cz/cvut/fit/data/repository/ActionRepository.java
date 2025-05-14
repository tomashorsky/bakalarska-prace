package cz.cvut.fit.data.repository;

import cz.cvut.fit.data.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
}

