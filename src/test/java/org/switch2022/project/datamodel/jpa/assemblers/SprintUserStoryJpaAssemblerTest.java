package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.jpa.SprintJPA;
import org.switch2022.project.datamodel.jpa.SprintUserStoryJpa;
import org.switch2022.project.model.value_objects.UserStoryID;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link SprintUserStoryJpaAssembler}.
 */
class SprintUserStoryJpaAssemblerTest {


    /**
     * Test private constructor for {@link SprintUserStoryJpaAssembler}.
     */
    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException,
            InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = SprintUserStoryJpaAssembler.class.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }

        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    /**
     * Test class for {@link SprintUserStoryJpaAssembler#toDataModel(String, SprintJPA)} .
     */
    @Test
    void toDataModel_ensureTheSprintJpaIsCreatedFromTheGivenSprint() {

        String userStoryId = "US1";

        String sprintIDValue = "SP1";
        String projectCodeValue = "PRJ1";
        int sprintNumberValue = 1;
        String startDateValue = "2023-05-30";
        String endDateValue = "2023-06-15";
        String sprintStatus = null;

        SprintJPA sprintJPA = new SprintJPA(sprintIDValue, projectCodeValue, sprintNumberValue,
                startDateValue, endDateValue, sprintStatus);

        SprintUserStoryJpa expected = new SprintUserStoryJpa(userStoryId, sprintJPA);

        // Act
        SprintUserStoryJpa result = SprintUserStoryJpaAssembler.toDataModel(userStoryId, sprintJPA);

        // Assert
        assertEquals(expected.getSprintID(), result.getSprintID());
        assertEquals(expected.getUserStoryID(), result.getUserStoryID());
    }
    /**
     * Test class for {@link SprintUserStoryJpaAssembler#toDomain(SprintUserStoryJpa)}  .
     */
    @Test
    void toDomain_ensureTheUserStoryIDIsCreatedFromTheGivenSprintUserStoryJpa() {
        String userStoryId = "US1";

        String sprintIDValue = "SP1";
        String projectCodeValue = "PRJ1";
        int sprintNumberValue = 1;
        String startDateValue = "2023-05-30";
        String endDateValue = "2023-06-15";
        String sprintStatus = null;

        SprintJPA sprintJPA = new SprintJPA(sprintIDValue, projectCodeValue, sprintNumberValue,
                startDateValue, endDateValue, sprintStatus);

        SprintUserStoryJpa storyJpa = new SprintUserStoryJpa(userStoryId, sprintJPA);

        // Act
        UserStoryID result = SprintUserStoryJpaAssembler.toDomain(storyJpa);

        // Assert
        assertEquals(storyJpa.getUserStoryID(), result.getUserStoryID());
    }
}
