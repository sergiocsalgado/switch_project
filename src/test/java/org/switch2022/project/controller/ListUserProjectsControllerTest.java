package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.ProjectDTO;
import org.switch2022.project.service.interfaces.ListUserProjectsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Test class for {@link ListUserProjectsController}.
 */

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListUserProjectsController.class)
class ListUserProjectsControllerTest {
    @MockBean
    ListUserProjectsService listUserProjectsService;
    @Autowired
    ListUserProjectsController listUserProjectsController;

    /**
     * Unit test for the method {@link ListUserProjectsController#listAllUserProjects(String, String)}.
     */
    @Test
    void listAllUserProjects_Success() {
        //ARRANGE
        String email = "teste@gmail.com";
        String date = "2023-05-04";
        List<ProjectDTO> userProjects = mock(List.class);
        when(listUserProjectsService.listAllUserProjects(email, date)).thenReturn(userProjects);
        //ACT
        ResponseEntity<Object> result = listUserProjectsController.listAllUserProjects(email, date);

        //ASSERT
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(userProjects, result.getBody());
    }

    @Test
    void listAllUserProjects_Fail_InvalidInputs() {
        //ARRANGE
        String email = "testemail.com";
        String date = "20235-05-04";

        IllegalArgumentException exception =
                new IllegalArgumentException("Invalid input parameters to list projects where the user is present.");
        when(listUserProjectsService.listAllUserProjects(email, date)).thenThrow(exception);
        //ACT
        ResponseEntity<Object> result = listUserProjectsController.listAllUserProjects(email, date);

        //ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(exception.getMessage(), result.getBody());
    }
}






