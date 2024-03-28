package org.switch2022.project.datamodel.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This class represents the Account entity in the data model, mapped to the "account" table in the database.
 */
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountJpa {
    @Id
    private String email;
    private String name;
    private String phoneNumber;
    private String status;
    private String profileID;

    /**
     * Constructs an AccountJpa object with the provided email, name, phone number, status, and profile ID.
     *
     * @param email       The email of the account.
     * @param name        The name of the account.
     * @param phoneNumber The phone number of the account.
     * @param status      The status of the account.
     * @param profileID   The profile ID of the account.
     */
    public AccountJpa(String email, String name, String phoneNumber, String status, String profileID) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.profileID = profileID;
    }

    /**
     * Retrieves the email of the account.
     *
     * @return The email of the account.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the name of the account.
     *
     * @return The name of the account.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the phone number of the account.
     *
     * @return The phone number of the account.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Retrieves the status of the account.
     *
     * @return The status of the account.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Changes the status of the account.
     *
     * @param status the status to be set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the profile ID of the account.
     *
     * @return The profile ID of the account.
     */
    public String getProfileID() {
        return profileID;
    }

    /**
     * Changes the profile ID of the account.
     *
     * @param profileID the profile ID to be set.
     */
    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }
}