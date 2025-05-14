package cz.cvut.fit.data.entity;

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
    private int impact;
    private float trustInStateImpact;
    @CreationTimestamp
    private LocalDateTime usedAt;
}
