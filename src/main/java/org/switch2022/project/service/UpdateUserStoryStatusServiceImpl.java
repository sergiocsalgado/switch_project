package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.user_story.FactoryUserStory;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.interfaces.UpdateUserStoryStatusService;

/**
 * Service class for updating the status of a user story.
 */
@Service
public class UpdateUserStoryStatusServiceImpl implements UpdateUserStoryStatusService {

    Repository<UserStoryID, UserStory> userStoryRepository;
    Repository<ProjectCode, Project> projectRepository;
    FactoryUserStory factoryUserStory;

    /**
     * Constructs a UpdateUserStoryStatusImpl object with the specified dependencies.
     *
     * @param userStoryRepository the repository for user story entities.
     * @param projectRepository the repository for project entities.
     * @param factoryUserStory the factory of user story.
     */
    public UpdateUserStoryStatusServiceImpl(
            @Qualifier("storyJPARepository") Repository<UserStoryID, UserStory> userStoryRepository,
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository,
            FactoryUserStory factoryUserStory) {
        this.userStoryRepository = userStoryRepository;
        this.projectRepository = projectRepository;
        this.factoryUserStory = factoryUserStory;
    }

    /**
     * Updates the status of a user story.
     *
     * @param userStoryDTO the Data Transfer Object (DTO) for a User Story.
     * @return the user story saved in the user story repository.
     */
    public UserStory updateUserStoryStatus(UserStoryDTO userStoryDTO) {
        ProjectCode projectCode;
        UserStoryID userStoryID;
        UserStoryNumber userStoryNumber;
        Name actor;
        Description userStoryText;
        UserStoryStatus userStoryStatus;
        Priority priority;

        try {
            projectCode = new ProjectCode(userStoryDTO.getProjectCode());
            userStoryID = new UserStoryID(userStoryDTO.getUserStoryID());
            userStoryNumber = new UserStoryNumber(userStoryDTO.getUserStoryNumber());
            actor = new Name(userStoryDTO.getActor());
            userStoryText = new Description(userStoryDTO.getUserStoryText());
            userStoryStatus = new UserStoryStatus(userStoryDTO.getStatus());
            priority = new Priority(userStoryDTO.getPriority());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        if(projectRepository.containsOfIdentity(projectCode)) {
            if (userStoryRepository.containsOfIdentity(userStoryID)) {
                UserStory userStory = factoryUserStory.createUserStory(
                        userStoryID,
                        projectCode,
                        userStoryNumber,
                        actor,
                        userStoryText,
                        userStoryStatus,
                        priority);

                return userStoryRepository.save(userStory);
            } else {
                throw new IllegalStateException("User Story does not exist.");
            }
        } else {
            throw new IllegalStateException("Project does not exist.");
        }
    }
}
