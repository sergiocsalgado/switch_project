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
import org.switch2022.project.utils.DateManagement;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListUserProjectsServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListUserProjectsServiceImpl.class)
class ListUserProjectsServiceTest {
    @MockBean
    Project project;
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
    private Repository<TypologyID, Typology> typologyRepository;

    @Autowired
    ListUserProjectsServiceImpl serviceUnderTest;

    /**
     * Unit Test for {@link ListUserProjectsServiceImpl#listAllUserProjects(String, String)}
     */
    @Test
    void listAllUserProjects_ensureTheUserIsInOneProject() {
        //Arrange
        String emailReceivedInController = "teste@gmail.com";
        String dateReceivedInController = "2023-05-04";
        String customerValue = "customer";
        String typologyValue = "typology";
        String businessSect = "business";

        Name customerName = mock(Name.class);
        Description typologyDescription = mock(Description.class);
        Description bsDescription = mock(Description.class);

        when(customer.getCustomerID()).thenReturn(customerID);
        when(customer.getName()).thenReturn(customerName);
        when(customerName.getValue()).thenReturn(customerValue);

        when(typology.getTypologyID()).thenReturn(typologyID);
        when(typology.getDescription()).thenReturn(typologyDescription);
        when(typologyDescription.getDescription()).thenReturn(typologyValue);

        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);
        when(businessSector.getBusinessSectorDescription()).thenReturn(bsDescription);
        when(bsDescription.getDescription()).thenReturn(businessSect);

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(businessSectorRepository.findAll()).thenReturn(List.of(businessSector));
        when(typologyRepository.findAll()).thenReturn(List.of(typology));
        when(projectRepository.findAll()).thenReturn(List.of(project));

        when(project.resourceIsInProject(new Email(emailReceivedInController),
                DateManagement.toLocalDate(dateReceivedInController))).thenReturn(true);

        when(project.getCustomerID()).thenReturn(customerID);
        when(project.getTypologyID()).thenReturn(typologyID);
        when(project.getBusinessSectorID()).thenReturn(businessSectorID);

        List<ProjectDTO> expected = List.of(projectDTO);

        try (MockedStatic<ProjectDTOMapper> mapperDouble = Mockito.mockStatic(ProjectDTOMapper.class)) {
            mapperDouble.when(() -> ProjectDTOMapper.toDTOWithoutIDs(project, customerValue, businessSect,
                    typologyValue)).thenReturn(projectDTO);

            // Act
            List<ProjectDTO> result = serviceUnderTest.
                    listAllUserProjects(emailReceivedInController, dateReceivedInController);

            // Assert
            assertEquals(expected.size(), result.size());
            assertEquals(expected, result);
        }
    }

    @Test
    void listAllUserProjects_DontExist_EmptyList() {
        //Arrange
        String emailReceivedInController = "teste@gmail.com";
        String dateReceivedInController = "2023-05-04";
        String customerValue = "customer";
        String typologyValue = "typology";
        String businessSect = "business";

        Name customerName = mock(Name.class);
        Description typologyDescription = mock(Description.class);
        Description bsDescription = mock(Description.class);

        when(customer.getCustomerID()).thenReturn(customerID);
        when(customer.getName()).thenReturn(customerName);
        when(customerName.getValue()).thenReturn(customerValue);

        when(typology.getTypologyID()).thenReturn(typologyID);
        when(typology.getDescription()).thenReturn(typologyDescription);
        when(typologyDescription.getDescription()).thenReturn(typologyValue);

        when(businessSector.getBusinessSectorID()).thenReturn(businessSectorID);
        when(businessSector.getBusinessSectorDescription()).thenReturn(bsDescription);
        when(bsDescription.getDescription()).thenReturn(businessSect);

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(businessSectorRepository.findAll()).thenReturn(List.of(businessSector));
        when(typologyRepository.findAll()).thenReturn(List.of(typology));
        when(projectRepository.findAll()).thenReturn(List.of(project));

        when(project.resourceIsInProject(new Email(emailReceivedInController),
                DateManagement.toLocalDate(dateReceivedInController))).thenReturn(false);

        List<ProjectDTO> expected = Collections.emptyList();

        //ACT
        List<ProjectDTO> result = serviceUnderTest
                .listAllUserProjects(emailReceivedInController, dateReceivedInController);

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void listAllUserProjects_Unsuccessful_NullEmail_EmptyList() {
        String date = "2023-05-04";

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceUnderTest.listAllUserProjects(null, date);
        });

        //Assert
        assertEquals("Email cannot be null", exception.getMessage());
    }
}
