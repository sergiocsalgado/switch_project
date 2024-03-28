# US016 - As Authenticated User, I want to get a list of all projects I'm currently allocated to.

## 1. Requirements Engineering

### 1.1 User Story Description

#### As Authenticated User, I want to get a list of all projects I'm currently allocated to.

To be able to receive the list of all the projects the user is allocated to,
he needs to enter his e-mail.

### 1.2 Customer Specifications and Clarification

From the specification document:

2.4.7 Allocations report

The allocations report should display information about the projects to which each resource is
assigned at each time. For a specified start and end date, clear and easy-to-query information
about the distribution of time by the different projects is displayed.
For the Manager profile, information about the allocation of all users registered in the system
should be listed.
For the Project Manager, PO and SM profiles, only the information corresponding to projects
to which the user has access as a manager should be listed.
For the User profile, the information relating to himself must be displayed.

From client clarification:

* *Question (asked by José Teixeira (Turma B)):*

É especificado que o User está autenticado ("As Authenticated User...").
Poderia por favor detalhar o que é pretendido, nesta US, relativamente à autenticação?
Pretende que seja já introduzido o conceito de sign in e session?

* *Answer:*

"As a authenticated user" significa "como utilizador autenticado".
Isto é, como qualquer utilizador autenticado no sistema.
Isto é, qualquer profile que requeira autenticação pode executar esta user story.
Assim, ao executar esta user story é preciso verificar o profile do user passado ao modelo.
A resposta vai estar associada ao profile e, em alguns casos, aos papéis que o user possa ter nos projetos existentes.
Para testar esta US seria conveniente que haja vários projetos e vários utilizadores com diferentes papéis.
Caso contrário, os testes não serão minimamente convincentes.
Os conceitos de sign in e session não são necessários nesta, nem em qualquer outra user story deste sprint.

### 1.3 Accepted Criteria

n/a

### 1.4 Found out Dependencies

* In order to be able to get a list of all the projects of the user, the following
  dependencies where found:


    * *US002* "As Administrator, I want to register a user"
    * *US010* "As Manager, I want to register/create a new project."
    * *US011* "As Manager, I want to associate a user as Team Member of a project."
    * *US012* "As Manager, I want to define the PO of a project."
    * *US013* "As Manager, I want to define the SM of a project."


### 1.5 Input and Output Data

*Input Data:*
* email

*Output Data:*
* *List of all projects the user is allocated to, with those attributes:*
    * code
    * name
    * description
    * customer
    * business sector
    * status


### 1.6 System Sequence Diagram (SSD)

![SSD016.png](images%2FSSD016.png)

### 1.7 Other Relevant Remarks

n/a

## 2. OO Analysis
### 2.1 Relevant Domain Model Excerpt

![DomainModelDDD_v2.png](images%2FDomainModelDDD_v2.png)

### 2.2 Other Remarks

n/a

## 3. Design - User Story Realization

### 3.1 Rationale


### 3.2 Sequence Diagram

![SD016_V2.0.png](images%2FSD016_V2.0.png)

### 3.3 Class Diagram

![CD016_V2.0.png](images%2FCD016_V2.0.png)

## 4. Tests

[ListUserProjectsControllerTest.java](..%2F..%2F..%2F..%2F..%2Fsrc%2Ftest%2Fjava%2Forg%2Fswitch2022%2Fproject%2Fcontroller%2FListUserProjectsControllerTest.java)

* *Success*

![Test_Success.png](images%2FTest_Success.png)

* *Fail*

![Test_Fail.png](images%2FTest_Fail.png)

## 5. Integration and Demo

n/a

## 6. Observations

n/a