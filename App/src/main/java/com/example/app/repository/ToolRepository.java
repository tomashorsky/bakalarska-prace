package com.example.app.repository;

import com.example.app.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    public List<Tool> findAllByTeamId(Long teamId);
}

