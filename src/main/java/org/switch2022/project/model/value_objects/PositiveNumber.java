package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public final class PositiveNumber implements ValueObject<PositiveNumber> {
    private final int number;

    public PositiveNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("it cannot be lower than zero");
        }
        this.number = number;
    }


    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() == o.getClass()) {
            return this.number == ((PositiveNumber) o).getNumber();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public boolean sameAs(PositiveNumber o) {
        return o != null
                && this.number == o.getNumber();
    }
}