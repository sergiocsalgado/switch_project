package org.switch2022.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.value_objects.ProjectCode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InMemoryProjectRepository.class)
class InMemoryProjectRepositoryTest {

    @Autowired
    InMemoryProjectRepository inMemoryProjectRepository;

    @MockBean
    Project project;

    @MockBean
    ProjectCode projectCode;

    @Test
    void ensureSaveProject() {
        //Arrange
        Project expected = project;

        //Act
        Project result = inMemoryProjectRepository.save(project);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetListOfProjects() {
        //Arrange
        when(project.identity()).thenReturn(projectCode);

        inMemoryProjectRepository.save(project);

        List<Project> expected = Arrays.asList(project);

        //Act
        List<Project> result = inMemoryProjectRepository.findAll();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureDoNotGetProjectByProjectCode_projectRepositoryEmpty() {
        //Arrange
        Optional<Project> expected = Optional.empty();

        //Act
        Optional<Project> result = inMemoryProjectRepository.ofIdentity(projectCode);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetProjectByProjectCode() {
        //Arrange
        when(project.identity()).thenReturn(projectCode);
        inMemoryProjectRepository.save(project);

        Optional<Project> expected = Optional.of(project);

        //Act
        Optional<Project> result = inMemoryProjectRepository.ofIdentity(projectCode);

        //Assert
        assertEquals(expected, result);
    }
}