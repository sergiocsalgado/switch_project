package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.EstimateEffortRequestDTO;
import org.switch2022.project.service.interfaces.EstimateEffortService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = EstimateEffortController.class)
class EstimateEffortControllerTest {
    @MockBean
    private EstimateEffortService estimateEffortService;
    @Autowired
    EstimateEffortController estimateEffortController;


    /**
     * Unit Test for addEffortInUserStory.
     */
    @Test
    void addEffortInUserStory() {
        // Arrange
        String userStoryID = "US1";
        int storyPoints = 2;

        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO.setUserStoryID(userStoryID);
        estimateEffortRequestDTO.setStoryPoints(storyPoints);

        // Mock the behavior of estimateEffortService.setEffort() method
        when(estimateEffortService.setEffort(userStoryID,storyPoints)).thenReturn(true);

        ResponseEntity<Object> expected = new ResponseEntity<>(HttpStatus.OK);

        // Act
        ResponseEntity<Object> result = estimateEffortController.setEffortToUserStory(estimateEffortRequestDTO);

        // Assert
        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }

    /**
     * Unit Test for addEffortInUserStory (failure case).
     */
    @Test
    void addEffortInUserStory_Fail() {
        // Arrange
        String userStoryID = "US1";
        int storyPoints = 2;

        EstimateEffortRequestDTO estimateEffortRequestDTO = new EstimateEffortRequestDTO();
        estimateEffortRequestDTO.setUserStoryID(userStoryID);
        estimateEffortRequestDTO.setStoryPoints(storyPoints);

        // Mock the behavior of estimateEffortService.setEffort() method
        when(estimateEffortService.setEffort(userStoryID,storyPoints)).thenReturn(false);

        ResponseEntity<Object> expected = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<Object> result = estimateEffortController.setEffortToUserStory(estimateEffortRequestDTO);

        // Assert
        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }
}

