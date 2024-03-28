package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.mapper.ProfileDTO;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;
import org.switch2022.project.service.interfaces.CreateCustomerService;
import org.switch2022.project.service.interfaces.ListAllCustomersService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the CustomerController class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CustomerController.class)
class CustomerControllerTest {

    @MockBean
    Customer customer;

    @MockBean
    CustomerID customerID;

    @MockBean
    Name name;

    @MockBean
    Nif nif;

    @MockBean
    CreateCustomerService createCustomerService;
    @MockBean
    ListAllCustomersService listAllCustomersService;

    @Autowired
    CustomerController customerController;

    /**
     * Unit Test for {@link CustomerController#addCustomer(CustomerDTO)}.
     */
    @Test
    void ensureCustomerIsAdded_ExpectTrue() {
        //Arrange
        String customerID0 = "costumer1";
        String name0 = "Adidas";
        String nif0 = "240606019";

        when(customer.getCustomerID()).thenReturn(customerID);
        when(customer.getName()).thenReturn(name);
        when(customer.getNif()).thenReturn(nif);

        when(customerID.getIdOfCustomer()).thenReturn(customerID0);
        when(name.getValue()).thenReturn(name0);
        when(nif.getNif()).thenReturn(nif0);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(customerID0);
        customerDTO.setName(name0);
        customerDTO.setNif(nif0);

        when(createCustomerService.createAndSaveCustomer(customerID0, name0, nif0)).thenReturn(customer);

        ResponseEntity<Object> expected = new ResponseEntity<>(customerDTO, HttpStatus.CREATED);

        //ACT
        ResponseEntity<Object> result = customerController.addCustomer(customerDTO);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Unit Test for {@link CustomerController#addCustomer(CustomerDTO)}.
     */
    @Test
    void ensureCustomerIsNotAddedDueToInvalidParameters_ExpectFalse() {
        //Arrange
        String customerID0 = null;
        String name0 = "Adidas";
        String nif0 = "240606019";

        when(customer.getCustomerID()).thenReturn(customerID);
        when(customer.getName()).thenReturn(name);
        when(customer.getNif()).thenReturn(nif);

        when(customerID.getIdOfCustomer()).thenReturn(customerID0);
        when(name.getValue()).thenReturn(name0);
        when(nif.getNif()).thenReturn(nif0);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(customerID0);
        customerDTO.setName(name0);
        customerDTO.setNif(nif0);

        IllegalArgumentException exception = new IllegalArgumentException("Invalid parameters to add a Customer");

        when(createCustomerService.createAndSaveCustomer(customerID0, name0, nif0)).thenThrow(exception);

        //ACT
        ResponseEntity<Object> result = customerController.addCustomer(customerDTO);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(exception.getMessage(), result.getBody());
    }

    /**
     * Unit Tests class for {@link CustomerController#getCustomers()} .
     */
    @Test
    public void getProfiles_ensureReturnAnEmptyListWhenThereAreNotProfilesInRepository() {
        // Arrange
        List<CustomerDTO> customerDTOs = List.of();
        when(listAllCustomersService.getCustomers()).thenReturn(customerDTOs);

        ResponseEntity<Object> expected = new ResponseEntity<>(customerDTOs, HttpStatus.OK);

        // Act
        ResponseEntity<Object> result = customerController.getCustomers();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void getProfiles_ensureReturnAListWithOneProfileWhenThereAreOnlyOneProfileInRepository() {
        // Arrange
        CustomerDTO customerDTO = mock(CustomerDTO.class);
        List<CustomerDTO> customerDTOs = List.of(customerDTO);
        when(listAllCustomersService.getCustomers()).thenReturn(customerDTOs);

        ResponseEntity<Object> expected = new ResponseEntity<>(customerDTOs, HttpStatus.OK);

        // Act
        ResponseEntity<Object> result = customerController.getCustomers();

        // Assert
        assertEquals(expected, result);
    }
}