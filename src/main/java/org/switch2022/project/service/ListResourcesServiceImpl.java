package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ResourcesInProjectDTO;
import org.switch2022.project.mapper.ResourcesInProjectMapper;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.service.interfaces.ListResourcesService;

import java.util.List;
import java.util.Optional;

@Service
public class ListResourcesServiceImpl implements ListResourcesService {
    private final Repository<ProjectCode, Project> projectRepository;

    public ListResourcesServiceImpl(
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ResourcesInProjectDTO> getResourcesInProjectDTO(String projectCode_input) {
        ProjectCode projectCode;
        try {
            projectCode = new ProjectCode(projectCode_input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        Optional<Project> projectFounded = getProject(projectCode);

        if (projectFounded.isPresent()) {
            Project project = projectFounded.get();

            return ResourcesInProjectMapper.toResourcesDTOList(project.getResources(), projectCode);

        } else {
            throw new IllegalStateException("Project does not exists");
        }
    }

    private Optional<Project> getProject(ProjectCode projectCode) {
        return projectRepository.ofIdentity(projectCode);
    }


}
