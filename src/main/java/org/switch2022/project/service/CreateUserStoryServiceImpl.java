package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.user_story.FactoryUserStory;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.interfaces.CreateUserStoryService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for registering and saving user accounts.
 */
@Service
public class CreateUserStoryServiceImpl implements CreateUserStoryService {

    private static final String STATUS_PLANNED = "planned";
    private final FactoryUserStory factoryUserStory;
    private final Repository<UserStoryID, UserStory> userStoryRepository;
    private final Repository<ProjectCode, Project> projectRepository;
    private static final int INCREMENT = 2;

    /**
     * Constructs a new RegisterAccountService with the specified dependencies.
     *
     * @param userStoryRepository The repository for managing userStories.
     * @param projectRepository   The repository for managing Projects.
     * @param factoryUserStory    The factory for creating new UserStory instances.
     */
    public CreateUserStoryServiceImpl(
            FactoryUserStory factoryUserStory,
            @Qualifier("storyJPARepository") Repository<UserStoryID, UserStory> userStoryRepository,
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository) {
        this.factoryUserStory = factoryUserStory;
        this.userStoryRepository = userStoryRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * Creates a new user story with the given parameters.
     *
     * @param code    The project code for the user story.
     * @param id      The ID of the user story.
     * @param number  The number of the user story.
     * @param name    The name of the actor associated with the user story.
     * @param text    The text description of the user story.
     * @param prior   The priority of the user story.
     * @return The created UserStory object.
     * @throws IllegalArgumentException if any of the input parameters are invalid.
     * @throws IllegalArgumentException if the user story already exists.
     * @throws IllegalStateException    if the project does not exist.
     */
    public UserStory createUserStory(String code, String id, String number,
                                     String name, String text, int prior) {
        ProjectCode projectCode;
        UserStoryID userStoryID;
        UserStoryNumber userStoryNumber;
        Name actor;
        Description userStoryText;
        UserStoryStatus userStoryStatus;
        Priority priority;

        try {
            projectCode = new ProjectCode(code);
            userStoryID = new UserStoryID(id);
            userStoryNumber = new UserStoryNumber(number);
            actor = new Name(name);
            userStoryText = new Description(text);
            userStoryStatus = new UserStoryStatus(STATUS_PLANNED);
            priority = new Priority(prior);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        if(projectRepository.containsOfIdentity(projectCode)) {
            UserStory userStory = factoryUserStory.createUserStory(
                    userStoryID,
                    projectCode,
                    userStoryNumber,
                    actor,
                    userStoryText,
                    userStoryStatus,
                    priority);

            if (addUserStory(userStory, priority, userStoryID, projectCode)) {
                return userStory;
            } else {
                throw new IllegalArgumentException("UserStory already exist");
            }

        } else {
            throw new IllegalStateException("Project does not exist.");
        }
    }

    /**
     * Adds a user story to the user story repository with the specified priority.
     *
     * @param userStory   The UserStory object to be added.
     * @param priority    The priority of the user story.
     * @param userStoryID The ID of the user story.
     * @param projectCode The project code associated with the user story.
     * @return true if the user story was successfully added, false otherwise.
     * @throws IllegalStateException if the project code does not exist.
     */
    public boolean addUserStory(UserStory userStory, Priority priority,
                                UserStoryID userStoryID, ProjectCode projectCode) {
        boolean flag = false;

        List<UserStory> userStories = userStoryRepository.findAll();

        List<UserStory> userStoriesProject = new ArrayList<>();

        for (UserStory us: userStories) {
            if (us.getProjectCode().equals(projectCode)){
                userStoriesProject.add(us);
            }

        }

        if (!userStoryRepository.findAll().contains(userStory)
                && validateUserStory(userStory,userStoriesProject)
                && !existsNumberOrIDInOtherUsInProject(userStory.getUserStoryNumber()
                        .getNumber(),
                projectCode.getProjectCode(), userStoryID.getUserStoryID())) {

            if (!projectRepository.containsOfIdentity(projectCode)) {
                throw new IllegalStateException("ProjectCode does not exist!");
            }

            if (priority.getIndex() <= userStoriesProject.size()) {
                for (int i = priority.getIndex() -1; i < userStoriesProject.size() ; i++) {

                    Priority newPriority = new Priority(i + INCREMENT);
                    UserStory us = userStoriesProject.get(i);

                    us.setPriority(newPriority);

                    userStoryRepository.save(us);
                }
                userStoryRepository.save(userStory);
                flag = userStoryRepository.containsOfIdentity(userStoryID);

            } else {
                userStoryRepository.save(userStory);
                flag = userStoryRepository.containsOfIdentity(userStoryID);
            }
            sortList();
        }
        return flag;
    }


    /**
     * Verifies if the user story is valid.
     *
     * @param userStory the user story that will be verified if all attributes are valid.
     * @param userStories the list of user stories in product backlog.
     * @return true if priority is less or equal to the list size plus one.
     */
    private static boolean validateUserStory(UserStory userStory, List<UserStory> userStories) {
        return userStory != null
                && userStory.getPriority().getIndex() <= userStories.size() + 1;
    }

    /**
     *  Verifies if the user story exists in a project checking the ID and the number.
     *
     * @param userStoryNumber the user story number.
     * @param projectCode the project code of the project.
     * @param userStoryID the ID of the user story.
     * @return true if exists, false otherwise.
     */
    private boolean existsNumberOrIDInOtherUsInProject(String userStoryNumber,
                                                        String projectCode,
                                                        String userStoryID){
        List<UserStory> allUSs = userStoryRepository.findAll();
        for (UserStory us: allUSs) {
            if ((us.getProjectCode().getProjectCode().equals(projectCode)
                    && us.getUserStoryNumber().getNumber().equals(userStoryNumber))
                    || us.getUserStoryID().getUserStoryID().equals(userStoryID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to short the UserStories by priority.
     */
    private void sortList() {
        userStoryRepository.findAll().stream()
                .sorted(Comparator.comparing(userStory -> userStory.getPriority().getIndex()))
                .collect(Collectors.toList());
    }
}
