# US017 - As Product Owner, I want to create a user story and add it to the ProductBacklog.

## 1. Requirements Engineering

### 1.1 User Story Description

For this user story, the actor will need to enter the Project Code, User Story Number, Actor and User Story Text.
Then the user story will be created and added to the Product Backlog.

### 1.2 Customer Specifications and Clarification

*From the specification document:*

* A US must have at least the following information:
  * US number;
  * Actor; 
  * US text; 
  * List of acceptance criteria.

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
* Each User Story will have an Effort that will not be indicated in the User Story creation. The Effort will only be estimated during the sprint planning ceremony.

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

* User Story created and added to the ProductBacklog

### 1.6 Use Case Diagram (UCD)

![](UC017_CreateUserStory.puml)

### 1.7 System Sequence Diagram (SSD)

![](SSD017_CreateUserStory.puml)

### 1.8 Other Relevant Remarks

* N/A

## 2. Analysis

### 2.1 Relevant Domain Model Excerpt

![](DM017_CreateUserStory.puml)

### 2.2 Other Remarks

* N/A

## 3. Design - User Story Realization

### 3.1 Rationale
| Interaction ID | Question: Which class is responsible for...                         | Answer                    | Justification                                                                                                 |
|----------------|---------------------------------------------------------------------|---------------------------|---------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                                     | UserStoryUI               | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model  |
| Step 2         | ... interacting with the UI layer, and coordinating the user story? | CreateUserStoryController | Controller: is responsible for receiving or handling a system operation to coordinate the user story          |
| Step 3         | ... interacting with the controller?                                | ProjectContainer          | Information Expert: have access to all projects                                                               |
|                | ... interacting with the controller?                                | StatusContainer           | Information Expert: have access to all status                                                                 |
|                | ... interacting with the controller?                                | ProductBacklog            | Information Expert: have access to all userStories                                                            |
| Step 4         | ... creating the UserStory Object?                                  | FactoryUserStoryImpl      | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model  |
| Step 5         | ... providing data to the UI?                                       | CreateUserStoryController | Controller: informs operation success                                                                         |
| Step 6         | ... providing data to the Actor?                                    | ProjectUI                 | Information Expert: is responsible for user interactions                                                      |

### 3.2 Sequence Diagram

![](SD017_CreateUserStory.puml)

### 3.3 Class Diagram

![](CD017_CreateUserStory.puml)

## 4. Tests

    /**
    * Unit Test for {@link CreateUserStoryController#createUserStory(String, String, String, String)}.
    */

    @Test
    void createUserStory() {

    //Arrange
    ProjectContainer projectContainerDouble = mock(ProjectContainer.class);
    StatusContainer statusContainerDouble = mock(StatusContainer.class);

    CreateUserStoryController createUserStoryController = new CreateUserStoryController(projectContainerDouble, statusContainerDouble);

    Status statusDouble = mock(Status.class);
    Project projectDouble = mock(Project.class);
    ProductBacklog productBacklogDouble = mock(ProductBacklog.class);

    when(projectContainerDouble.getProject("PRJ001")).thenReturn(projectDouble);
    when(projectDouble.getProductBacklog()).thenReturn(productBacklogDouble);
    when(productBacklogDouble.createUserStory("US001", "As Administrator", "I Want to create user profiles.", statusDouble)).thenReturn(true);
    when(statusContainerDouble.getStatus("planned")).thenReturn(statusDouble);

    boolean expected = true;

    //Act
    boolean result = createUserStoryController.createUserStory("PRJ001", "US001", "As Administrator", "I Want to create user profiles.");

    //Assert
    assertEquals(expected, result);
    }


    /**
    * Unit Test for {@link ProductBacklog#createUserStory(String, String, String, Status)}.
    */

    @Test
    void ensureDuplicateUserStoryIsNotAdded(){

    //Arrange
    FactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
    ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

    Status statusDouble = mock(Status.class);
    UserStory userStoryDouble = mock(UserStory.class);

    when(factoryUserStoryDouble.createUserStory("US001","Manager", "I Want to create user profiles.",statusDouble)).thenReturn(userStoryDouble);

    productBacklog.createUserStory("US001","Manager", "I Want to create user profiles.",statusDouble);

    boolean expected = false;

    //Act
    boolean result = productBacklog.createUserStory("US001","Manager", "I Want to create user profiles.",statusDouble);

    //Assert
    assertEquals(expected, result);

    }


## 5. Integration and Demo

* N/A

## 6. Observations

* N/A


