package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.EstimateEffortRequestDTO;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.repository.InMemoryUserStoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * This is a test class for the EstimateEffortServiceImpl class, which is responsible for
 * testing the behavior of the class methods using Spring Boot mock injections.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = EstimateEffortServiceImpl.class)
class EstimateEffortServiceImpTest {
    @MockBean
    UserStoryNumber userStoryNumber;
    @MockBean
    ProjectCode projectCode;
    @MockBean
    Name actor;
    @MockBean
    Description userStoryText;
    @MockBean
    UserStoryStatus userStoryStatus;
    @MockBean
    Priority priority;
    @MockBean
    @Qualifier("storyJPARepository")
    Repository<UserStoryID, UserStory> userStoryRepository;
    @MockBean
    UserStoryID userStoryIDDoule;
    @Autowired
    private EstimateEffortServiceImpl estimateEffortServiceImp;

    /**
     * Test method for setting effort successfully.
     * It verifies that the effort is set correctly for a user story when it exists.
     */
    @Test
    void setEffort_Success() {
        // Arrange
        UserStory userStory = new UserStory(userStoryIDDoule, projectCode,
                userStoryNumber, actor, userStoryText, userStoryStatus, priority);
        Optional<UserStory> userStoryOptional = Optional.of(userStory);

        // Mock the behavior of userStoryRepository.ofIdentity() method
        when(userStoryRepository.ofIdentity(any(UserStoryID.class))).thenReturn(userStoryOptional);

        // Act
        boolean result = estimateEffortServiceImp.setEffort("US01",2);

        // Assert
        assertTrue(result);
    }

    /**
     * Test method for setting effort unsuccessfully (user story not found).
     * It verifies that setting effort fails when the user story is not found.
     */
    @Test
    void setEffort_Unsuccessful() {
        // Arrange
        String userStoryID = "US01";
        int storyPoints = 2;
        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO.setUserStoryID(userStoryID);
        estimateEffortRequestDTO.setStoryPoints(storyPoints);

        Optional<UserStory> userStoryOptional = Optional.empty();// Summarize the absence of a User Story

        // Mock the behavior of userStoryRepository.ofIdentity() method
        when(userStoryRepository.ofIdentity(any(UserStoryID.class))).thenReturn(userStoryOptional);

        // Act
        boolean result = estimateEffortServiceImp.setEffort("US01",2);

        // Assert
        assertFalse(result);
    }
}
