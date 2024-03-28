package org.switch2022.project.model.customer;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;

import java.util.Objects;

public class Customer implements AggregateRoot<CustomerID> {
    private final Name name;
    private final CustomerID customerID;
    private final Nif nif;

    public Customer(CustomerID customerID, Name name, Nif nif) {

        if (customerID == null) {
            throw new IllegalArgumentException("CustomerID can not be null");
        }
        this.customerID = customerID;

        if (name == null) {
            throw new IllegalArgumentException("Name can not be null");
        }
        this.name = name;

        if (nif == null) {
            throw new IllegalArgumentException("Nif can not be null");
        }
        this.nif = nif;
    }

    public boolean verifyName(Name name) {
        return this.name.equals(name);
    }

    public Customer copy() {
        return new Customer(this.customerID, this.name, this.nif);
    }

    public Name getName() {
        return this.name;
    }

    public Nif getNif() {
        return nif;
    }

    public CustomerID getCustomerID() {
        return this.customerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return sameIDAs(customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public CustomerID identity() {
        return customerID;
    }

    @Override
    public boolean sameIDAs(Object object) {
        if (object instanceof Customer) {
            Customer customer = (Customer) object;
            return this.name.equals(customer.getName())
                    && this.nif.equals(customer.getNif());
        }
        return false;
    }
}
