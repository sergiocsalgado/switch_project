package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SprintJPATest {

    @Test
    void ensureConstructorCreatesValidSprint() {
        // Arrange
        String sprintID = "SP1";
        String projectCode = "PRJ1";
        int sprintNumber = 1;
        String startDate = "02-05-2023";
        String endDate = "25-08-2023";
        String sprintStatus = null;

        // Act
        SprintJPA sprint = new SprintJPA(
                sprintID,
                projectCode,
                sprintNumber,
                startDate,
                endDate,
                sprintStatus
        );

        // Assert
        assertEquals(sprintID, sprint.getSprintID());
        assertEquals(projectCode, sprint.getProjectCode());
        assertEquals(sprintNumber, sprint.getSprintNumber());
        assertEquals(startDate, sprint.getStartDate());
        assertEquals(endDate, sprint.getEndDate());
        assertEquals(sprintStatus, sprint.getSprintStatus());
    }

    @Test
    void ensureSetSprintStatus() {
        // Arrange
        String sprintID = "SP1";
        String projectCode = "PRJ1";
        int sprintNumber = 1;
        String startDate = "02-05-2023";
        String endDate = "25-08-2023";
        String sprintStatus = null;
        String newSprintStatus = "Open";

        // Act
        SprintJPA sprint = new SprintJPA(
                sprintID,
                projectCode,
                sprintNumber,
                startDate,
                endDate,
                sprintStatus
        );

        sprint.setSprintStatus(newSprintStatus);

        // Assert
        assertEquals(sprint.getSprintStatus(), newSprintStatus);
    }

    @Test
    void setSprintUserStories_ensureTheSprintUserStoriesAreSet() {
        // Arrange
        String sprintID = "SP1";
        String projectCode = "PRJ1";
        int sprintNumber = 1;
        String startDate = "02-05-2023";
        String endDate = "25-08-2023";
        String sprintStatus = null;

        SprintJPA sprint = new SprintJPA(
                sprintID,
                projectCode,
                sprintNumber,
                startDate,
                endDate,
                sprintStatus
        );

        List<SprintUserStoryJpa> sprintUserStoryJpas = List.of(
                new SprintUserStoryJpa("US001", sprint),
                new SprintUserStoryJpa("US02", sprint)
        );

        // Act
        sprint.setSprintUserStories(sprintUserStoryJpas);

        // Assert
        assertEquals(sprintUserStoryJpas, sprint.getSprintUserStories());
    }
}
