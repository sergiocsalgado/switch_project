package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.jpa.UserStoryJpa;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link UserStoryJpaAssembler}.
 */
class UserStoryJpaAssemblerTest {

    /**
     * Test private constructor for {@link UserStoryJpaAssembler}.
     */
    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = UserStoryJpaAssembler.class.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }

        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    /**
     * Test class for {@link UserStoryJpaAssembler#toDataModel(UserStory)}.
     */
    @Test
    void toDataModel_shouldReturnAValidUserStoryJpaObjectGivenAnUserStory() {
        // Arrange
        String userStoryID = "US1";
        String projectCode = "PRJ1";
        String number = "1";
        String actor = "actor";
        String text = "text";
        String status = "planned";
        int priority = 1;

        UserStoryID userStoryID1 = new UserStoryID(userStoryID);
        ProjectCode projectCode1 = new ProjectCode(projectCode);
        UserStoryNumber number1 = new UserStoryNumber(number);
        Name actor1 = new Name(actor);
        Description text1 = new Description(text);
        UserStoryStatus status1 = new UserStoryStatus(status);
        Priority priority1 = new Priority(priority);

        UserStory userStory = new UserStory(
                userStoryID1,
                projectCode1,
                number1,
                actor1,
                text1,
                status1,
                priority1
        );

        // Act
        UserStoryJpa userStoryJpa = UserStoryJpaAssembler.toDataModel(userStory);

        // Assert
        assertEquals(userStory.getUserStoryID().getUserStoryID(), userStoryJpa.getUserStoryID());
        assertEquals(userStory.getProjectCode().getProjectCode(), userStoryJpa.getProjectCode());
        assertEquals(userStory.getUserStoryNumber().getNumber(), userStoryJpa.getUserStoryNumber());
        assertEquals(userStory.getActor().getValue(), userStoryJpa.getActor());
        assertEquals(userStory.getUserStoryText().getDescription(), userStoryJpa.getUserStoryText());
        assertEquals(userStory.getStatus().getDescription(), userStoryJpa.getStatus());
        assertEquals(userStory.getPriority().getIndex(), userStoryJpa.getPriority());
    }

    /**
     * Test class for {@link UserStoryJpaAssembler#toDomain(UserStoryJpa)}.
     */
    @Test
    void toDomain_shouldReturnAValidUserStoryObjectGivenAnUserStoryJpa() {
        // Arrange
        String userStoryID = "US1";
        String projectCode = "PRJ1";
        String number = "1";
        String actor = "actor";
        String text = "text";
        String status = "planned";
        int priority = 1;

        UserStoryJpa userStoryJpa = new UserStoryJpa(
                userStoryID,
                projectCode,
                number,
                actor,
                text,
                status,
                priority
        );

        // Act
        UserStory userStory = UserStoryJpaAssembler.toDomain(userStoryJpa);

        // Assert
        assertEquals(userStoryJpa.getUserStoryID(), userStory.getUserStoryID().getUserStoryID());
        assertEquals(userStoryJpa.getProjectCode(), userStory.getProjectCode().getProjectCode());
        assertEquals(userStoryJpa.getUserStoryNumber(), userStory.getUserStoryNumber().getNumber());
        assertEquals(userStoryJpa.getActor(), userStory.getActor().getValue());
        assertEquals(userStoryJpa.getUserStoryText(), userStory.getUserStoryText().getDescription());
        assertEquals(userStoryJpa.getStatus(), userStory.getStatus().getDescription());
        assertEquals(userStoryJpa.getPriority(), userStory.getPriority().getIndex());
    }

}
