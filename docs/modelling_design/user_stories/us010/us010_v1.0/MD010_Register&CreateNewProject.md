# US010 - As Manager, I want to register/create a new project

## 1. Requirements Engineering

### 1.1 User Story Description

For this user story, the actor will need to enter the project code, name and description. It will need to enter the customer name, business sector description and typology description has well.
The project will be registered and added to the list after verifying that it does not already exist. 

### 1.2 Customer Specifications and Clarification


*From the specification document:*

The system should allow the registration of projects, the base entity that will serve for the association of activities and resources (users). Each project must have at least the following information:

* Code (unique alphanumerical identifier for each project);
* Project manager (user who will have the project manager profile for that record; may change over time);
* Name;
* Description;
* Start Date;
* Sprint duration (initial estimate);
* Number of planned sprints;
* End Date (when available, date it was closed);
* Customer;
* Business Sector;
* Typology (Fixed Cost / Time and materials);
* Product Owner (may change over time);  
* Scrum Master (may change over time);
* Project team (may change over time);  
* Project Status (Planned / Inception / Elaboration / Construction / Transition / Warranty / Closed);
* Budget (monetary amount available for resource spending).

The process of creating information relating to a project should be available exclusively for the Manager profile. However, the Project Manager should be able to edit part of the information.


*From client clarification:*

>Question: O que é essencial/mínimo para criar um projecto? Registar inclui guardar?
>
>
>
>Answer: A pergunta está formulada de uma forma estranha. Nem sequer percebo a segunda parte. Na dúvida, como PO não respondo.
>
>Answer: Mas é evidente que a US010 levanta a questão se o registo de um novo projeto é feito de uma vez só, ou se será efetuado por fazes. Por exemplo, faz sentido que a atribuição da equipa seja feita só quando o projeto esteja numa fase de execução, ou de planeamento da execução (Inception). Assim, vou colocar no canal Project uma clarificação sobre este assunto.
>
>Answer: Relativamente à US010, um projeto recém criado estará no estado "planned". Não terá recursos humanos associados.
Também não terá duração, start date, número de sprints e budget. Estes só serão definidos na fase de "inception".


### 1.3 Accepted Criteria
* Code (unique alphanumerical identifier for each project)
* A newly created Project will always be with the Project Status of "Planned"


### 1.4 Found out Dependencies
* In order to be able to add a customer, a business sector and a typology on the project register, the following dependencies where found:
  * US007 - As Administrator, I want to create a new project typology
  * US008 - As Administrator, I want to add a business sector
  * US009 - As Administrator, I want to add a customer


### 1.5 Input and Output Data

* Input Data:

  * Project code
  * Project name
  * Project description
  * Customer name
  * Business Sector description
  * Typology description


* Output Data:

  * Project registered

### 1.6 Use Case Diagram (UCD)
![](UCD010_Register&CreateNewProject.png)


### 1.7 System Sequence Diagram (SSD)

![](SSD010_Register&CreateNewProject.png)

### 1.8 Other Relevant Remarks
* N/A

## 2. OO Analysis

### 2.1 Relevant Domain Model Excerpt

![](DM010_Register&CreateNewProject.png)

### 2.2 Other Remarks
* N/A


## 3. Design - User Story Realization

### 3.1 Rationale

| Interaction ID | US010        | Question: Which class is responsible for...                         | Answer                                                        | Justification                                                                                                |
|----------------|--------------|---------------------------------------------------------------------|---------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| Step 1         | SD 4.1       | ... interacting with the actor?                                     | ProjectUI                                                     | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model |
| Step 2         | SD 4.2       | ... interacting with the UI layer, and coordinating the user story? | ProjectController                                             | The Controller is responsible for receiving or handling a system operation to coordinate the user story      |
| Step 3         | SD 4.3       | ... interacting with the controller?                                | Company                                                       | Information Expert: have access to all containers                                                            |
| Step 4         | SD 4.4/4.7   | ... interacting with the company?                                   | Containers(Customer, BusinessSector, Typology, ProjectStatus) | Information Expert: have lists of data (Projects)                                                            |
|                | SD 4.8/4.10  | ... interacting with the company?                                   | ProjectContainer                                              | Information Expert: knows every project of the system                                                        |
| Step 5         | SD 4.11/4.13 | ... providing data to the UI?                                       | ProjectController                                             | Controller: informs operation success                                                                        |
| Step 6         | SD 4.14      | ... providing data to the Actor?                                    | ProjectUI                                                     | Information Expert: Is responsible for user interactions                                                     |


### 3.2 Sequence Diagram

![](SD010_Register&CreateNewProject_Extra.png)

![](SD010_Register&CreateNewProject_US.png)

### 3.3 Class Diagram

![](CD010_Register&CreateNewProject.png)

## 4. Tests

* *Success*
  * Ensure that a new Project is created with all the included information;
    ![](Test1.png)
  
* *Fail*
  * Ensure that the project we want to register is not registered in duplicate;
    ![](Test2.png)


## 5. Integration and Demo
* N/A

## 6. Observations
* N/A