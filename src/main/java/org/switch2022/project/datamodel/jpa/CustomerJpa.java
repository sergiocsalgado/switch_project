package org.switch2022.project.datamodel.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class represents the Customer entity in the data model, mapped to the "customer" table in the database.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")

public class CustomerJpa {
    @Id
    @Column
    private String customerID;
    @Column
    private String name;
    @Column
    private String nif;

    /**
     * Constructs a CustomerJpa object with the provided customer ID, name, and NIF.
     *
     * @param customerID The customer ID.
     * @param name       The name of the customer.
     * @param nif        The NIF (tax identification number) of the customer.
     */
    public CustomerJpa(String customerID, String name, String nif) {
        this.customerID = customerID;
        this.name = name;
        this.nif = nif;
    }

    /**
     * Retrieves the customer ID.
     *
     * @return The customer ID.
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the NIF (tax identification number) of the customer.
     *
     * @return The NIF of the customer.
     */
    public String getNif() {
        return nif;
    }
}
