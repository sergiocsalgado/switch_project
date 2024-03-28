package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for an account,
 * containing information such as email, name, phone number, status and profileID.
 */
public class AccountDTO {
    private String email;
    private String name;
    private String phoneNumber;
    private String status;

     /**
     * Returns the email of accountDTO.
     *
     * @return the email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the email of accountDTO.
     *
     * @param email the email of the account.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the name of accountDTO.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the code of accountDTO.
     *
     * @param name the name of the account.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the phone number of accountDTO.
     *
     * @return the phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *  Set the phone number of accountDTO.
     *
     * @param phoneNumber the phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the status of accountDTO.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of accountDTO.
     *
     * @param status the status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Compares two AccountDTO objects.
     *
     * @param obj the object to compare with.
     * @return true if the email, name, phone number, and status fields are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            return email.equals(((AccountDTO) obj).getEmail())
                    && name.equals(((AccountDTO) obj).getName())
                    && phoneNumber.equals(((AccountDTO) obj).getPhoneNumber())
                    && status.equals(((AccountDTO) obj).getStatus());
        }
        return false;
    }

    /**
     * Returns a hash code value for this AccountDTO object based on its email, name, phone number and status fields.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, name, phoneNumber, status);
    }
}
