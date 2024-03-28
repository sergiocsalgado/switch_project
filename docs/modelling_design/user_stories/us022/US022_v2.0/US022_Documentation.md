# US022 - As Team Member, I want to view the Scrum Board of current sprint.

## 1. Requirements Engineering

### 1.1 User Story Description
For this user story, the actor (Team Member) through the unique code of
a specific project will be able to view the scrum board, i.e., a list of the user stories that are
in "planned", "running" and "finished" status at that current sprint.

### 1.2 Customer Specifications and Clarification

*From the specification document:*

*2.4.6 View the status of activities in the project*

The project activity list should have two distinct viewing modes, which can be used interchangeably or
simultaneously:
- Table-shaped view;
- View in Gantt format (by sprint).


*From client clarification:*

> **Question:** <br>
> Em relação à US022, no Scrum Board devem constar as User Stories de determinado projeto <br>
> no sprint atual ou devem constar as Tasks de determinado projeto no sprint atual? 
> 
> **Answer:** <br>
> Parece-me importante haver uma ferramenta que permita saber e alterar o estado de cada user story no sprint. <br>
> O scrum board parece ser o candidato óbvio.
> 
> Vai haver tarefas que estão diretamente associadas a user stories e outras não. Dá jeito ter uma ferramenta <br>
> para controlo do estado das tarefas? Concordo que sim. <br>
> Cria-se um task board em paralelo com o scrum board? Provavelmente não. Ferramentas a mais são uma distração. <br>
> É possível misturar tarefas e user stories num mesmo scrum board? Porque não? O scrum board é uma representação <br>
> de estados. Se eles são os mesmos.


> **Question:** <br>
> Nesta sprint deveremos implementar também as tasks ou apenas as user stories?
> 
> **Answer:** <br>
> Não receberam qualquer user story sobre tasks, pelo que me parece extremamente difícil <br>
> que possam criar tasks nesta altura. Seria pura adivinhação, e adivinhar requisitos é um erro.


> **Question:** <br>
> "Parece-me importante haver uma ferramenta que permita saber e alterar o estado de cada user story no sprint. <br>
> O scrum board parece ser o candidato óbvio."
>
> Implementar já também funcionalidade que permita alterar estado das US no sprint atual? Ou apenas <br>
> funcionalidade que permita saber qual é esse estado?
>
> **Answer:** <br>
> Só devem implementar as user stories que estão no sprint backlog.

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

| Interaction ID | Question: Which class is responsible for... | Answer                             | Justification (with patterns)                                                                                   |
|----------------|---------------------------------------------|------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?             | ViewScrumBoardUI                   | Pure fabrication: collecting data from the user                                                                 |
| Step 2         | ... coordinating the US?                    | ProjectController                  | Controller : coordinating and distributing the actions performed on<br/> the User Interface to the domain layer |
| Step 3         | ... validate/collect data globally?         | ViewScrumBoardService              | Pure fabrication: handle the logical part of the process                                                        |
|                | ... knowing the database?                   | Repository<ProjectCode, Project>   | Information Expert: knows/has all Projects                                                                      |
|                | ... knowing the database?                   | Repository<SprintID, Sprint>       | Information Expert: knows/has all Sprints                                                                       |
|                | ... knowing the database?                   | Repository<UserStoryID, UserStory> | Information Expert: knows/has all User Stories                                                                  |
|                | ... shows the data?                         | Project                            | Information Expert: knows its own data                                                                          |
|                | ... shows the data?                         | Sprint                             | Information Expert: knows its own data                                                                          |
|                | ... shows the data?                         | UserStory                          | Information Expert: knows its own data                                                                          |
|                | ... maps the data?                          | UserStoryMapper                    | Pure fabrication: maps the domain objects to DTOs                                                               |
| Step 4         | ... present the project list?               | UI                                 | Information Expert: responsible for user interaction                                                            |


### 3.2 Sequence Diagram
[SD022_ViewScrumBoard.puml](SD022_ViewScrumBoard.puml)

### 3.3 Class Diagram
[CD022_ViewScrumBoard.puml](CD022_ViewScrumBoard.puml)

## 4. Unit Tests

* *Success*

  * {@link ViewScrumBoardServiceImplTest#getScrumBoard_shouldReturnTheScrumBoardOrderedByUserStoryStatus_Success()}

  * {@link ViewScrumBoardControllerTest#viewScrumBoard_UnitTest_ensureScrumBoardViewIsASortedByStatusDescriptionListOfUserStoriesDTO_Unit()}

* *Fail*
  
  * {@link ViewScrumBoardServiceImplTest#getScrumBoard_shouldFailWhenIsNotOrdered()}
  
  * {@link ViewScrumBoardControllerTest#viewScrumBoard_UnitTest_ensureThrowsExceptionWhenProjectNotFound()}

## 5. Observations

* It will be necessary later on to ensure that only the account with the associated "manager" profile
  can request the project list.
