package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ProjectDTO;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.project.FactoryProject;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the RegisterProjectServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RegisterProjectServiceImpl.class)
class RegisterProjectServiceImplTest {

    @MockBean
    Project project;

    @MockBean
    FactoryProject factoryProject;

    @MockBean
    @Qualifier("projectJPARepository")
    Repository<ProjectCode, Project> projectRepository;

    @MockBean
    @Qualifier("customerJPARepository")
    Repository<CustomerID, Customer> customerRepository;

    @MockBean
    @Qualifier("businessSectorJPARepository")
    Repository<BusinessSectorID, BusinessSector> businessSectorRepository;

    @MockBean
    @Qualifier("typologyJPARepository")
    Repository<TypologyID, Typology> typologyRepository;

    @Autowired
    RegisterProjectServiceImpl registerProjectServiceImpl;

    @MockBean
    ProjectDTO projectDTO;

    @MockBean
    Customer customer;

    @MockBean
    BusinessSector businessSector;

    @MockBean
    Typology typology;

    @MockBean
    Name name;

    @MockBean
    CustomerID customerID;

    @MockBean
    BusinessSectorID businessSectorID;

    @MockBean
    TypologyID typologyID;

    /**
     * Test for {@link RegisterProjectServiceImpl#registerProject(ProjectDTO)}
     */

    @Test
    void ensureProjectIsRegistered() {
        //Arrange
        String projCode = "PRJ1";
        String projName = "E-commerce platform";
        String projDescription = "Update layout of website";
        String customerName = "c1";
        String businessSectorDescription = "bs1";
        String typologyDescription = "typ1";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String projectStartDate = "2023-10-10";
        String projectEndDate = "2024-10-10";
        String projectBudget = "5000EUR";
        int value = 5000;
        String currency = "EUR";

        ProjectCode projectCode = new ProjectCode(projCode);
        Name projectName = new Name(projName);
        Description projectDescription = new Description(projDescription);
        ProjectStatus projectStatus = new ProjectStatus("planned");
        PositiveNumber sprintDuration = new PositiveNumber(durationOfSprint);
        PositiveNumber numberOfPlannedSprints = new PositiveNumber(numberOfSprints);
        Cost budget = new Cost(value, currency);

        when(projectDTO.getProjectCode()).thenReturn(projCode);
        when(projectDTO.getName()).thenReturn(projName);
        when(projectDTO.getDescription()).thenReturn(projDescription);
        when(projectDTO.getSprintDuration()).thenReturn(durationOfSprint);
        when(projectDTO.getNumberOfPlannedSprints()).thenReturn(numberOfSprints);
        when(projectDTO.getStartDate()).thenReturn(projectStartDate);
        when(projectDTO.getEndDate()).thenReturn(projectEndDate);
        when(projectDTO.getBudget()).thenReturn(projectBudget);


        LocalDate startDate = DateManagement.toLocalDate(projectStartDate);
        LocalDate endDate = DateManagement.toLocalDate(projectEndDate);

        Period period = new Period(startDate, endDate);

        when(projectDTO.getCustomer()).thenReturn(customerName);
        when(projectDTO.getBusinessSector()).thenReturn(businessSectorDescription);
        when(projectDTO.getTypology()).thenReturn(typologyDescription);

        List<Customer> customers = Collections.singletonList(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customer.getName()).thenReturn(name);
        when(name.getValue()).thenReturn(customerName);
        when(customer.getCustomerID()).thenReturn(customerID);


        List<BusinessSector> businessSectors = Collections.singletonList(businessSector);

        Description bsDescriptionDouble = mock(Description.class);

        when(businessSectorRepository.findAll()).thenReturn(businessSectors);
        when(businessSector.getBusinessSectorDescription()).thenReturn(bsDescriptionDouble);
        when(bsDescriptionDouble.getDescription()).thenReturn(businessSectorDescription);
        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);


        Description typDescriptionDouble = mock(Description.class);

        List<Typology> typologies = Collections.singletonList(typology);

        when(typologyRepository.findAll()).thenReturn(typologies);
        when(typology.getDescription()).thenReturn(typDescriptionDouble);
        when(typDescriptionDouble.getDescription()).thenReturn(typologyDescription);
        when(typology.getTypologyID()).thenReturn(typologyID);


        when(factoryProject.createProject(projectCode, projectName, projectDescription, customerID,
                businessSectorID, typologyID, projectStatus, sprintDuration, numberOfPlannedSprints,
                budget, period)).thenReturn(project);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(false);

        Project projectDouble = mock(Project.class);
        Name projectNameDouble = mock(Name.class);
        when(projectNameDouble.getValue()).thenReturn("name");

        List<Project> projects = Collections.singletonList(projectDouble);

        when(projectRepository.findAll()).thenReturn(projects);
        when(projectDouble.getProjectName()).thenReturn(projectNameDouble);

        when(projectRepository.save(project)).thenReturn(project);


        Project expected = project;

        //Act
        Project result = registerProjectServiceImpl.registerProject(projectDTO);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectIsNotRegistered_projectCodeAlreadyExists() {
        //Arrange
        String projCode = "PRJ1";
        String projName = "E-commerce platform";
        String projDescription = "Update layout of website";
        String customerName = "c1";
        String businessSectorDescription = "bs1";
        String typologyDescription = "typ1";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String projectStartDate = "2023-10-10";
        String projectEndDate = "";
        String projectBudget = "5000EUR";
        int value = 5000;
        String currency = "EUR";

        ProjectCode projectCode = new ProjectCode(projCode);
        Name projectName = new Name(projName);
        Description projectDescription = new Description(projDescription);
        ProjectStatus projectStatus = new ProjectStatus("planned");
        PositiveNumber sprintDuration = new PositiveNumber(durationOfSprint);
        PositiveNumber numberOfPlannedSprints = new PositiveNumber(numberOfSprints);
        Cost budget = new Cost(value, currency);

        when(projectDTO.getProjectCode()).thenReturn(projCode);
        when(projectDTO.getName()).thenReturn(projName);
        when(projectDTO.getDescription()).thenReturn(projDescription);
        when(projectDTO.getSprintDuration()).thenReturn(durationOfSprint);
        when(projectDTO.getNumberOfPlannedSprints()).thenReturn(numberOfSprints);
        when(projectDTO.getStartDate()).thenReturn(projectStartDate);
        when(projectDTO.getEndDate()).thenReturn(projectEndDate);
        when(projectDTO.getBudget()).thenReturn(projectBudget);


        LocalDate startDate = DateManagement.toLocalDate(projectStartDate);
        LocalDate endDate = startDate.plus(100, ChronoUnit.YEARS);

        Period period = new Period(startDate, endDate);

        when(projectDTO.getCustomer()).thenReturn(customerName);
        when(projectDTO.getBusinessSector()).thenReturn(businessSectorDescription);
        when(projectDTO.getTypology()).thenReturn(typologyDescription);

        List<Customer> customers = Collections.singletonList(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customer.getName()).thenReturn(name);
        when(name.getValue()).thenReturn(customerName);
        when(customer.getCustomerID()).thenReturn(customerID);


        List<BusinessSector> businessSectors = Collections.singletonList(businessSector);

        Description bsDescriptionDouble = mock(Description.class);

        when(businessSectorRepository.findAll()).thenReturn(businessSectors);
        when(businessSector.getBusinessSectorDescription()).thenReturn(bsDescriptionDouble);
        when(bsDescriptionDouble.getDescription()).thenReturn(businessSectorDescription);
        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);


        Description typDescriptionDouble = mock(Description.class);

        List<Typology> typologies = Collections.singletonList(typology);

        when(typologyRepository.findAll()).thenReturn(typologies);
        when(typology.getDescription()).thenReturn(typDescriptionDouble);
        when(typDescriptionDouble.getDescription()).thenReturn(typologyDescription);
        when(typology.getTypologyID()).thenReturn(typologyID);


        when(factoryProject.createProject(projectCode, projectName, projectDescription, customerID,
                businessSectorID, typologyID, projectStatus, sprintDuration, numberOfPlannedSprints,
                budget, period)).thenReturn(project);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            registerProjectServiceImpl.registerProject(projectDTO);
        });
        //Assert
        assertEquals("Project already exists.", exception.getMessage());
    }

    @Test
    void ensureProjectIsNotRegistered_projectNameAlreadyExists() {
        //Arrange
        String projCode = "PRJ1";
        String projName = "E-commerce platform";
        String projDescription = "Update layout of website";
        String customerName = "c1";
        String businessSectorDescription = "bs1";
        String typologyDescription = "typ1";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String projectStartDate = "2023-10-10";
        String projectEndDate = "";
        String projectBudget = "5000EUR";
        int value = 5000;
        String currency = "EUR";

        ProjectCode projectCode = new ProjectCode(projCode);
        Name projectName = new Name(projName);
        Description projectDescription = new Description(projDescription);
        ProjectStatus projectStatus = new ProjectStatus("planned");
        PositiveNumber sprintDuration = new PositiveNumber(durationOfSprint);
        PositiveNumber numberOfPlannedSprints = new PositiveNumber(numberOfSprints);
        Cost budget = new Cost(value, currency);

        when(projectDTO.getProjectCode()).thenReturn(projCode);
        when(projectDTO.getName()).thenReturn(projName);
        when(projectDTO.getDescription()).thenReturn(projDescription);
        when(projectDTO.getSprintDuration()).thenReturn(durationOfSprint);
        when(projectDTO.getNumberOfPlannedSprints()).thenReturn(numberOfSprints);
        when(projectDTO.getStartDate()).thenReturn(projectStartDate);
        when(projectDTO.getEndDate()).thenReturn(projectEndDate);
        when(projectDTO.getBudget()).thenReturn(projectBudget);


        LocalDate startDate = DateManagement.toLocalDate(projectStartDate);
        LocalDate endDate = startDate.plus(100, ChronoUnit.YEARS);

        Period period = new Period(startDate, endDate);

        when(projectDTO.getCustomer()).thenReturn(customerName);
        when(projectDTO.getBusinessSector()).thenReturn(businessSectorDescription);
        when(projectDTO.getTypology()).thenReturn(typologyDescription);

        List<Customer> customers = Collections.singletonList(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customer.getName()).thenReturn(name);
        when(name.getValue()).thenReturn(customerName);
        when(customer.getCustomerID()).thenReturn(customerID);


        List<BusinessSector> businessSectors = Collections.singletonList(businessSector);

        Description bsDescriptionDouble = mock(Description.class);

        when(businessSectorRepository.findAll()).thenReturn(businessSectors);
        when(businessSector.getBusinessSectorDescription()).thenReturn(bsDescriptionDouble);
        when(bsDescriptionDouble.getDescription()).thenReturn(businessSectorDescription);
        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);


        Description typDescriptionDouble = mock(Description.class);

        List<Typology> typologies = Collections.singletonList(typology);

        when(typologyRepository.findAll()).thenReturn(typologies);
        when(typology.getDescription()).thenReturn(typDescriptionDouble);
        when(typDescriptionDouble.getDescription()).thenReturn(typologyDescription);
        when(typology.getTypologyID()).thenReturn(typologyID);


        when(factoryProject.createProject(projectCode, projectName, projectDescription, customerID,
                businessSectorID, typologyID, projectStatus, sprintDuration, numberOfPlannedSprints,
                budget, period)).thenReturn(project);

        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(false);

        Project projectDouble = mock(Project.class);
        List<Project> projects = Collections.singletonList(projectDouble);

        when(projectRepository.findAll()).thenReturn(projects);
        when(projectDouble.getProjectName()).thenReturn(projectName);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            registerProjectServiceImpl.registerProject(projectDTO);
        });
        //Assert
        assertEquals("Project already exists.", exception.getMessage());
    }

    @Test
    void ensureProjectIsNotRegistered_invalidProjectCode() {
        //Arrange
        String projCode = "";
        String projName = "E-commerce platform";
        String projDescription = "Update layout of website";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String projectStartDate = "2023-10-10";
        String projectEndDate = "";
        String projectBudget = "5000EUR";

        when(projectDTO.getProjectCode()).thenReturn(projCode);
        when(projectDTO.getName()).thenReturn(projName);
        when(projectDTO.getDescription()).thenReturn(projDescription);
        when(projectDTO.getSprintDuration()).thenReturn(durationOfSprint);
        when(projectDTO.getNumberOfPlannedSprints()).thenReturn(numberOfSprints);
        when(projectDTO.getStartDate()).thenReturn(projectStartDate);
        when(projectDTO.getEndDate()).thenReturn(projectEndDate);
        when(projectDTO.getBudget()).thenReturn(projectBudget);


        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            registerProjectServiceImpl.registerProject(projectDTO);
        });
        //Assert
        assertEquals("Project code cannot be empty", exception.getMessage());
    }

    @Test
    void ensureProjectIsNotRegistered_customerIDNotFound() {
        //Arrange
        String projCode = "PRJ1";
        String projName = "E-commerce platform";
        String projDescription = "Update layout of website";
        String customerName = "c1";
        String customerName2 = "c2";
        String businessSectorDescription = "bs1";
        String typologyDescription = "typ1";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String projectStartDate = "2023-10-10";
        String projectEndDate = "";
        String projectBudget = "5000EUR";


        when(projectDTO.getProjectCode()).thenReturn(projCode);
        when(projectDTO.getName()).thenReturn(projName);
        when(projectDTO.getDescription()).thenReturn(projDescription);
        when(projectDTO.getSprintDuration()).thenReturn(durationOfSprint);
        when(projectDTO.getNumberOfPlannedSprints()).thenReturn(numberOfSprints);
        when(projectDTO.getStartDate()).thenReturn(projectStartDate);
        when(projectDTO.getEndDate()).thenReturn(projectEndDate);
        when(projectDTO.getBudget()).thenReturn(projectBudget);


        when(projectDTO.getCustomer()).thenReturn(customerName);
        when(projectDTO.getBusinessSector()).thenReturn(businessSectorDescription);
        when(projectDTO.getTypology()).thenReturn(typologyDescription);

        List<Customer> customers = Collections.singletonList(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customer.getName()).thenReturn(name);
        when(name.getValue()).thenReturn(customerName2);


        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            registerProjectServiceImpl.registerProject(projectDTO);
        });
        //Assert
        assertEquals("CustomerID not found for creating a project", exception.getMessage());
    }

    @Test
    void ensureProjectIsNotRegistered_businessSectorIDNotFound() {
        //Arrange
        String projCode = "PRJ1";
        String projName = "E-commerce platform";
        String projDescription = "Update layout of website";
        String customerName = "c1";
        String businessSectorDescription = "bs1";
        String businessSectorDescription2 = "bs2";
        String typologyDescription = "typ1";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String projectStartDate = "2023-10-10";
        String projectEndDate = "";
        String projectBudget = "5000EUR";


        when(projectDTO.getProjectCode()).thenReturn(projCode);
        when(projectDTO.getName()).thenReturn(projName);
        when(projectDTO.getDescription()).thenReturn(projDescription);
        when(projectDTO.getSprintDuration()).thenReturn(durationOfSprint);
        when(projectDTO.getNumberOfPlannedSprints()).thenReturn(numberOfSprints);
        when(projectDTO.getStartDate()).thenReturn(projectStartDate);
        when(projectDTO.getEndDate()).thenReturn(projectEndDate);
        when(projectDTO.getBudget()).thenReturn(projectBudget);


        when(projectDTO.getCustomer()).thenReturn(customerName);
        when(projectDTO.getBusinessSector()).thenReturn(businessSectorDescription);
        when(projectDTO.getTypology()).thenReturn(typologyDescription);

        List<Customer> customers = Collections.singletonList(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customer.getName()).thenReturn(name);
        when(name.getValue()).thenReturn(customerName);
        when(customer.getCustomerID()).thenReturn(customerID);


        List<BusinessSector> businessSectors = Collections.singletonList(businessSector);

        Description bsDescriptionDouble = mock(Description.class);

        when(businessSectorRepository.findAll()).thenReturn(businessSectors);
        when(businessSector.getBusinessSectorDescription()).thenReturn(bsDescriptionDouble);
        when(bsDescriptionDouble.getDescription()).thenReturn(businessSectorDescription2);


        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            registerProjectServiceImpl.registerProject(projectDTO);
        });
        //Assert
        assertEquals("BusinessSectorID not found for creating a project", exception.getMessage());
    }

    @Test
    void ensureProjectIsNotRegistered_typologyIDNotFound() {
        //Arrange
        String projCode = "PRJ1";
        String projName = "E-commerce platform";
        String projDescription = "Update layout of website";
        String customerName = "c1";
        String businessSectorDescription = "bs1";
        String typologyDescription = "typ1";
        String typologyDescription2 = "typ2";
        int durationOfSprint = 3;
        int numberOfSprints = 15;
        String projectStartDate = "2023-10-10";
        String projectEndDate = "";
        String projectBudget = "5000EUR";
        int value = 5000;
        String currency = "EUR";


        when(projectDTO.getProjectCode()).thenReturn(projCode);
        when(projectDTO.getName()).thenReturn(projName);
        when(projectDTO.getDescription()).thenReturn(projDescription);
        when(projectDTO.getSprintDuration()).thenReturn(durationOfSprint);
        when(projectDTO.getNumberOfPlannedSprints()).thenReturn(numberOfSprints);
        when(projectDTO.getStartDate()).thenReturn(projectStartDate);
        when(projectDTO.getEndDate()).thenReturn(projectEndDate);
        when(projectDTO.getBudget()).thenReturn(projectBudget);


        when(projectDTO.getCustomer()).thenReturn(customerName);
        when(projectDTO.getBusinessSector()).thenReturn(businessSectorDescription);
        when(projectDTO.getTypology()).thenReturn(typologyDescription);

        List<Customer> customers = Collections.singletonList(customer);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customer.getName()).thenReturn(name);
        when(name.getValue()).thenReturn(customerName);
        when(customer.getCustomerID()).thenReturn(customerID);


        List<BusinessSector> businessSectors = Collections.singletonList(businessSector);

        Description bsDescriptionDouble = mock(Description.class);

        when(businessSectorRepository.findAll()).thenReturn(businessSectors);
        when(businessSector.getBusinessSectorDescription()).thenReturn(bsDescriptionDouble);
        when(bsDescriptionDouble.getDescription()).thenReturn(businessSectorDescription);
        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);


        Description typDescriptionDouble = mock(Description.class);

        List<Typology> typologies = Collections.singletonList(typology);

        when(typologyRepository.findAll()).thenReturn(typologies);
        when(typology.getDescription()).thenReturn(typDescriptionDouble);
        when(typDescriptionDouble.getDescription()).thenReturn(typologyDescription2);


        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            registerProjectServiceImpl.registerProject(projectDTO);
        });
        //Assert
        assertEquals("TypologyID not found for creating a project", exception.getMessage());
    }
}