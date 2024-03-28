package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class AccountName implements ValueObject<AccountName> {
    private static final int MAX_CHARACTERS = 40;
    private final String name;

    public AccountName(String name) {
        StringValidation.checkNull("Account Name", name);

        StringValidation.checkBlank("Account Name", name);

        if (name.matches(".*\\d+.*")) {
            throw new IllegalArgumentException("Name cannot have numbers");
        }

        if (name.length() > MAX_CHARACTERS) {
            throw new IllegalArgumentException("Name cannot exceed 40 characters");
        }
        this.name = name;
    }

    public String getAccountName() {
        return name;
    }

    @Override
    public boolean sameAs(AccountName other) {
        return other != null
                && this.name.equals(other.getAccountName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        AccountName accountName = (AccountName) o;
        return sameAs(accountName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
