package org.switch2022.project.model.profile;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * This class contains unit tests for the {@link Profile} class.
 */
public class ProfileTest {

    /**
     * Unit Test for verifying that an exception is thrown when the profile ID is null.
     */
    @Test
    void ensureExceptionIsThrownWhenProfileIDIsNull() {
        // ARRANGE
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Description description = mock(Description.class);
            new Profile(null, description);
        });

        // ASSERT
        assertEquals("ProfileID cannot be null", exception.getMessage());
    }

    /**
     * Unit Test for verifying that an exception is thrown when the profile description is null.
     */
    @Test
    void ensureExceptionIsThrownWhenDescriptionIsNull() {
        // ARRANGE
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            ProfileID profileID = mock(ProfileID.class);
            new Profile(profileID, null);
        });

        // ASSERT
        assertEquals("The profile description cannot be null", exception.getMessage());
    }

    /**
     * Unit Test for verifying that the copy method returns the same object.
     */
    @Test
    void ensureCopyReturnsTheSameObject() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile expected = profile;

        // ACT
        Profile result = profile.copy();

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Unit Test for verifying that {@link Profile#getProfileID()} returns the correct profile ID.
     */
    @Test
    void ensureGetProfileIDReturnsTheRightProfileID() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        ProfileID expected = profileID;

        // ACT
        ProfileID result = profile.getProfileID();

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Unit Test for verifying that {@link Profile#getDescription()} returns the correct description.
     */
    @Test
    void ensureGetDescriptionReturnsTheRightDescription() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Description expected = description;

        // ACT
        Description result = profile.getDescription();

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Unit Tests for verifying the behavior of the {@link Profile#equals(Object)} method.
     */
    @Test
    void testEquals_SameObject_ReturnsTrue() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        boolean expected = true;

        // ACT
        boolean result = profile.equals(profile);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void testEquals_NullObject_ReturnsFalse() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile profile1 = null;
        boolean expected = false;

        // ACT
        Boolean result = profile.equals(profile1);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void testEquals_DifferentClass_ReturnsFalse() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        String differentClassObject = "Not a Profile object";
        boolean expected = false;

        // ACT
        boolean result = profile.equals(differentClassObject);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void testEquals_SameProfileIDAndDescription_ReturnsTrue() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile profile2 = new Profile(profileID, description);
        boolean expected = true;

        // ACT
        boolean result1 = profile.equals(profile2);
        boolean result2 = profile2.equals(profile);

        // ASSERT
        assertEquals(expected, result1);
        assertEquals(expected, result2);
    }

    @Test
    void returnFalseWhenObjectIsDifferent() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Object object = mock(Object.class);
        boolean expected = false;

        // ACT
        boolean isEquals = profile.equals(object);

        // ASSERT
        assertEquals(expected, isEquals);
    }

    @Test
    void returnFalseWhenObjectIsNull() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile profile1 = mock(Profile.class);
        boolean expected = false;

        // ACT
        boolean result = profile.equals(profile1);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void returnTrueWhenObjectIsTheSame() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile profile1 = new Profile(profileID, description);
        boolean expected = true;

        // ACT
        boolean isEquals = profile.equals(profile1);

        // ASSERT
        assertEquals(expected, isEquals);
    }

    /**
     * Unit Tests for verifying the behavior of the {@link Profile#hashCode()} method.
     */
    @Test
    void ensureHashCodeIsTheSameForSameObjects() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile profile1 = new Profile(profileID, description);

        // ASSERT
        assertEquals(profile.hashCode(), profile1.hashCode());
    }

    @Test
    void ensureHashCodeReturnsDifferentValuesForDifferentObjects() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        ProfileID profileID1 = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile profile1 = new Profile(profileID1, description);

        // ASSERT
        assertNotEquals(profile.hashCode(), profile1.hashCode());
    }

    @Test
    void ensureHashCodeReturnsDifferentValuesForDifferentDescriptions() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Description description1 = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile profile1 = new Profile(profileID, description1);

        // ASSERT
        assertNotEquals(profile.hashCode(), profile1.hashCode());
    }

    /**
     * Unit Tests for verifying the behavior of the {@link Profile#identity()} method.
     */
    @Test
    void ensureIdentityReturnsTheRightProfileID() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        ProfileID expected = profileID;

        // ACT
        ProfileID result = profile.identity();

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureIdentityReturnsFalseWhenProfileIDIsDifferent() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        ProfileID profileID1 = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        ProfileID expected = profileID1;

        // ACT
        ProfileID result = profile.identity();

        // ASSERT
        assertNotEquals(expected, result);
    }

    /**
     * Unit Tests for verifying the behavior of the {@link Profile#sameIDAs(Object)} method.
     */
    @Test
    void ensureSameIDAsReturnsTrueWhenObjectsAreTheSame() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile profile1 = new Profile(profileID, description);

        // ACT
        boolean result = profile.sameIDAs(profile1);

        // ASSERT
        assertTrue(result);
    }

    @Test
    void ensureSameIDAsReturnsFalseWhenIDIsDifferent() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        ProfileID profileID1 = mock(ProfileID.class);
        Description description = mock(Description.class);
        Description description1 = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        Profile profile1 = new Profile(profileID1, description1);

        // ACT
        boolean result = profile.sameIDAs(profile1);

        // ASSERT
        assertFalse(result);
    }

    @Test
    void ensureSameIDAsReturnsFalseWhenObjectsAreDifferent() {
        // ARRANGE
        ProfileID profileID = mock(ProfileID.class);
        Description description = mock(Description.class);
        Profile profile = new Profile(profileID, description);
        boolean expected = false;

        // ACT
        boolean result = profile.sameIDAs(profileID);

        // ASSERT
        assertEquals(expected, result);
    }
}
