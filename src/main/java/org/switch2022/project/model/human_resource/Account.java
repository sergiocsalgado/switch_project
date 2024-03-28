package org.switch2022.project.model.human_resource;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.value_objects.*;

import java.util.Objects;

public class Account implements AggregateRoot<Email> {
    Email email;
    Name name;
    PhoneNumber phoneNumber;
    AccountStatus status;
    ProfileID profile;

    public Account(Email email, Name name, PhoneNumber phoneNumber, AccountStatus status, ProfileID profile) {

        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        if (profile == null) {
            throw new IllegalArgumentException("ProfileID cannot be null");
        }

        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.profile = profile;

    }

    public Email getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public ProfileID getProfileID() {
        return profile;
    }

    public boolean setName(Name name) {
        if (name != null) {
            this.name = name;
            return name.equals(this.getName());
        }

        return false;
    }

    public boolean setPhoneNumber(PhoneNumber phoneNumber) {
        if (phoneNumber != null) {
            this.phoneNumber = phoneNumber;
            return phoneNumber.equals(this.getPhoneNumber());
        }

        return false;
    }

    public boolean setStatus(AccountStatus status) {
        if (status != null) {
            this.status = status;
            return status.equals(this.getStatus());
        }

        return false;
    }

    public boolean setProfile(ProfileID profile) {
        if (profile != null) {
            this.profile = profile;
            return profile.equals(this.getProfileID());
        }

        return false;
    }

    public boolean verifyEmail(Email email) {
        return this.email.equals(email);
    }

    public Account copy() {
        return new Account(this.email, this.name, this.phoneNumber, this.status, this.profile);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Account account = (Account) obj;

        return sameIDAs(account.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public Email identity() {
        return email;
    }

    @Override
    public boolean sameIDAs(Object object) {
        return object != null && object.equals(email);
    }

}
