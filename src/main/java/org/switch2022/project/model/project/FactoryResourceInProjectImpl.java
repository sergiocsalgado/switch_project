package org.switch2022.project.model.project;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.*;

@Component
public class FactoryResourceInProjectImpl implements FactoryResourceInProject {
    @Override
    public ResourceInProject create(ResourceInProjectID resourceID, Email accountID, Role role,
                                    Period period, Cost cost, Allocation allocation) {

        return new ResourceInProject(
                resourceID,
                accountID,
                role,
                period,
                cost,
                allocation);
    }
}
