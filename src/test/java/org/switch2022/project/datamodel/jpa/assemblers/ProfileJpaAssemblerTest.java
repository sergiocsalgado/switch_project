package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.jpa.ProfileJpa;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.ProfileID;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link ProfileJpaAssembler}.
 */
class ProfileJpaAssemblerTest {

    /**
     * Test private constructor for {@link ProfileJpaAssembler}.
     */
    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException,
            InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = ProfileJpaAssembler.class.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }

        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    /**
     * Test class for {@link ProfileJpaAssembler#toDataModel(Profile)}.
     */
    @Test
    void toDataModel_shouldReturnAValidProfileObjectGivenAProfileJpaObject() {
        // Arrange
        String profileID = "P1";
        String description = "user";

        ProfileJpa profileJpa = new ProfileJpa(profileID, description);

        ProfileID profileID1 = new ProfileID(profileID);
        Description description1 = new Description(description);

        Profile profile = new Profile(profileID1, description1);

        // Act
        ProfileJpa profileJpaResult = ProfileJpaAssembler.toDataModel(profile);

        // Assert
        assertEquals(profileJpa.getProfileID(), profileJpaResult.getProfileID());
        assertEquals(profileJpa.getDescription(), profileJpaResult.getDescription());
    }

    /**
     * Test class for {@link ProfileJpaAssembler#toDomain(ProfileJpa)}.
     */
    @Test
    void toDomain_shouldReturnAValidProfileJpaObjectGivenAProfile() {
        // Arrange
        String profileID = "P1";
        String description = "user";

        ProfileJpa profileJpa = new ProfileJpa(profileID, description);

        ProfileID profileID1 = new ProfileID(profileID);
        Description description1 = new Description(description);

        Profile profile = new Profile(profileID1, description1);

        // Act
        Profile profileResult = ProfileJpaAssembler.toDomain(profileJpa);

        // Assert
        assertEquals(profile.getProfileID(), profileResult.getProfileID());
        assertEquals(profile.getDescription(), profileResult.getDescription());
    }
}
