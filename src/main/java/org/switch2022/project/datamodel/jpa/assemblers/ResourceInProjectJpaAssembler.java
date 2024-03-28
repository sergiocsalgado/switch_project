package org.switch2022.project.datamodel.jpa.assemblers;

import org.switch2022.project.datamodel.jpa.ResourceInProjectJpa;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

public final class ResourceInProjectJpaAssembler {
    private ResourceInProjectJpaAssembler(){}

    public static ResourceInProject toDomain(ResourceInProjectJpa resourceInProjectJpa) {
        return new ResourceInProject(
                new ResourceInProjectID(resourceInProjectJpa.getResourceInProjectID()),
                new Email(resourceInProjectJpa.getEmail()),
                new Role(resourceInProjectJpa.getRole()),
                new Period(DateManagement.toLocalDate(resourceInProjectJpa.getStartDate()),
                        DateManagement.toLocalDate(resourceInProjectJpa.getEndDate())),
                new Cost(resourceInProjectJpa.getCostPerHour(), resourceInProjectJpa.getCurrency()),
                new Allocation(resourceInProjectJpa.getAllocation())
        );
    }
}


