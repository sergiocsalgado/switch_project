package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ProjectDTOMapper;
import org.switch2022.project.mapper.ResourcesInProjectDTO;
import org.switch2022.project.mapper.ResourcesInProjectMapper;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListResourcesServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListResourcesServiceImpl.class)
class ListResourcesServiceTest {
    @MockBean
    @Qualifier("projectJPARepository")
    Repository<ProjectCode, Project> projectRepository;
    @Autowired
    ListResourcesServiceImpl listResourcesService;

    @Test
    void ensureGetListOfResourcesInProjectDTO() {
        //Arrange
        Optional<Project> optionalDouble = mock(Optional.class);
        Project projectDouble = mock(Project.class);
        String projCode = "PRJ1";
        ProjectCode projectCode=new ProjectCode(projCode);


        ResourcesInProjectDTO resourcesInProjectDTODouble = mock(ResourcesInProjectDTO.class);
        List<ResourcesInProjectDTO> resourcesInProjectDTOS = Collections.singletonList(resourcesInProjectDTODouble);

        ResourceInProject resourceInProjectDouble = mock(ResourceInProject.class);
        List<ResourceInProject> resources = Collections.singletonList(resourceInProjectDouble);

        when(projectRepository.ofIdentity(projectCode)).thenReturn(optionalDouble);
        when(optionalDouble.isPresent()).thenReturn(true);
        when(optionalDouble.get()).thenReturn(projectDouble);

        when(projectDouble.getResources()).thenReturn(resources);

        List<ResourcesInProjectDTO> expected = Arrays.asList(resourcesInProjectDTODouble);

        try (MockedStatic<ResourcesInProjectMapper> mapperDouble = Mockito.mockStatic(ResourcesInProjectMapper.class)) {
            mapperDouble.when(() -> ResourcesInProjectMapper.toResourcesDTOList(resources,projectCode)).thenReturn(resourcesInProjectDTOS);

            //Act
            List<ResourcesInProjectDTO> result = listResourcesService.getResourcesInProjectDTO(projCode);
            //Assert
            assertEquals(expected, result);
        }
    }

    @Test
    void ensureGetEmptyListOfResourcesInProjectDTO_projectNotFound() {
        //Arrange
        Optional<Project> optionalDouble = mock(Optional.class);
        String projectCode = "PRJ1";
        ProjectCode projectCode_vo=mock(ProjectCode.class);

        when(projectRepository.ofIdentity(projectCode_vo)).thenReturn(optionalDouble);
        when(optionalDouble.isPresent()).thenReturn(false);

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            listResourcesService.getResourcesInProjectDTO(projectCode);
        });
            //Assert
            assertEquals("Project does not exists", exception.getMessage());
        }

    @Test
    void ListResources_FailedBecauseInvalidInputs() {
        //Arrange
        String projectCode = "";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            listResourcesService.getResourcesInProjectDTO(projectCode);
        });
        //Assert
        assertEquals("Project code cannot be empty", exception.getMessage());
    }
}