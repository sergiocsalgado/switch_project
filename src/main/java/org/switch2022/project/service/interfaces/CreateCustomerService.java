package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.customer.Customer;

public interface CreateCustomerService {
    Customer createAndSaveCustomer(String id, String name, String nif);
}
