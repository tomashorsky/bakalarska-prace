package cz.cvut.fit.data.repository;

import cz.cvut.fit.data.entity.ToolUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToolUsageRepository extends JpaRepository<ToolUsage, Long> {
}

