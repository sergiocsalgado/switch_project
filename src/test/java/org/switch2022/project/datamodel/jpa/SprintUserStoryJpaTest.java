package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link SprintUserStoryJpa}.
 */
class SprintUserStoryJpaTest {

    /**
     * Test class for {@link ProfileJpa#ProfileJpa(String, String)}.
     */
    @Test
    void constructor_shouldCreateAValidProfileJpa() {
        // Arrange
        SprintJPA sprint = new SprintJPA();
        String userStoryID = "US1";

        // Act
        SprintUserStoryJpa sprintUserStoryJpa = new SprintUserStoryJpa(userStoryID, sprint);

        // Assert
        assertEquals(sprint, sprintUserStoryJpa.getSprintID());
        assertEquals(userStoryID, sprintUserStoryJpa.getUserStoryID());
    }
    @Test
    void constructor_NoArgsConstructor() {
        // Arrange
        // Act
        SprintUserStoryJpa sprintUserStoryJpa = new SprintUserStoryJpa();

        // Assert
        assertInstanceOf(SprintUserStoryJpa.class, sprintUserStoryJpa);
    }

    @Test
    void setSprint_ensureTheSprintJpaGivenIsSeted() {
        // Arrange
        SprintJPA sprint1 = new SprintJPA();
        SprintJPA sprint2 = new SprintJPA();
        String userStoryID = "US1";

        SprintUserStoryJpa sprintUserStoryJpa = new SprintUserStoryJpa(userStoryID, sprint1);

        // Act
        sprintUserStoryJpa.setSprint(sprint2);

        // Assert
        assertEquals(sprint2, sprintUserStoryJpa.getSprintID());
        assertEquals(userStoryID, sprintUserStoryJpa.getUserStoryID());
    }
}
