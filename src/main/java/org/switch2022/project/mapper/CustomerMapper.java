package org.switch2022.project.mapper;

import org.springframework.lang.NonNull;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CustomerMapper {

    private CustomerMapper() {
    }

    /**
     * Maps a list of {@link Customer} objects to a list of {@link CustomerDTO} objects.
     *
     * @param customers a list of {@link Customer} objects to be mapped
     * @return a list of {@link CustomerDTO} objects
     */
    public static List<CustomerDTO> listCustomerDTO(List<Customer> customers) {
        List<CustomerDTO> customersDTO = new ArrayList<>();
        for (Customer ctmr : customers) {
            CustomerID customerID = ctmr.getCustomerID();
            Name name = ctmr.getName();
            Nif nif = ctmr.getNif();

            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerID(customerID.getIdOfCustomer());
            customerDTO.setName(name.getValue());
            customerDTO.setNif(nif.getNif());

            customersDTO.add(customerDTO);
        }
        return Collections.unmodifiableList(customersDTO);
    }

    /**
     * Retrieve a customerDTO with customerID, name and nif from a customer object.
     *
     * @param customer the customer to map to dto.
     * @return customerDTO object with customerID, name and nif attributes.
     */
    public static CustomerDTO toDTO(@NonNull Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID(customer.getCustomerID().getIdOfCustomer());
        customerDTO.setName(customer.getName().getValue());
        customerDTO.setNif(customer.getNif().getNif());

        return customerDTO;
    }
}
