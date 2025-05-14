package cz.cvut.fit.service.dto;

import cz.cvut.fit.data.enums.ToolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolDTO {
    private Long id;
    private ToolType type;
    private Long teamId;
    private LocalDateTime canBeUsedAt;
    private Long canBeUsedAtInMillis;
}
