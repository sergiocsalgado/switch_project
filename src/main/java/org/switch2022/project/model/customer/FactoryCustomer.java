package org.switch2022.project.model.customer;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;

@Component
public interface FactoryCustomer {
    public Customer createCustomer(CustomerID customerID, Name name, Nif nif);
}
