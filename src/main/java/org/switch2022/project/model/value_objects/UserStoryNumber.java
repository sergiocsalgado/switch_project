package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public final class UserStoryNumber implements ValueObject<UserStoryNumber> {
    private final String number;

    public UserStoryNumber(String number) {
        if (!number.matches("^[1-9]\\d*$")) {
            throw new IllegalArgumentException("UserStoryNumber must be a " +
                    "positive number");
        }
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryNumber userStoryNumber = (UserStoryNumber) o;
        return Objects.equals(number, userStoryNumber.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public boolean sameAs(UserStoryNumber other) {
        return other != null
                && this.number.equals((other).getNumber());
    }
}
