package org.switch2022.project.repository.jpa;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.datamodel.jpa.ProjectJPA;
import org.switch2022.project.datamodel.jpa.ResourceInProjectJpa;
import org.switch2022.project.datamodel.jpa.assemblers.ProjectJpaAssembler;
import org.switch2022.project.datamodel.jpa.assemblers.ResourceInProjectJpaAssembler;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.repository.jpa.interfaces.ProjectJpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectRepositoryImpl.class)
class ProjectRepositoryImplTest {

    @MockBean
    Project project;

    @MockBean
    ProjectJPA projectJPA;

    @MockBean
    Optional optionalProjectJPA;

    @MockBean
    ProjectCode projectCode;

    @MockBean
    ProjectJpaRepository projectJpaRepository;

    @Autowired
    ProjectRepositoryImpl projectRepositoryImpl;

    @MockBean
    ResourceInProjectJpa resourceInProjectJpa;

    @MockBean
    ResourceInProject resourceInProject;

    @Test
    void ensureSaveProject() {
        //Arrange
        String projectCodeValue = "PRJ1";

        Project expected = project;
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCodeValue);

        when(projectJpaRepository.findByProjectCode(projectCodeValue)).thenReturn(optionalProjectJPA);
        when(optionalProjectJPA.isPresent()).thenReturn(false);

        try (MockedStatic<ProjectJpaAssembler> assemblerDouble = Mockito.mockStatic(ProjectJpaAssembler.class)) {
            assemblerDouble.when(() -> ProjectJpaAssembler.toDataModel(project)).thenReturn(projectJPA);
            assemblerDouble.when(() -> ProjectJpaAssembler.toDomain(projectJPA)).thenReturn(project);

            when(projectJpaRepository.save(projectJPA)).thenReturn(projectJPA);

            //Act
            Project result = projectRepositoryImpl.save(project);

            //Assert
            assertEquals(expected, result);
        }
    }

    @Test
    void ensureUpdateProject() {
        //Arrange
        String projectCodeValue = "PRJ1";

        Project expected = project;
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectCode.getProjectCode()).thenReturn(projectCodeValue);

        when(projectJpaRepository.findByProjectCode(projectCodeValue)).thenReturn(optionalProjectJPA);
        when(optionalProjectJPA.isPresent()).thenReturn(true);

        when(optionalProjectJPA.get()).thenReturn(projectJPA);

        try (MockedStatic<ProjectJpaAssembler> assemblerDouble = Mockito.mockStatic(ProjectJpaAssembler.class)) {
            assemblerDouble.when(() -> ProjectJpaAssembler.toDataModel(project)).thenReturn(projectJPA);
            assemblerDouble.when(() -> ProjectJpaAssembler.toDomain(projectJPA)).thenReturn(project);

            when(projectJpaRepository.save(projectJPA)).thenReturn(projectJPA);

            //Act
            Project result = projectRepositoryImpl.save(project);

            //Assert
            assertEquals(expected, result);
        }
    }


    @Test
    void findAll() {
        //Arrange
        List<ProjectJPA> projectJPAList = Collections.singletonList(projectJPA);

        when(projectJpaRepository.findAll()).thenReturn(projectJPAList);

        List<Project> expected = Collections.singletonList(project);

        try (MockedStatic<ProjectJpaAssembler> assemblerDouble = Mockito.mockStatic(ProjectJpaAssembler.class)) {
            assemblerDouble.when(() -> ProjectJpaAssembler.toDomain(projectJPA)).thenReturn(project);

            //Act
            List<Project> result = projectRepositoryImpl.findAll();

            //Assert
            assertEquals(expected, result);
        }
    }

    @Test
    void ensureGetOptionalProject() {
        //Arrange
        when(projectCode.getProjectCode()).thenReturn("PRJ1");

        when(projectJpaRepository.findByProjectCode("PRJ1")).thenReturn(optionalProjectJPA);

        when(optionalProjectJPA.isPresent()).thenReturn(true);

        when(optionalProjectJPA.get()).thenReturn(projectJPA);

        Optional<Project> expected = Optional.of(project);


        try (MockedStatic<ProjectJpaAssembler> assemblerDouble = Mockito.mockStatic(ProjectJpaAssembler.class)) {
            assemblerDouble.when(() -> ProjectJpaAssembler.toDomain(projectJPA)).thenReturn(project);

            List<ResourceInProjectJpa> resourceInProjectJpas = Collections.singletonList(resourceInProjectJpa);

            when(projectJPA.getResourceInProjectJpas()).thenReturn(resourceInProjectJpas);

            try (MockedStatic<ResourceInProjectJpaAssembler> assemblerDouble2 = Mockito.mockStatic(ResourceInProjectJpaAssembler.class)) {
                assemblerDouble2.when(() -> ResourceInProjectJpaAssembler.toDomain(resourceInProjectJpa)).thenReturn(resourceInProject);

                //Act
                Optional<Project> result = projectRepositoryImpl.ofIdentity(projectCode);

                //Assert
                assertEquals(expected, result);
            }
        }
    }

    @Test
    void ensureGetEmptyOptionalProject() {
        //Arrange
        when(projectCode.getProjectCode()).thenReturn("PRJ1");

        when(projectJpaRepository.findByProjectCode("PRJ1")).thenReturn(optionalProjectJPA);

        when(optionalProjectJPA.isPresent()).thenReturn(false);

        Optional<Project> expected = Optional.empty();

        //Act
        Optional<Project> result = projectRepositoryImpl.ofIdentity(projectCode);

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void ensureGetEmptyOptional() {
        //Arrange
        when(projectCode.getProjectCode()).thenReturn("PRJ1");

        when(projectJpaRepository.findByProjectCode("PRJ1")).thenReturn(optionalProjectJPA);

        when(optionalProjectJPA.isPresent()).thenReturn(false);


        Optional<Project> expected = Optional.empty();

        //Act
        Optional<Project> result = projectRepositoryImpl.ofIdentity(projectCode);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureContainsProject() {
        //Arrange
        when(projectCode.getProjectCode()).thenReturn("PRJ1");

        when(projectJpaRepository.existsByProjectCode("PRJ1")).thenReturn(true);

        boolean expected = true;

        //Act
        boolean result = projectRepositoryImpl.containsOfIdentity(projectCode);

        //Assert
        assertEquals(expected, result);
    }
}
