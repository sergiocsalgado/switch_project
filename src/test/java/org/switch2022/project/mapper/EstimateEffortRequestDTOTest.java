package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.human_resource.Account;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EstimateEffortRequestDTOTest {
    /**
     * Unit Test for sameInstanceSuccess.
     * Verifies that the same instance of EstimateEffortRequestDTO is created successfully.
     */
    @Test
    void sameInstanceSuccess() {
        // Arrange
        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();

        String userStoryID = "US01";
        int storyPoints = 2;

        estimateEffortRequestDTO.setUserStoryID(userStoryID);
        estimateEffortRequestDTO.setStoryPoints(storyPoints);

        // Act

        // Assert
        assertInstanceOf(EstimateEffortRequestDTO.class, estimateEffortRequestDTO);
    }

    /**
     * Unit Test for objectNotSame.
     * Verifies that different objects are not considered the same.
     */
    @Test
    void objectNotSame() {
        // Arrange
        Account accountDouble = mock(Account.class);

        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();

        String userStoryID = "US01";
        int storyPoints = 2;

        estimateEffortRequestDTO.setUserStoryID(userStoryID);
        estimateEffortRequestDTO.setStoryPoints(storyPoints);

        // Act

        // Assert
        assertNotEquals(accountDouble, estimateEffortRequestDTO);
    }

    /**
     * Test class for {@link EstimateEffortRequestDTO#equals(Object)}.
     */
    @Test
    void ensureObjectNotEqualsNull() {

        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();

        String userStoryID = "US01";
        int storyPoints = 2;

        estimateEffortRequestDTO.setUserStoryID(userStoryID);
        estimateEffortRequestDTO.setStoryPoints(storyPoints);

        EstimateEffortRequestDTO estimateEffortRequestDTO1 = estimateEffortRequestDTO;

        EstimateEffortRequestDTO estimateEffortRequestDTO2 = null;
        assertNotEquals(estimateEffortRequestDTO1,estimateEffortRequestDTO2);
    }

    /**
     * Unit Test for equalSameObject.
     * Verifies that the same objects are considered equal.
     */
    @Test
    void equalSameObject() {
        // Arrange
        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();

        String userStoryID = "US01";
        int storyPoints = 2;

        estimateEffortRequestDTO.setUserStoryID(userStoryID);
        estimateEffortRequestDTO.setStoryPoints(storyPoints);

        EstimateEffortRequestDTO estimateEffortRequestDTO1 = estimateEffortRequestDTO;

        // Act

        // Assert
        assertEquals(estimateEffortRequestDTO, estimateEffortRequestDTO1);
        assertEquals(estimateEffortRequestDTO.hashCode(), estimateEffortRequestDTO1.hashCode());
    }



    /**
     * Unit Test for notEqualObjectNull.
     * Verifies that the object is not equal to null.
     */
    @Test
    void notEqualObjectNull() {
        // Arrange
        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();

        String userStoryID = "US01";
        int storyPoints = 2;

        estimateEffortRequestDTO.setUserStoryID(userStoryID);
        estimateEffortRequestDTO.setStoryPoints(storyPoints);

        // Act

        // Assert
        assertNotEquals(null,estimateEffortRequestDTO);
    }

    /**
     * Unit Test for equalUserStoryIDs.
     * Verifies that objects with equal user story IDs are considered equal.
     */
    @Test
    void equalUserStoryIDsSuccess() {
        // Arrange
        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO.setUserStoryID("US01");

        EstimateEffortRequestDTO estimateEffortRequestDTO1 = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO1.setUserStoryID("US01");

        // Act

        // Assert
        assertEquals(estimateEffortRequestDTO, estimateEffortRequestDTO1);
        assertEquals(estimateEffortRequestDTO.hashCode(), estimateEffortRequestDTO1.hashCode());
    }

    /**
     * Unit Test for equalStoryPoints.
     * Verifies that objects with different user story IDs are not considered equal.
     */

    @Test
    void equalStoryPoints() {
        // Arrange
        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO.setStoryPoints(2);

        EstimateEffortRequestDTO estimateEffortRequestDTO1 = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO1.setStoryPoints(2);

        // Act

        // Assert
        assertEquals(estimateEffortRequestDTO, estimateEffortRequestDTO1);
        assertEquals(estimateEffortRequestDTO.hashCode(), estimateEffortRequestDTO1.hashCode());
    }

    /**
     * Unit Test for notEqualUserStoryIDs.
     * Verifies that objects with different user story IDs are not considered equal.
     */
    @Test
    void notEqualUserStoryID() {
        // Arrange
        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO.setStoryPoints(5);
        estimateEffortRequestDTO.setUserStoryID("US01");

        EstimateEffortRequestDTO estimateEffortRequestDTO1 = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO1.setStoryPoints(5);
        estimateEffortRequestDTO.setUserStoryID("US02");

        // Act

        // Assert
        assertNotEquals(estimateEffortRequestDTO, estimateEffortRequestDTO1);
        assertNotEquals(estimateEffortRequestDTO.hashCode(), estimateEffortRequestDTO1.hashCode());
    }

    /**
     * Unit Test for notEqualStoryPoints.
     * Verifies that objects with different user story points are not considered equal.
     */
    @Test
    void notEqualStoryPoinnts() {
        // Arrange
        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO.setStoryPoints(2);
        estimateEffortRequestDTO.setUserStoryID("US01");

        EstimateEffortRequestDTO estimateEffortRequestDTO1 = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO1.setStoryPoints(5);
        estimateEffortRequestDTO.setUserStoryID("US01");

        // Act

        // Assert
        assertNotEquals(estimateEffortRequestDTO, estimateEffortRequestDTO1);
        assertNotEquals(estimateEffortRequestDTO.hashCode(), estimateEffortRequestDTO1.hashCode());
    }

    /**
     * Unit Test for notEqualDifferentClass.
     * Verifies that objects with different classes are not considered equal.
     */
    @Test
    void notEqualDifferentClass() {
        // Arrange
        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();
        ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();

        // Act

        // Assert
        assertNotEquals(estimateEffortRequestDTO,changeStatusDTO);
    }


}