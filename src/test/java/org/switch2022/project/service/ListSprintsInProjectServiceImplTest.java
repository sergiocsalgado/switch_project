package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.SprintDTO;
import org.switch2022.project.mapper.SprintMapper;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.SprintID;
import org.switch2022.project.service.interfaces.ListSprintsInProjectService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListSprintsInProjectServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListSprintsInProjectServiceImpl.class)
class ListSprintsInProjectServiceImplTest {
    @MockBean
    @Qualifier("sprintJPARepository")
    Repository<SprintID, Sprint> sprintRepository;
    @MockBean
    Sprint sprint;
    @MockBean
    SprintDTO sprintDTO;
    @MockBean
    ProjectCode projectCode;

    @Autowired
    ListSprintsInProjectServiceImpl listSprintsInProjectService;

    /**
     * Test class for {@link ListSprintsInProjectServiceImpl#getSprintsInProject(String)}
     */
    @Test
    void getSprintsInProject() {
        //ARRANGE
        String projectCode_Input = "PRJ1";
        List<Sprint> sprintsList = Arrays.asList(sprint);
        when(sprintRepository.findAll()).thenReturn(sprintsList);
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCode_Input);

        List<SprintDTO> expected = Arrays.asList(sprintDTO);

        try (
                MockedStatic<SprintMapper> mapperDouble = Mockito.mockStatic(SprintMapper.class)) {
            mapperDouble.when(() -> SprintMapper.listSprintDTO(sprintsList)).thenReturn(expected);

            //ACT
            List<SprintDTO> result = listSprintsInProjectService.getSprintsInProject(projectCode_Input);

            //ASSERT
            assertEquals(expected, result);
        }
    }

    @Test
    void getEmptyListOfSprintsInProject(){
        //ARRANGE
        String projectCode_Input = "PRJ1";
        String projectCode_Input2 = "PRJ2";
        List<Sprint> sprintsList = Arrays.asList(sprint);
        when(sprintRepository.findAll()).thenReturn(sprintsList);
        when(sprint.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCode_Input2);

        List<SprintDTO> expected = Arrays.asList(sprintDTO);

        try (
                MockedStatic<SprintMapper> mapperDouble = Mockito.mockStatic(SprintMapper.class)) {
            mapperDouble.when(() -> SprintMapper.listSprintDTO(sprintsList)).thenReturn(expected);

            //ACT
            List<SprintDTO> result = listSprintsInProjectService.getSprintsInProject(projectCode_Input);

            //ASSERT
            assertNotEquals(expected, result);
        }
    }
}
