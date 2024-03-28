package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.CustomerJpa;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;

public final class CustomerJpaAssembler {

    /**
     * This class provides static methods to convert between Customer and CustomerJpa objects.
     */
    private CustomerJpaAssembler() {
    }

    /**
     * Converts a Customer object to a CustomerJpa object.
     *
     * @param customer The Customer object to convert.
     * @return The converted CustomerJpa object.
     */
    public static CustomerJpa toDataModel(Customer customer) {
        return new CustomerJpa(
                customer.getCustomerID().getIdOfCustomer(),
                customer.getName().getValue(),
                customer.getNif().getNif()
        );
    }

    /**
     * Converts a CustomerJpa object to a Customer object.
     *
     * @param customerJpa The CustomerJpa object to convert.
     * @return The converted Customer object.
     */
    public static Customer toDomain(CustomerJpa customerJpa) {
        return new Customer(
                new CustomerID(customerJpa.getCustomerID()),
                new Name(customerJpa.getName()),
                new Nif(customerJpa.getNif())
        );
    }
}