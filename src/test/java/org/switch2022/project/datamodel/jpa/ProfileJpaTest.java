package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Test class for {@link ProfileJpa}.
 */
class ProfileJpaTest {

    /**
     * Test class for {@link ProfileJpa#ProfileJpa(String, String)}.
     * Test method to ensure that the constructor of ProfileJpa creates a valid ProfileJpa object.
     * It verifies that the provided values are correctly assigned to the corresponding fields.
     */
    @Test
    void constructor_shouldCreateAValidProfileJpa() {
        // Arrange
        long id = 0;
        String profileID = "P1";
        String description = "user";

        // Act
        ProfileJpa profileJpa = new ProfileJpa(profileID, description);

        // Assert
        assertEquals(profileID, profileJpa.getProfileID());
        assertEquals(description, profileJpa.getDescription());
    }

    /**
     * Test method to ensure that the no-argument constructor of ProfileJpa creates a valid ProfileJpa object.
     * It verifies that an instance of ProfileJpa is created.
     */
    @Test
    void constructor_NoArgsConstructor() {
        // Arrange
        ProfileJpa profileJpa = new ProfileJpa();

        //Act + Assert
        assertInstanceOf(ProfileJpa.class, profileJpa);
    }
}