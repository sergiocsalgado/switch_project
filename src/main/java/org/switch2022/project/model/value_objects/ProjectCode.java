package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class ProjectCode implements EntityID {

    private final String code;

    public ProjectCode(String code) {
        StringValidation.checkNull("Project code", code);

        StringValidation.checkBlank("Project code", code);

        this.code = code;
    }

    public String getProjectCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectCode projectCode = (ProjectCode) o;
        return sameAs(projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean sameAs(Object other) {
        return other != null
                && this.code.equals(((ProjectCode) other).getProjectCode());
    }
}