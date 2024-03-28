package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class ResourceInProjectID implements EntityID {
    private final String resourceOfProjectID;

    public ResourceInProjectID(String resourceOfProjectID) {

        StringValidation.checkNull("Resource in Project ID", resourceOfProjectID);

        StringValidation.checkBlank("Resource in Project ID", resourceOfProjectID);

        this.resourceOfProjectID = resourceOfProjectID;
    }

    public String getResourceOfProjectID() {
        return resourceOfProjectID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ResourceInProjectID resource = (ResourceInProjectID) o;
        return sameAs(resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceOfProjectID);
    }

    @Override
    public boolean sameAs(Object other) {
        return other != null
                && this.resourceOfProjectID.equals(((ResourceInProjectID) other).getResourceOfProjectID());
    }
}