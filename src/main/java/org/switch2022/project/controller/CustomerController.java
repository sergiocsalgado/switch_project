package org.switch2022.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.CustomerDTO;
import org.switch2022.project.mapper.CustomerMapper;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.service.interfaces.CreateCustomerService;
import org.switch2022.project.service.interfaces.ListAllCustomersService;

import java.util.List;

/**
 * The CustomerController.md class is responsible for adding customers.
 */
@Controller
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CreateCustomerService customerService;
    private final ListAllCustomersService listAllCustomersService;

    /**
     * Constructs a new CustomerController.md with the specified CustomerService.
     *
     * @param customerService the CustomerService to be used for customer add and saving.
     * @param listAllCustomersService the ListAllCustomersService to be used for listing the customers.
     */

    public CustomerController(CreateCustomerService customerService, ListAllCustomersService listAllCustomersService) {
        this.customerService = customerService;
        this.listAllCustomersService = listAllCustomersService;
    }

    /**
     * Adds a customer based on the provided CustomerDTO.
     *
     * @param customerDTO the CustomerDTO containing the customer details.
     * @return the CustomerDTO representing the created and saved customer.
     * @throws IllegalArgumentException if the provided customerDTO contains invalid input parameters.
     */
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerDTO customerDTO) {

        try {
            Customer addCustomer = customerService.createAndSaveCustomer(
                    customerDTO.getCustomerID(),
                    customerDTO.getName(),
                    customerDTO.getNif()
            );

            CustomerDTO addedCustomer = CustomerMapper.toDTO(addCustomer);

            return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);

        } catch (IllegalArgumentException argumentException) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameters to add a Customer");
        }
    }

    /**
     * Retrieves all customers.
     *
     * @return ResponseEntity containing a list of customers DTOs.
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getCustomers() {
        List<CustomerDTO> customerDTOs = listAllCustomersService.getCustomers();

        return new ResponseEntity<>(customerDTOs, HttpStatus.OK);
    }
}