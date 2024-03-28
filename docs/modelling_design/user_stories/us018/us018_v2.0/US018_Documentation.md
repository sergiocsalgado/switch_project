# US018 - As PO/SM/Team Member, I want to consult the product backlog, i.e. to get the list of user stories sorted by priority.

## 1. Requirements Engineering

### 1.1 User Story Description

#### As PO/SM/Team Member, I want to consult the product backlog, i.e. to get the list of user stories sorted by priority.

For this user story, the actor (Product Owner, Scrum Master or Team Member) through the unique code of 
a specific project will be able to get the project product backlog, i.e., a list of the user stories that are
in "planned" status at that moment.

### 1.2 Customer Specifications and Clarification

*From the specification document:*

*2.4.3 Record of activities of a project*

In scrum, the requirements of the project are expressed as user stories and the set of all user 
stories (US) is the “product backlog”, i.e., the ordered set (by priority) of all user stories still to
be implemented in the project. Almost all activities in the project are related to these user 
stories. User stories are created by the Product Owner (PO) and added to the project backlog.


*From client clarification:*

####   Question: 
- Como é definida a prioridade das User Stories e qual a sua escala?
- Caso as User Stories tenham a mesma prioridade, devem ser ordenadas pelo número da User Story? 
- De sprint para sprint, caso a US se mantenha, a prioridade pode alterar?

####   Answer:
O Product Backlog é a lista ordenada das user stories do projeto ainda não implementadas, ordenada
por ordem decrescente de prioridade. Isto é, o primeiro elemento da lista é o de maior prioridade.
Naturalmente que não pode haver duas US com a mesma prioridade, uma vez que numa lista não pode haver dois
elementos na mesma posição.

No final do sprint:
- Se há user stories aceites naturalmente que "saem" do Product Backlog (lista das US por concluir).
- User stories rejeitadas continuam no Product Backlog, mas a prioridade/posição na lista é a que o PO definir na 
altura. Nada impede que seja relegada para o fim da lista.
- Podem ser adicionadas novas US ao Product Backlog.
- Pode ser alterada a posição relativa das US que o PO entender.

Ou seja, o Product Backlog vai ser necessariamente alterado.

####   Question:
Que informações das US é que essa lista tem de conter?

####   Answer:
O Product Backlog é a listas das user stories para implementação (nem concluídas nem eliminadas). Provavelmente até 
pode ser uma lista de strings.

### 1.3 Acceptance Criteria

*User stories in list must be ordered by priority. The priority is defined by their placement in the list (first one 
in position 0).
*User stories in the same project can't have the same number


### 1.4 Found out Dependencies

* In order to be able to get the product backlog the following dependencies where found:
    * *US017* - "As Product Owner, I want to create a user story and add it to the Product Backlog."

### 1.5 Input and Output Data

**Input Data:**
* Project code.

**Output Data:**
* List of user stories of the product backlog.
* (In)Success of the operation.

### 1.6 Use-Case Diagram (UCD)
[UC018_ListUS'sOfTheProductBacklog.puml](UC018_ListUS%27sOfTheProductBacklog.puml)

### 1.7 System Sequence Diagram (SSD)
[SSD018_ListUS'sOfTheProductBacklog.puml](SSD018_ListUS%27sOfTheProductBacklog.puml)


## 2. OO Analysis
### 2.1 Relevant Domain Model Excerpt
[DomainModel_US018.puml](DomainModel_US018.puml)

## 3. Design - User Story Realization
### 3.1 Rationale

| Interaction ID | Question: Which class is responsible for... | Answer                             | Justification                                                                                                   |
|----------------|---------------------------------------------|------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?             | UI                                 | Pure fabrication: collecting data from the user                                                                 |
| Step 2         | ... coordinating the user story?            | UserStoryController                | Controller : coordinating and distributing the actions performed on<br/> the User Interface to the domain layer |
| Step 3         | ... validate/collect data globally?         | ListUserStoriesService             | Pure fabrication: handle the logical part of the process                                                        |
|                | ... knowing the database?                   | Repository<ProjectCode, Project>   | Information Expert: have access to all Projects                                                                 |
|                | ... knowing the database?                   | Repository<UserStoryID, UserStory> | Information Expert: have access to all User Stories                                                             |
|                | ... shows the data?                         | Project                            | Information Expert: knows its own data                                                                          |
|                | ... shows the data?                         | UserStory                          | Information Expert: knows its own data                                                                          |
|                | ... maps the data?                          | UserStoryMapper                    | Pure fabrication: maps the domain objects to DTOs                                                               |
| Step 4         | ... present the user stories list?          | UI                                 | Information Expert: responsible for user interaction                                                            |

 
### 3.2 Sequence Diagram
[SD018_ListUS'sOfTheProductBacklog.puml](SD018_ListUS%27sOfTheProductBacklog.puml)

### 3.3 Class Diagram
[CD018_ListUS'sOfTheProductBacklog.puml](CD018_ListUS%27sOfTheProductBacklog.puml)

## 4. Tests

* **Success**

       
    @Test
    void unitTest_ensureUserStoriesWithAllTypeOfStatusAreListedCorrectly_Successful() {
        //Arrange
        String projectCodeGiven = "PRJ1";
        ProjectCode projectCode = new ProjectCode(projectCodeGiven);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        //create and save user stories
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);
        UserStoryID userStoryID4 = mock(UserStoryID.class);
        UserStoryID userStoryID5 = mock(UserStoryID.class);

        UserStoryStatus planned = mock(UserStoryStatus.class);
        UserStoryStatus running = mock(UserStoryStatus.class);
        UserStoryStatus finished = mock(UserStoryStatus.class);
        UserStoryStatus blocked = mock(UserStoryStatus.class);

        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        UserStory userStory3 = mock(UserStory.class);
        UserStory userStory4 = mock(UserStory.class);
        UserStory userStory5 = mock(UserStory.class);

        Priority priority1 = mock(Priority.class);
        Priority priority2 = mock(Priority.class);
        Priority priority3 = mock(Priority.class);
        Priority priority4 = mock(Priority.class);
        Priority priority5 = mock(Priority.class);

        when(userStory1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStory2.getUserStoryID()).thenReturn(userStoryID2);
        when(userStory3.getUserStoryID()).thenReturn(userStoryID3);
        when(userStory4.getUserStoryID()).thenReturn(userStoryID4);
        when(userStory5.getUserStoryID()).thenReturn(userStoryID5);

        when(userStory1.getStatus()).thenReturn(planned);
        when(userStory2.getStatus()).thenReturn(running);
        when(userStory3.getStatus()).thenReturn(planned);
        when(userStory4.getStatus()).thenReturn(finished);
        when(userStory5.getStatus()).thenReturn(blocked);

        when(finished.getDescription()).thenReturn("finished");
        when(planned.getDescription()).thenReturn("planned");
        when(running.getDescription()).thenReturn("running");

        when(userStory1.getProjectCode()).thenReturn(projectCode);
        when(userStory2.getProjectCode()).thenReturn(projectCode);
        when(userStory3.getProjectCode()).thenReturn(projectCode);
        when(userStory4.getProjectCode()).thenReturn(projectCode);
        when(userStory5.getProjectCode()).thenReturn(projectCode);

        when(priority1.getIndex()).thenReturn(1);
        when(priority2.getIndex()).thenReturn(2);
        when(priority3.getIndex()).thenReturn(3);
        when(priority4.getIndex()).thenReturn(4);
        when(priority5.getIndex()).thenReturn(5);

        when(userStory1.getPriority()).thenReturn(priority1);
        when(userStory2.getPriority()).thenReturn(priority2);
        when(userStory3.getPriority()).thenReturn(priority3);
        when(userStory4.getPriority()).thenReturn(priority4);
        when(userStory5.getPriority()).thenReturn(priority5);

        List<UserStory> userStories = List.of(userStory1, userStory2, userStory3, userStory4, userStory5);

        when(userStoryRepository.findAll()).thenReturn(userStories);

        List<UserStory> expected = List.of(userStory1, userStory3);

        //Act
        List<UserStory> result = listUserStoriesService.getUserStories(projectCodeGiven);

        //Assert
        assertEquals(expected, result);
    }

* **Fail**
  
        @Test
        void unitTest_ensureThatReturnsEmptyListBecauseWrongProjectCode_Successful() {
            //Arrange
            String projectCodeGiven = "PRJ1";
            ProjectCode projectCode = new ProjectCode(projectCodeGiven);
            when(project.getProjectCode()).thenReturn(projectCode);
            when(projectRepository.containsOfIdentity(projectCode)).thenReturn(false);

            List<UserStory> expected = new ArrayList<>();

            //Act
            List<UserStory> result = listUserStoriesService.getUserStories(projectCodeGiven);

            //Assert
            assertEquals(expected, result);
        }

