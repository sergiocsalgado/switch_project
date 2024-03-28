package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class Description implements ValueObject<Description> {
    private final String text;

    public Description(String text) {
        final int CHARACTER = 140;

        StringValidation.checkNull("Description", text);

        StringValidation.checkBlank("Description", text);
        if (text.length() > CHARACTER) {
            throw new IllegalArgumentException("Description cannot be more than 140 characters.");
        }
        this.text = text;
    }

    public String getDescription() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Description description = (Description) o;
        return sameAs(description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public boolean sameAs(Description o) {
        return o != null
                && this.text.equals(o.getDescription());
    }
}

