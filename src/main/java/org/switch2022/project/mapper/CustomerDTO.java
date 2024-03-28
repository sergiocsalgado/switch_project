package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for a customer,
 * containing information such as customerID, name, and nif.
 */

public class CustomerDTO {
    private String customerID;
    private String name;
    private String nif;

    /**
     * Returns the customerID of CustomerDTO.
     *
     * @return customerID.
     */
    public String getCustomerID() {
        return this.customerID;
    }

    /**
     * Set the customerID of CustomerDTO.
     *
     * @param customerID the customer ID.
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * Returns the name of CustomerDTO.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of CustomerDTO.
     *
     * @param name the name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the nif of CustomerDTO.
     *
     * @return the nif.
     */
    public String getNif() {
        return this.nif;
    }

    /**
     * Set the nif of CustomerDTO.
     *
     * @param nif the nif of the customer.
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Compares two CustomerDTO objects.
     *
     * @param o the object to compare with.
     * @return true if the customerID, name and nif fields are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() == o.getClass()) {
            return customerID.equals(((CustomerDTO) o).getCustomerID())
                    && name.equals(((CustomerDTO) o).getName())
                    && nif.equals(((CustomerDTO) o).getNif());
        }
        return false;
    }

    /**
     * Returns a hash code value for this CustomerDTO object based on its customerID, name and nif.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customerID, name, nif);
    }
}