# US017 - As Product Owner, I want to create a user story and add it to the ProductBacklog.

## 1. Requirements Engineering
### 1.1 User Story Description
For this user story the actor will only need to enter the attributes "Text", "Number", "Actor", and "Priority", but the
user story will also use the attributes "projectCode" and "userStoryID" that are automatically generated or fetched in 
the project where the user story is created in the UI.
Then the user story will be created and added to the Product Backlog.

### 1.2 Customer Specifications and Clarification
*From the specification document:*

* A US must have at least the following information:
    * US number;
    * Actor;
    * US text;
    * List of acceptance criteria.
      </br>
      </br>

*From client clarification:*
>Question: Ao criar uma user story, o número da mesma é automaticamente atribuído? Isto é, se já foi anteriormente criada uma us001 para determinado projecto, a que for criada em seguida para o mesmo projecto terá automaticamente o número 002?
De seguida, ao criar uma user story, há critério(s) para garantir que não é repetida? Quais seriam os critérios?
>
> Answer: A identificação das user stories é dada pelo autor. Não é automática. Naturalmente que num dado projeto não podem existir duas user stories com o mesmo ID. Isto inclui user stories já concluídas.
Note-se que, por exemplo, US03 e US03v2 não são a mesma.

>Question: Relativamente à US017, quando o PO cria uma US é logo definida, pelo próprio, a sua prioridade ou esta apenas é definida depois de já ter sido adicionada à lista? Isto é, entraria na lista, assumiria a última posição e, posteriormente, sofria reorganização mediante a sua priorização. Ou ambas?
Para atribuição do número de cada US podemos considerar que o PO define o ID da US e o número é automaticamente incrementado? Exemplo: "APP-001; PLO-001; APP-002", "APP" seria o nome do projeto e "001" seria o número da US automaticamente gerado.
>
> Answer: Eu já respondi a alguém que as user stories não são numeradas automaticamente.
As US também não têm uma propriedade "prioridade". A prioridade é dada pela posição na lista.
Não me parece mal que o PO, ao criar uma US, possa dizer qual a posição na lista. Se nada for indicado, ser adicionada no fim ou no início.

>Question: Como essa User Story visa obter a lista de toda as User Stories pela ordem de prioridade (product backlog) devemos devolver as User Stories com todos os seus atributos ou seria somente parte desses atributos, como por exemplo a User Story number e o User Story  text.
>
> Answer: Mas, para já, que propriedades têm as user stories, para além do número/ID, texto e, eventualmente, estado? O Product Backlog é a listas das user stories para implementação (nem concluídas nem eliminadas). Provavelmente até pode ser uma lista de strings.

### 1.3 Acceptance Criteria
* A newly created User Story will always be with the Status of "Planned", meaning the user story is still to be implemented in the project.
* The User Story Number can not be null or the same as other User Story in the same Project.
* Each User Story will have an Effort that will **not** be indicated in the User Story creation. The Effort will only be estimated during the sprint planning ceremony.

### 1.4 Found out Dependencies
* In order to be able to create a user story, we need to know for which project. The following dependencies where found:
    * US010 - As Manager, I want to register/create a new project.

### 1.5 Input and Output Data
*Input Data:*
* *Typed data:*
    * projectCode
    * userStoryNumber
    * actor
    * userStoryText

* *Selected data:*
    * N/A

*Output Data:*
* *Attributes:*
  * userStoryID
  * projectCode
  * userStoryNumber
  * actor
  * userStoryText
  * status
  * priority

* *Message Output:*
  * Message with the **Http Status** of the result of the operation.

### 1.6 Use Case Diagram (UCD)

[UC017_CreateUserStory](UC017_CreateUserStory.puml)

### 1.7 System Sequence Diagram (SSD)

[SSD017_CreateUserStory](SSD017_CreateUserStory.puml)


## 2. Analysis
### 2.1 Relevant Domain Model Excerpt (DDD)
[DM017_CreateUserStory_DDD](DM017_CreateUserStory_DDD.puml)


## 3. Design - User Story Realization
### 3.1 Rationale
| Interaction ID | Question: Which class is responsible for...                         | Answer                 | Justification                                                                                                |
|----------------|---------------------------------------------------------------------|------------------------|--------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                                     | UI                     | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model |
| Step 2         | ... interacting with the UI layer, and coordinating the user story? | UserStoryController    | Controller: is responsible for receiving or handling a system operation to coordinate the user story         |
| Step 3         | ... validate/collect data globally?                                 | CreateUserStoryService | Pure fabrication: handle the logical part of the process                                                     |
| Step 4         | ... knowing the Projects of the system?                             | ProjectRepository      | Information Expert: have access to all projects                                                              |
| Step 5         | ... knowing the User Stories of the system?                         | UserStoryRepository    | Information Expert: have access to all userStories                                                           |
| Step 6         | ... maps the data?                                                  | UserStoryMapper        | Pure fabrication: maps the domain objects to DTOs                                                            |
| Step 7         | ... providing data to the UI?                                       | UserStoryController    | Controller: returns to the UI the operation result                                                           |
| Step 8         | ... providing data to the Actor?                                    | UI                     | Information Expert: is responsible for user interactions                                                     |


### 3.2 Sequence Diagram
[SD017_CreateUserStory](SD017_CreateUserStory.puml)

### 3.3 Class Diagram
[CD017_CreateUserStory](CD017_CreateUserStory.puml)


## 4. Tests
* *Controller Unit Test Success*
```java
@Test
    void ensureUserStoryIsAdded() {
        //Arrange
        String projectCodeInput = "PRJ1";
        String userStoryIDInput = "US1";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        when(userStory.getProjectCode()).thenReturn(projectCode);
        when(userStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStory.getActor()).thenReturn(actor);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        when(userStory.getUserStoryText()).thenReturn(userStoryText);
        when(userStory.getPriority()).thenReturn(priority);

        when(projectCode.getProjectCode()).thenReturn(projectCodeInput);
        when(userStoryID.getUserStoryID()).thenReturn(userStoryIDInput);
        when(userStoryNumber.getNumber()).thenReturn(userStoryNumberInput);
        when(actor.getValue()).thenReturn(actorInput);
        when(userStoryStatus.getDescription()).thenReturn(userStoryStatusInput);
        when(userStoryText.getDescription()).thenReturn(userStoryTextInput);
        when(priority.getIndex()).thenReturn(priorityInput);

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.setProjectCode(projectCodeInput);
        userStoryDTO.setUserStoryID(userStoryIDInput);
        userStoryDTO.setUserStoryNumber(userStoryNumberInput);
        userStoryDTO.setActor(actorInput);
        userStoryDTO.setStatus(userStoryStatusInput);
        userStoryDTO.setUserStoryText(userStoryTextInput);
        userStoryDTO.setPriority(priorityInput);

        when(userStoryService.createUserStory(projectCodeInput, userStoryIDInput,
                userStoryNumberInput, actorInput, userStoryTextInput, priorityInput)).thenReturn(userStory);

        ResponseEntity<Object> expected = new ResponseEntity<>(userStoryDTO, HttpStatus.CREATED);

        //ACT
        ResponseEntity<Object> result = controllerUnderTest.createUserStoryAndAddToProductBacklog(
                projectCodeInput, userStoryDTO);

        //Assert
        assertEquals(expected, result);
    }
```

* *Service Unit Test Success*
```java
@Test
    void createUserStory_Successful_EmptyRepository() {
        //ARRANGE
        String projectCodeInput = "PRJ1";
        String userStoryIDInput = "US1";
        String userStoryNumberInput = "1";
        String actorInput = "name";
        String userStoryTextInput = "desc";
        String userStoryStatusInput = "planned";
        int priorityInput = 1;

        ProjectCode projectCode = new ProjectCode(projectCodeInput);
        UserStoryID userStoryID = new UserStoryID(userStoryIDInput);
        UserStoryNumber userStoryNumber = new UserStoryNumber(userStoryNumberInput);
        Name actor = new Name(actorInput);
        Description userStoryText = new Description(userStoryTextInput);
        UserStoryStatus userStoryStatus = new UserStoryStatus(userStoryStatusInput);
        Priority priority = new Priority(priorityInput);

        when(factoryUserStory.createUserStory(userStoryID, projectCode,
                userStoryNumber, actor, userStoryText, userStoryStatus
                , priority)).thenReturn(userStory);

        when(userStory.getPriority()).thenReturn(priority);
        List<UserStory> userStories = new ArrayList<>();
        when(userStoryRepository.findAll()).thenReturn(userStories);
        when(projectRepository.containsOfIdentity(projectCode)).thenReturn(true);

        when(userStory.getUserStoryNumber()).thenReturn(userStoryNumber);

        when(userStory.getProjectCode()).thenReturn(projectCode);

        when(userStory.getUserStoryID()).thenReturn(userStoryID);

        when(userStoryRepository.save(userStory)).thenReturn(userStory);
        when(userStoryRepository.containsOfIdentity(userStoryID)).thenReturn(true);

        UserStory expected = factoryUserStory.createUserStory(userStoryID, projectCode,
                userStoryNumber, actor, userStoryText, userStoryStatus
                , priority);

        //ACT
        UserStory result = userStoryService.createUserStory(projectCode.getProjectCode(),
                userStoryID.getUserStoryID(), userStoryNumber.getNumber(),
                actor.getValue(), userStoryText.getDescription(), priority.getIndex());

        //ASSERT
        assertEquals(result, expected);
    }
```


## 5. Integration and Demo
* It will be necessary to ensure that only the *Product Owner* can create user stories.
