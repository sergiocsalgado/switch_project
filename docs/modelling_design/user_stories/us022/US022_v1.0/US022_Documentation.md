# US022 - As Team Member, I want to view the Scrum Board of current sprint.

## 1. Requirements Engineering

### 1.1 User Story Description

For this user story, the actor (Team Member) through the unique code of
a specific project will be able to view the scrum board, i.e., a list of the user stories that are
in "planned", "running" and "finished" status at that current sprint.

### 1.2 Customer Specifications and Clarification

>*From the specification document:*

*2.4.6 View the status of activities in the project*

The project activity list should have two distinct viewing modes, which can be used interchangeably or
simultaneously:
- Table-shaped view;
- View in Gantt format (by sprint).


>*From client clarification:*

####   Question:
- Em relação à US022, no Scrum Board devem constar as User Stories de determinado projeto 
no sprint atual ou devem constar as Tasks de determinado projeto no sprint atual?
- 

####   Answer:
Parece-me importante haver uma ferramenta que permita saber e alterar o estado de cada user story no sprint. 
O scrum board parece ser o candidato óbvio.

Vai haver tarefas que estão diretamente associadas a user stories e outras não. Dá jeito ter uma ferramenta 
para controlo do estado das tarefas? Concordo que sim.
Cria-se um task board em paralelo com o scrum board? Provavelmente não. Ferramentas a mais são uma distração.
É possível misturar tarefas e user stories num mesmo scrum board? Porque não? O scrum board é uma representação 
de estados. Se eles são os mesmos.


####   Question:
Nesta sprint deveremos implementar também as tasks ou apenas as user stories?

####   Answer:
Não receberam qualquer user story sobre tasks, pelo que me parece extremamente difícil 
que possam criar tasks nesta altura. Seria pura adivinhação, e adivinhar requisitos é um erro. .


####   Question:
"Parece-me importante haver uma ferramenta que permita saber e alterar o estado de cada user story no sprint. 
O scrum board parece ser o candidato óbvio."

Implementar já também funcionalidade que permita alterar estado das US no sprint atual? Ou apenas
funcionalidade que permita saber qual é esse estado?

####   Answer:
Só devem implementar as user stories que estão no sprint backlog.


### 1.3 Acceptance Criteria

The information about the user stories of the scrum board should be displayed in two 
distinct viewing modes which can be used interchangeably or simultaneously, such as:
- Table-shaped view;
- View in Gantt format (by sprint).


### 1.4 Found out Dependencies

* In order to be able to view the scrum board the following dependencies where found:
    * *US010* - "As Manager, I want to register/create a new project."
    * *US017* - "As Product Owner, I want to create a user story and add it to the Product Backlog."
    * *US019* - "As Project Manager, I want to create a sprint."
    * *US020* - "As Team Member, I want to add a user story in the product backlog to the sprint backlog,
  during the sprint planning ceremony."

### 1.5 Input and Output Data

*Input Data:*
* Project code.

*Output Data:*
* List of user stories of the sprint backlog that are in "planned", "running" and "finished" status.
* (In)Success of the operation.

### 1.6 Use-Case Diagram (UCD)
[UCD022_ListScrumBoard.puml](UCD022_ViewScrumBoard.puml)

### 1.7 System Sequence Diagram (SSD)
[SSD022_ListScrumBoard.puml](SSD022_ViewScrumBoard.puml)


## 2. OO Analysis
### 2.1 Relevant Domain Model Excerpt
[DM022_ViewScrumBoard.puml](DM022_ViewScrumBoard.puml)

## 3. Design - User Story Realization
### 3.1 Rationale

| Interaction ID | Question: Which class is responsible for...                        | Answer                     | Justification                                                                                                                            |
|----------------|--------------------------------------------------------------------|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                                    | ViewScrumBoardUI           | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                            |
| Step 2         | ... interacting with the UI layer and coordinating the user story? | ViewScrumBoardUIController | The Controller is responsible for receiving or handling a system operation to coordinate the user story.                                 |
| Step 3         | ... knowing the Projects of the system?                            | ProjectContainer           | Information Expert: ProjectContainer contains all the projects.                                                                          |                                                                                                              |
| Step 4         | ... knowing the SprintContainer of the project?                    | Project                    | Information Expert: Project knows is own Sprint Container.                                                                               |
| Step 5         | ... knowing the current sprint of the project?                     | SprintContainer            | Information Expert: SprintContainer knows is own Sprint.                                                                                 |
| Step 6         | ... knowing the User Stories (scrum board)?                        | Sprint                     | Information Expert: Sprint contains all the User Stories of the current sprint "planned", "running" and "finished" status (scrum board). |
| Step 7         | ... providing data to the UI?                                      | ViewScrumBoardUIController | Controller: gives product backlog list.                                                                                                  |
| Step 8         | ... providing data to the Actor?                                   | ViewScrumBoardUI           | Information Expert: Is responsible to show the list.                                                                                     |


### 3.2 Sequence Diagram
[SD022_ViewScrumBoard.puml](SD022_ViewScrumBoard.puml)

### 3.3 Class Diagram
[CD022_ViewScrumBoard.puml](CD022_ViewScrumBoard.puml)

## 4. Tests

* *ViewScrumBoardControllerTest*

        @Test
        

* *SprintTest*

        @Test
        void getScrumBoard_Regular_Success() {
        //Arrange
        Status statusPlanned = new Status("planned");
        Status statusRunning = new Status("running");
        Status statusFinished = new Status("finished");
        UserStory userStory_planned_1 = new UserStory("US005", "team member", "text", statusPlanned);
        UserStory userStory_planned_2 = new UserStory("US006", "team member", "text", statusPlanned);
        UserStory userStory_running_1 = new UserStory("US003", "team member", "text", statusPlanned);
        UserStory userStory_running_2 = new UserStory("US004", "team member", "text", statusPlanned);
        UserStory userStory_finished_1 = new UserStory("US001", "team member", "text", statusPlanned);
        UserStory userStory_finished_2 = new UserStory("US002", "team member", "text", statusPlanned);

        Period period = mock(Period.class);
        Sprint sprint = new Sprint("SP01", period);

        sprint.addUserStoryToSprintBacklog(userStory_planned_1);
        sprint.addUserStoryToSprintBacklog(userStory_planned_2);
        sprint.addUserStoryToSprintBacklog(userStory_running_1);
        sprint.addUserStoryToSprintBacklog(userStory_running_2);
        sprint.addUserStoryToSprintBacklog(userStory_finished_1);
        sprint.addUserStoryToSprintBacklog(userStory_finished_2);

        userStory_running_1.setStatus(statusRunning);
        userStory_running_2.setStatus(statusRunning);
        userStory_finished_1.setStatus(statusFinished);
        userStory_finished_2.setStatus(statusFinished);

        List<UserStory> expected = new ArrayList<>();
        expected.add(userStory_finished_1);
        expected.add(userStory_finished_2);
        expected.add(userStory_planned_1);
        expected.add(userStory_planned_2);
        expected.add(userStory_running_1);
        expected.add(userStory_running_2);

        //Act
        List<UserStory> result = sprint.getScrumBoardList();

        //Assert
        assertEquals(expected, result);
       }
