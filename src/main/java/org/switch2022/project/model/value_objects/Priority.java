package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public final class Priority implements ValueObject<Priority> {

    private final int index;

    public Priority(int index) {
        if (index < 1) {
            throw new IllegalArgumentException("Priority cannot be lower than one");
        }
        this.index = index;
    }


    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Priority priority = (Priority) o;
        return index == priority.index;

    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    @Override
    public boolean sameAs(Priority other) {
        return other != null
                && this.index == other.getIndex();
    }
}