# US021 - As Team Member, I Want to Estimate the Effort of a User Story in a Sprint, during the Sprint Planning Ceremony

## 1. Requirements Engineering

### 1.1 User Story Description

#### As Team Member, I Want to Estimate the Effort of a User Story in a Sprint, during the Sprint Planning Ceremony

### 1.2 Customer Specifications and Clarification

*From the specification document:*

Each sprint has a “sprint backlog”, i.e., the set of US that should be addressed during the
sprint. The definition of the sprint backlog is a joint effort of the PO and the team, during the 
sprint planning ceremony. In this ceremony, an effort estimate is defined for each US, only
applicable to that specific sprint, so that it is possible to estimate the total effort in the sprint.

Effort estimate (start by having the initial estimate, but can be updated several times
throughout the project; uses Fibonacci series for duration in hours.);


*From client clarification:*

   Relativamente à US021, o esforço é em pontos do scrum poker (sequência de Fibbonacci adaptada, limitada a 40 pontos).
   Não vamos pensar, pelo menos para já, na estimativa em tempo. (by Angelo Martins)

### 1.3 Accepted Criteria
  * To estimate the effort of a User Story in a Sprint has to exist a project, a Sprint and a User Story.

### 1.4 Found out Dependencies

* In order to be able to add a resource to a project, the following dependencies where found:
    * US017 - As Product Owner, I want to create a user story and add it to the ProductBacklog.
    * US019 - As Project Manager, I want to create a sprint
    * US020 - As Team Member, I want to add a user story in the product backlog to the sprint backlog

### 1.5 Input and Output Data

*Input Data:*

 * effort
 * projectCode
 * sprintID
 * userStoryNumber

*Output Data:*

* Success of the operation.

### 1.6 System Sequence Diagram (SSD)

[SSD021_EstimateEffortOfUserStory.puml](SSD021_EstimateEffortOfUserStory.puml)

### 1.7 Use Case Diagram (UCD)

[UCD021_EstimateEffortOfUserStory.puml](UCD021_EstimateEffortOfUserStory.puml)


## 2. OO Analysis

### 2.1 Relevant Domain Model Excerpt

[DM021_Domain Model Excerpt.puml](DM021_Domain%20Model%20Excerpt.puml)


## 3. Design - User Story Realization

### 3.1 Rationale

| Interaction ID | Question: Which class is responsible for...                                   | Answer                   | Justification (with patterns)                                                                       |
|----------------|-------------------------------------------------------------------------------|--------------------------|-----------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                                               | EstimateEffortUI         | Pure fabrication                                                                                    |
| Step 2         | ... interacting with the UI layer?                                            | EstimateEffortController | Controller is responsible for receiving or handling a system operation to coordinate the user story |
| Step 3         | ... interacting with the controller?                                          | ProjectContainer         | Information Expert: have access to all projects                                                     |
|                | ... interacting with the controller?                                          | Project                  | Information Expert: have access to sprintContainer                                                  |
|                | ... interacting with the controller?                                          | SprintContainer          | Information Expert: have access to all sprints                                                      |                                                                                                            |
| Step 4         | ... interacting with the Sprint?                                              | SprintBacklog            | Information Expert: have access to all userStories                                                  | ... knowing the database?                           | ProjectContainer and SprintContainer             | Information Expert: knows/has all Projects and Sprints                                                                       |
| Step 5         | ... add estimated effort a User Story in a Sprint?                            | UserStory                | Creator                                                                                             |
| Step 6         | ... providing estimated effort added to the UI?                               | EstimateEffortController | Controller: is responsible for sending the operation result                                         |
| Step 7         | ... exhibit estimated effort added of a User Story in a Sprint to the Actor ? | EstimateEffortUI         | Information Expert: is responsible for user interactions                                            |


### 3.2 Sequence Diagram

[SD021_EstimateEffortOfUserStory.puml](SD021_EstimateEffortOfUserStory.puml)

### 3.3 Class Diagram

[UCD021_EstimateEffortOfUserStory.puml](UCD021_EstimateEffortOfUserStory.puml)

## 4. Tests

* *Unit Test - Success*

        /**
        * Unit test for class {@link EstimateEffortController#setEffortToUserStory(String, Effort, String, String)}.
        */ 
        @Test
        void setEffortToUserStory_UnitTest_Success() {
        //Arrange

        //Create ProjectContainer Double
        ProjectContainer projectContainerDouble = mock(ProjectContainer.class);

        //Create EstimateEffortController and Add ProjectContainer Double
        EstimateEffortController estimateEffortController = new EstimateEffortController(projectContainerDouble);

        //Create SprintContainer Double
        SprintContainer sprintContainerDouble = mock(SprintContainer.class);

        //Create Sprint Double
        Sprint sprintDouble = mock(Sprint.class);

        //Create Project Double
        Project projectDouble = mock(Project.class);

        //Add Behavior to Double
        when(projectContainerDouble.getProject("02")).thenReturn(projectDouble);

        when(projectDouble.getSprintContainer()).thenReturn(sprintContainerDouble);

        when(sprintContainerDouble.getSprint("02")).thenReturn(sprintDouble);

        when(sprintDouble.setUSEffort("02", Effort.TWO)).thenReturn(true);

        //Act
        boolean result = estimateEffortController.setEffortToUserStory("02", Effort.TWO, "02",
                    "02");

        //Assert
        assertTrue(result);
        }

* *Unit Test - Fail*
        
      /**
      * Unit test for class {@link EstimateEffortController#setEffortToUserStory(String, Effort, String, String)}.
      */
      @Test
      void setEffortToUserStory_UnitTest_UserStoryNumber_Fail() {
      //Arrange

      //Create ProjectContainer Double
      ProjectContainer projectContainerDouble = mock(ProjectContainer.class);

      //Create EstimateEffortController and Add ProjectContainer Double
      EstimateEffortController estimateEffortController = new EstimateEffortController(projectContainerDouble);

      //Create SprintContainer Double
      SprintContainer sprintContainerDouble = mock(SprintContainer.class);


      //Create Sprint Double
      Sprint sprintDouble = mock(Sprint.class);

      //Create Project Double
      Project projectDouble = mock(Project.class);

      //Add Behavior to Double
      when(projectContainerDouble.getProject("02")).thenReturn(projectDouble);

      when(projectDouble.getSprintContainer()).thenReturn(sprintContainerDouble);

      when(sprintContainerDouble.getSprint("02")).thenReturn(sprintDouble);

      when(sprintDouble.setUSEffort("02", Effort.TWO)).thenReturn(true);

      //Act
      boolean result = estimateEffortController.setEffortToUserStory("01", Effort.TWO, "02", "02");

      //Assert
      assertFalse(result);
      }

## 5. Integration and Demo

* n/a

## 6. Observations

* n/a
