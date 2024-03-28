package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class ProjectStatus implements ValueObject<ProjectStatus> {
    private final String statusDescription;

    /**
     * Constructs a ProjectStatus object with the provided status description.
     *
     * @param statusDescription the description of the project status
     */
    public ProjectStatus(String statusDescription) {
        StringValidation.checkNull("Project Status", statusDescription);

        StringValidation.checkBlank("Project Status", statusDescription);

        if (!isValidProjectStatus(statusDescription)) {
            throw new IllegalArgumentException("The status of the project is not valid (check valid status " +
                    "for more information)");
        }
        this.statusDescription = statusDescription;
    }

    /**
     * Validates that the project status matches one of the following descriptions: planned, inception,
     * elaboration, construction, transition, warranty, closed.
     *
     * @param statusDescription the description of the project status to be validated
     * @return true if the project status is valid, false otherwise
     */
    private static boolean isValidProjectStatus(String statusDescription) {
        switch (statusDescription.toLowerCase()) {
            case "planned":
            case "inception":
            case "elaboration":
            case "construction":
            case "transition":
            case "warranty":
            case "closed":
                return true;
            default:
                return false;
        }
    }

    /**
     * Returns the description of the project status.
     *
     * @return the description of the project status
     */
    public String getProjectStatus() {
        return statusDescription;
    }

    /**
     * Checks if this ProjectStatus is the same as the specified object.
     *
     * @param other the object to compare this ProjectStatus against
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean sameAs(ProjectStatus other) {
        return other != null
                && this.statusDescription.equals(other.getProjectStatus());
    }

    /**
     * Checks if this ProjectStatus is equal to the specified object.
     *
     * @param o the object to compare this ProjectStatus against
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectStatus projectStatus = (ProjectStatus) o;
        return sameAs(projectStatus);
    }

    /**
     * Returns the hash code value for this ProjectStatus.
     *
     * @return the hash code value for this ProjectStatus
     */
    @Override
    public int hashCode() {
        return Objects.hash(statusDescription);
    }
}
