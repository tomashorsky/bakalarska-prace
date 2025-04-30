package com.example.app.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class InfluenceOperation {
    private String name;
    private float impact;
    private float trustInStateImpact;
    @CreationTimestamp
    private LocalDateTime usedAt;
}
