package com.example.app.entity;

import com.example.app.enums.EconomicSituation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float mediaLiteracy;
    private float trustInState;
    private EconomicSituation economy;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpinionSupport> opinionSupporters = new ArrayList<>();

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Action> actions = new ArrayList<>();

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToolUsage> toolUsages = new ArrayList<>();

    public OpinionSupport getOpinionSupportByTeamId(Long teamId) {
        return opinionSupporters.stream()
                .filter(opinionSupporter -> opinionSupporter.getTeam().getId().equals(teamId))
                .findFirst()
                .orElse(null);
    }
}
