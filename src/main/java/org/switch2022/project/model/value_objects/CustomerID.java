package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class CustomerID implements EntityID {
    private static final int MAX_CUSTOMER_CHAR = 20;
    private final String idOfCustomer;

    public CustomerID(String idOfCustomer) {

        StringValidation.checkNull("Customer ID", idOfCustomer);

        StringValidation.checkBlank("Customer ID", idOfCustomer);

        if (idOfCustomer.length() > MAX_CUSTOMER_CHAR) {
            throw new IllegalArgumentException("Customer ID cannot be more than 20 characters");
        }

        this.idOfCustomer = idOfCustomer;
    }

    public String getIdOfCustomer() {
        return idOfCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerID customerID = (CustomerID) o;
        return sameAs(customerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfCustomer);
    }

    @Override
    public boolean sameAs(Object other) {
        return other != null
                && this.idOfCustomer.equals(((CustomerID) other).getIdOfCustomer());
    }
}