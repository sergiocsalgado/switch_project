# US019 - As Project Manager, I want to create a sprint

## 1. Requirements Engineering
### 1.1 User Story Description
#### As Project Manager, I want to create a sprint
In order to create a sprint in a project the Project Manager needs to be in the project it belongs.
After that he has to input some data (Number of sprint, start date, and end date).

### 1.2 Customer Specifications and Clarification
From the specification document:
> *2.4.3 Record of activities of a project*
>
> The project timeline is divided into sprints (multiple of weeks), typically of a predefined
> duration, and the project team, the PO and the SM don’t change during the sprint.
> Each sprint has a “sprint backlog”, i.e., the set of US that should be addressed during the
> sprint.

2.4.7 Allocations report
> Question:
>
> "Boa tarde Angelo Martins, em relação US019 quais ou atributos necessários para a criação da Sprint? Sprint number,
> start date e project code?" <br>(asked by Cristiana Azevedo Moreira)

> Answer:
>
> n/a

### 1.3 Accepted Criteria
* Each Sprint has a unique SprintID in all existing Sprints.
* Each Sprint has a unique SprintNumber in the sprints of a specific project.
* The end date will be the one specified when the project is created.
* Dates can't overlap

### 1.4 Found out Dependencies
* In order to be able to create a sprint, the following dependencies where found:
  * *US010* As Manager, I want to register/create a new project.

### 1.5 Input and Output Data
*Input Data:*
* *Typed data:*
  * sprintNumber
  * startDate
  * endDate

*Output Data:*
* *Attributes:*
  * projectCode
  * sprintID
  * sprintNumber
  * startDate
  * endDate
  * sprintStatus

* *Message Output:*
  * Message with the **Http Status** of the result of the operation.

### 1.6 Use-Case Diagram (UCD)
[Use Case](UC019_CreateSprint.puml)

### 1.7 System Sequence Diagram (SSD)
[Sequence System Diagram](SSD019_CreateSprint.puml)


## 2. OO Analysis
### 2.1 Relevant Domain Model Excerpt
[US Relevant Domain Model](US019_RelevantDomainModelExcerpt.puml)


## 3. Design - User Story Realization
### 3.1 Rationale
| Interaction ID | Question: Which class is responsible for...                 | Answer              | Justification                                                                                                |
|----------------|-------------------------------------------------------------|---------------------|--------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                             | UI                  | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model |
| Step 2         | ... interacting with the UI layer, and coordinating sprint? | SprintController    | Controller: is responsible for receiving or handling a system operation to coordinate the sprint             |
| Step 3         | ... validate/collect data globally?                         | CreateSprintService | Pure fabrication: handle the logical part of the process                                                     |
| Step 4         | ... knowing the Projects of the system?                     | ProjectRepository   | Information Expert: have access to all projects                                                              |
| Step 5         | ... knowing the Sprints of the system?                      | SprintRepository    | Information Expert: have access to all sprints                                                               |      
| Step 6         | ... maps the data?                                          | SprintMapper        | Pure fabrication: maps the domain objects to DTOs                                                            |
| Step 7         | ... providing data to the UI?                               | SprintController    | Controller: is responsible for receiving or handling a system operation to coordinate the sprint             |
| Step 8         | ... providing data to the Actor?                            | UI                  | Information Expert: is responsible for user interactions                                                     |

### 3.2 Sequence Diagram
[Sequence Diagram](SD019_CreateSprint.puml)

### 3.3 Class Diagram
[Class Diagram](CD019_CreateSprint.puml)

## 4. Tests
* *Success - Controller test*
```java
@Test
    void createSprint_Success() {
        //Arrange
        String sprintID_input = "SP01";
        int sprintNumber_input = 1;
        String startDate_input = "2023-05-17";
        String endDate_input = "2023-06-17";
        LocalDate startDate_InputTolLocalDate = DateManagement.toLocalDate(startDate_input);
        LocalDate endDate_InputTolLocalDate = DateManagement.toLocalDate(endDate_input);
        String projectCode_input = "PRJ1";
        String status = "planned";

        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprint.getSprintNumber()).thenReturn(sprintNumber);
        when(sprint.getPeriod()).thenReturn(period);
        when(sprint.getProjectCode()).thenReturn(projectCode);

        when(sprintID.getSprintID()).thenReturn(sprintID_input);
        when(sprintNumber.getNumber()).thenReturn(sprintNumber_input);
        when(period.getStartDate()).thenReturn(startDate_InputTolLocalDate);
        when(period.getEndDate()).thenReturn(endDate_InputTolLocalDate);
        when(projectCode.getProjectCode()).thenReturn(projectCode_input);
        when(sprintStatus.getDescription()).thenReturn(sprintID_input);

        when(sprintService.createAndSaveSprint(sprintID_input, sprintNumber_input, startDate_input,
                endDate_input, projectCode_input)).thenReturn(sprint);

        SprintDTO sprintIDTO = new SprintDTO();
        sprintIDTO.setSprintID(sprintID_input);
        sprintIDTO.setSprintNumber(sprintNumber_input);
        sprintIDTO.setStartDate(startDate_input);
        sprintIDTO.setEndDate(endDate_input);
        sprintIDTO.setProjectCode(projectCode_input);
        sprintIDTO.setSprintStatus(status);

        ResponseEntity<Object> expected = new ResponseEntity<>(sprintIDTO, HttpStatus.CREATED);

        try (MockedStatic<SprintMapper> mapperDouble = Mockito.mockStatic(SprintMapper.class)) {
            mapperDouble.when(() -> SprintMapper.toDTO(sprint)).thenReturn(sprintIDTO);

            //Act
            ResponseEntity<Object> result = controllerUnderTest.createSprint(projectCode_input, sprintIDTO);

            //Assert
            assertEquals(expected, result);
            assertEquals(HttpStatus.CREATED, result.getStatusCode());
        }
    }
```

* *Fail - Controller test*
```java
@Test
    void createSprint_FailBecauseInvalidInputs() {
        String sprintID_input = " ";
        int sprintNumber_input = 1;
        String startDate_input = "20222-05-17";
        String endDate_input = "202222-06-17";
        String projectCode_input = " ";

        SprintDTO sprintIDTO = new SprintDTO();
        sprintIDTO.setSprintID(sprintID_input);
        sprintIDTO.setSprintNumber(sprintNumber_input);
        sprintIDTO.setStartDate(startDate_input);
        sprintIDTO.setEndDate(endDate_input);
        sprintIDTO.setProjectCode(projectCode_input);

        IllegalArgumentException exception =
                new IllegalArgumentException("Invalid input parameters to create a sprint.");

        when(sprintService.createAndSaveSprint(sprintID_input,
                sprintNumber_input,
                startDate_input, endDate_input,
                projectCode_input))
                .thenThrow(exception);

        ResponseEntity<Object> result = controllerUnderTest.createSprint(projectCode_input, sprintIDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(exception.getMessage(), result.getBody());
    }
```

## 5. Integration and Demo
* It will be necessary to ensure that only the *Project Manager* can create sprints.