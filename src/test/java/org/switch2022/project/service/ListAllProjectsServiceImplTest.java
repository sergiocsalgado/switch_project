package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ProjectDTO;
import org.switch2022.project.mapper.ProjectDTOMapper;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListAllProjectsServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListAllProjectsServiceImpl.class)
class ListAllProjectsServiceImplTest {
    @MockBean
    Project project;
    @MockBean
    ProjectCode projectCode;
    @MockBean
    Customer customer;
    @MockBean
    CustomerID customerID;
    @MockBean
    BusinessSector businessSector;
    @MockBean
    BusinessSectorID businessSectorID;
    @MockBean
    Typology typology;
    @MockBean
    TypologyID typologyID;
    @MockBean
    ProjectDTO projectDTO;

    @MockBean
    @Qualifier("projectJPARepository")
    private Repository<ProjectCode, Project> projectRepository;
    @MockBean
    @Qualifier("customerJPARepository")
    private Repository<CustomerID, Customer> customerRepository;
    @MockBean
    @Qualifier("businessSectorJPARepository")
    private Repository<BusinessSectorID, BusinessSector> businessSectorRepository;
    @MockBean
    @Qualifier("typologyJPARepository")
    Repository<TypologyID, Typology> typologyRepository;

    @Autowired
    ListAllProjectsServiceImpl serviceUnderTest;

    /**
     * Unit Test for {@link ListAllProjectsServiceImpl#getProjects()}
     */
    @Test
    void ensureGetProjects_ensureTheListHasOneProject() {
        // Arrange
        String customerName = "customer";
        String businessSectorDescription = "business";
        String typologyDescription = "typology";

        Name customerNameDouble = mock(Name.class);
        Description businessDescriptionDouble = mock(Description.class);
        Description typologyDescriptionDouble = mock(Description.class);

        when(customerNameDouble.getValue()).thenReturn(customerName);
        when(businessDescriptionDouble.getDescription()).thenReturn(businessSectorDescription);
        when(typologyDescriptionDouble.getDescription()).thenReturn(typologyDescription);

        when(customer.getName()).thenReturn(customerNameDouble);
        when(businessSector.getBusinessSectorDescription()).thenReturn(businessDescriptionDouble);
        when(typology.getDescription()).thenReturn(typologyDescriptionDouble);

        when(project.getProjectCode()).thenReturn(projectCode);
        when(project.getCustomerID()).thenReturn(customerID);
        when(project.getBusinessSectorID()).thenReturn(businessSectorID);
        when(project.getTypologyID()).thenReturn(typologyID);

        when(customer.getCustomerID()).thenReturn(customerID);
        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);
        when(typology.getTypologyID()).thenReturn(typologyID);

        List<Project> projects = List.of(project);
        List<Customer> customers = List.of(customer);
        List<Typology> typologies = List.of(typology);
        List<BusinessSector> businessSectors = List.of(businessSector);

        when(projectRepository.findAll()).thenReturn(projects);
        when(customerRepository.findAll()).thenReturn(customers);
        when(typologyRepository.findAll()).thenReturn(typologies);
        when(businessSectorRepository.findAll()).thenReturn(businessSectors);

        List<ProjectDTO> expected = List.of(projectDTO);

        try (MockedStatic<ProjectDTOMapper> mapperDouble = Mockito.mockStatic(ProjectDTOMapper.class)) {
            mapperDouble.when(() -> ProjectDTOMapper.toDTOWithoutIDs(project, customerName, businessSectorDescription,
                    typologyDescription)).thenReturn(projectDTO);

            // Act
            List<ProjectDTO> result = serviceUnderTest.getProjects();

            // Assert
            assertEquals(expected.size(), result.size());
            assertEquals(expected, result);
        }
    }
}