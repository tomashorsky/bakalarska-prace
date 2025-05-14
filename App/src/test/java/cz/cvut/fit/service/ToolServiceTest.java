package cz.cvut.fit.service;

import cz.cvut.fit.config.GameMessageProperties;
import cz.cvut.fit.data.entity.Team;
import cz.cvut.fit.data.entity.Tool;
import cz.cvut.fit.data.enums.ToolType;
import cz.cvut.fit.data.repository.TeamRepository;
import cz.cvut.fit.data.repository.ToolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToolServiceTest {

    @Mock private ToolRepository toolRepository;
    @Mock private TeamRepository teamRepository;
    @Mock private GameMessageProperties gameMessages;

    @InjectMocks
    private ToolService toolService;

    @Test
    void addTool_shouldSaveToolAndReturnMessage() {
        Long teamId = 1L;
        String toolType = "DEEPFAKE_MAKER";
        ToolType type = ToolType.DEEPFAKE_MAKER;
        Team team = new Team();
        team.setId(teamId);
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(gameMessages.getToolAdded()).thenReturn("Nástroj {0} byl přidán!");

        String result = toolService.addTool(teamId, toolType);

        ArgumentCaptor<Tool> captor = ArgumentCaptor.forClass(Tool.class);
        verify(toolRepository).save(captor.capture());

        Tool saved = captor.getValue();
        assertEquals(type, saved.getType());
        assertEquals(team, saved.getTeam());

        assertEquals("Nástroj "+ type.getLabel() + " byl přidán!", result);
    }

    @Test
    void getUnownedTools_shouldReturnToolsNotOwnedByTeam() {
        Long teamId = 1L;
        Tool tool1 = new Tool();
        tool1.setType(ToolType.DEEPFAKE_MAKER);
        Tool tool2 = new Tool();
        tool2.setType(ToolType.TROLL_FARM);
        when(toolRepository.findAllByTeamIdOrderByType(teamId))
                .thenReturn(List.of(tool1, tool2));

        List<ToolType> unowned = toolService.getUnownedTools(teamId);

        List<ToolType> expected = Arrays.stream(ToolType.values())
                .filter(t -> t != ToolType.DEEPFAKE_MAKER && t != ToolType.TROLL_FARM)
                .toList();

        assertEquals(expected, unowned);
    }
}
