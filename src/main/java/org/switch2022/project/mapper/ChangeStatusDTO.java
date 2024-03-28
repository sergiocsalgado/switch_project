package org.switch2022.project.mapper;

import java.util.Objects;

/**
 * Class representing a Data Transfer Object (DTO) for change status,
 * containing information such as emailAddress and status.
 */

public class ChangeStatusDTO {
    private String emailAddress;
    private String status;

    /**
     * Returns the email address associated with this ChangeStatusDTO object.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Set the email address for this ChangeStatusDTO object.
     *
     * @param emailAddress the email address to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the status of this ChangeStatusDTO object.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status for this ChangeStatusDTO object.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Checks if this ChangeStatusDTO object is equal to another object.
     *
     * @param object the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        return emailAddress.equals(((ChangeStatusDTO) object).getEmailAddress());
    }

    /**
     * Generates a hash code value for this ChangeStatusDTO object.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }
}
