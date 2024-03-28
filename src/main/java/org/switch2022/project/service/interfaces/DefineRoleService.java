package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.project.ResourceInProject;

public interface DefineRoleService {

    ResourceInProject defineRole(String projectCode,
                                 String resourceInProjectID,
                                 String email,
                                 String role,
                                 Double allocation,
                                 Double costPerHour,
                                 String currency,
                                 String startDate,
                                 String endDate);
}
