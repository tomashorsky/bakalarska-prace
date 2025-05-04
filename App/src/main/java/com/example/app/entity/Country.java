package com.example.app.entity;

import com.example.app.enums.SituationLevel;
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
    private SituationLevel mediaLiteracy;
    private float trustInState;
    private SituationLevel economy;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpinionSupport> opinionSupports = new ArrayList<>();

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Action> actions = new ArrayList<>();

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToolUsage> toolUsages = new ArrayList<>();

    public OpinionSupport getOpinionSupportByTeamId(Long teamId) {
        return opinionSupports.stream()
                .filter(opinionSupporter -> opinionSupporter.getTeam().getId().equals(teamId))
                .findFirst()
                .orElse(null);
    }
}
