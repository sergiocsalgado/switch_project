package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.mapper.CustomerMapper;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the ListAllProfilesServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListAllCustomersServiceImpl.class)
class ListAllCustomersServiceImplTest {


    @MockBean
    @Qualifier("customerJPARepository")
    Repository<CustomerID, Customer> customerRepository;
    @Autowired
    ListAllCustomersServiceImpl serviceUnderTest;

    /**
     * Test class for {@link ListAllCustomersServiceImpl#getCustomers()} .
     */
    @Test
    void getCustomers_ensureAllTheCustomersAreReturned() {

        Customer customer1 = mock(Customer.class);
        Customer customer2 = mock(Customer.class);

        List<Customer> customers = List.of(customer1, customer2);
        when(customerRepository.findAll()).thenReturn(customers);

        CustomerDTO profileDTO1 = mock(CustomerDTO.class);
        CustomerDTO profileDTO2 = mock(CustomerDTO.class);
        List<CustomerDTO> expectedCustomerDTOs = List.of(profileDTO1, profileDTO2);

        try (MockedStatic<CustomerMapper> mapperDouble = Mockito.mockStatic(CustomerMapper.class)) {

            mapperDouble.when(() -> CustomerMapper.listCustomerDTO(customers)).thenReturn(expectedCustomerDTOs);

            // Act
            List<CustomerDTO> customerDTOs = serviceUnderTest.getCustomers();

            // Assert
            assertEquals(expectedCustomerDTOs, customerDTOs);
        }
    }

    @Test
    void getCustomers_ensureAnEmptyListWhenThereAreNoCustomersInRepository() {
        // Arrange
        List<CustomerDTO> expected = List.of();

        // Act
        List<CustomerDTO> customerDTOs = serviceUnderTest.getCustomers();

        // Assert
        assertEquals(expected, customerDTOs);
    }
}