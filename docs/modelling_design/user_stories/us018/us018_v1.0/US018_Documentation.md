# US018 - As PO/SM/Team Member, I want to consult the product backlog, i.e. to get the list of user stories sorted by priority.

## 1. Requirements Engineering

### 1.1 User Story Description

#### As PO/SM/Team Member, I want to consult the product backlog, i.e. to get the list of user stories sorted by priority.

For this user story, the actor (Product Owner, Scrum Master or Team Member) through the unique code of 
a specific project will be able to get the project product backlog, i.e., a list of the user stories that are
in "planned" status at that moment.

### 1.2 Customer Specifications and Clarification

>*From the specification document:*

*2.4.3 Record of activities of a project*

In scrum, the requirements of the project are expressed as user stories and the set of all user 
stories (US) is the “product backlog”, i.e., the ordered set (by priority) of all user stories still to
be implemented in the project. Almost all activities in the project are related to these user 
stories. User stories are created by the Product Owner (PO) and added to the project backlog.


>*From client clarification:*

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

User stories in list must be ordered by priority. The priority is defined by their placement in the list (first one 
in position 0).


### 1.4 Found out Dependencies

* In order to be able to get the product backlog the following dependencies where found:
    * *US017* - "As Product Owner, I want to create a user story and add it to the Product Backlog."

### 1.5 Input and Output Data

*Input Data:*
* Project code.

*Output Data:*
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

| Interaction ID | Question: Which class is responsible for...                        | Answer                    | Justification                                                                                                 |
|----------------|--------------------------------------------------------------------|---------------------------|---------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                                    | ListUserStoriesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| Step 2         | ... interacting with the UI layer and coordinating the user story? | ListUserStoriesController | The Controller is responsible for receiving or handling a system operation to coordinate the user story.      |
| Step 3         | ... knowing the Projects of the system?                            | ProjectContainer          | Information Expert: ProjectContainer contains all the projects.                                               |                                                                                                              |
| Step 4         | ... knowing the ProjectBacklog of the project?                     | Project                   | Information Expert: Project knows is own Product Backlog.                                                     |
| Step 5         | ... knowing the User Stories (product backlog)?                    | ProductBacklog            | Information Expert: ProductBacklog contains all the User Stories of the project with "planned" status.        |
| Step 6         | ... providing data to the UI?                                      | ListUserStoriesController | Controller: gives product backlog list.                                                                       |
| Step 7         | ... providing data to the Actor?                                   | ListUserStoriesUI         | Information Expert: Is responsible to show the list.                                                          |

 
### 3.2 Sequence Diagram
[SD018_ListUS'sOfTheProductBacklog.puml](SD018_ListUS%27sOfTheProductBacklog.puml)

### 3.3 Class Diagram
[CD018_ListUS'sOfTheProductBacklog.puml](CD018_ListUS%27sOfTheProductBacklog.puml)

## 4. Tests

* *ListUserStoriesControllerTest*

        @Test
        void mock_ensureUserStoriesAreListedCorrectly_Success() {

        ProjectContainer projectContainerDouble = mock(ProjectContainer.class);
        UserStoryMapper mapperDouble = mock(UserStoryMapper.class);
        ListUserStoriesController controller = new ListUserStoriesController(projectContainerDouble, mapperDouble);

        Project projectDouble = mock(Project.class);
        when(projectContainerDouble.getProject("code1")).thenReturn(projectDouble);

        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        when(projectDouble.getProductBacklog()).thenReturn(productBacklogDouble);

        List<UserStory> userStoriesDouble = mock(List.class);
        when(productBacklogDouble.getUserStories()).thenReturn(userStoriesDouble);

        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);
        when(userStoryDTODouble.getUserStoryNumber()).thenReturn("001");
        when(userStoryDTODouble.getUserStoryText()).thenReturn("text1");
        when(userStoryDTODouble.getStatus()).thenReturn("planned");

        List<UserStoryDTO> dtoListDouble = Arrays.asList(userStoryDTODouble);

        when(mapperDouble.getUserStoriesDTO(userStoriesDouble)).thenReturn(dtoListDouble);

        //Act
        List<UserStoryDTO> result = controller.getProductBacklogDTO("code1");

        //Assert
        assertEquals(dtoListDouble, result);
      }

* *ProductBacklogTest*
  
        @Test
        void unitTest_getUserStories_Successful() {

        ProjectContainer projectContainerDouble = mock(ProjectContainer.class);
        Project projectDouble = mock(Project.class);
        when(projectContainerDouble.getProject("code1")).thenReturn(projectDouble);

        FactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        Status statusDouble = mock(Status.class);

        UserStory userStoryDouble= mock(UserStory.class);
        when(factoryUserStoryDouble.createUserStory("1","andre","create us",
                statusDouble)).thenReturn(userStoryDouble);
        productBacklog.createUserStory("1","andre","create us",
                statusDouble);


        List<UserStory> expected = new ArrayList<>();
        expected.add(userStoryDouble);

        List<UserStory> result = productBacklog.getUserStories();

        assertEquals(expected,result);
      }

