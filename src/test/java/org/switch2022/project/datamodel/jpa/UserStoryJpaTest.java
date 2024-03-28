package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Test class for {@link UserStoryJpa}.
 */
class UserStoryJpaTest {

    /**
     * Test class for {@link UserStoryJpa#UserStoryJpa(String, String, String, String, String, String, int)} .
     */
    @Test
    void constructor_shouldCreateAValidAccountJpa() {
        String userStoryID = "US001";
        String projectCode = "PRJ1";
        String userStoryNumber = "1";
        String actor = "Actor";
        String userStoryText = "As a user, I want to...";
        String status = "Planned";
        int priority = 1;

        UserStoryJpa userStory = new UserStoryJpa(
                userStoryID,
                projectCode,
                userStoryNumber,
                actor,
                userStoryText,
                status,
                priority
        );

        assertEquals(userStoryID, userStory.getUserStoryID());
        assertEquals(projectCode, userStory.getProjectCode());
        assertEquals(userStoryNumber, userStory.getUserStoryNumber());
        assertEquals(actor, userStory.getActor());
        assertEquals(userStoryText, userStory.getUserStoryText());
        assertEquals(status, userStory.getStatus());
        assertEquals(priority, userStory.getPriority());
    }

    /**
     * Test class for {@link UserStoryJpa#setStatus(String)} .
     * Test class for {@link UserStoryJpa#setPriority(int)} .
     * Test class for {@link UserStoryJpa#setEffort(String)} .
     */
    @Test
    public void testSettersAndGetters() {
        UserStoryJpa userStory = new UserStoryJpa();

        String status = "planned";
        userStory.setStatus(status);
        assertEquals(status, userStory.getStatus());

        int priority = 1;
        userStory.setPriority(priority);
        assertEquals(priority, userStory.getPriority());

        String effort = "1";
        userStory.setEffort(effort);
        assertEquals(effort, userStory.getEffort());
    }

    @Test
    void constructor_NoArgsConstructor() {
        // Arrange
        // Act
        UserStoryJpa userStory = new UserStoryJpa();

        // Assert
        assertInstanceOf(UserStoryJpa.class, userStory);
    }

}
