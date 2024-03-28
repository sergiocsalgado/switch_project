# US015 - As Manager, I want to get a list of all projects.

## 1. Requirements Engineering

### 1.1 User Story Description

The purpose of this user story is to provide managers with a comprehensive list of all the projects composed only by<br/>
the relevant information. This list will help managers keep track of project progress, allocate resources effectively,<br/>
and make informed decisions about prioritizing projects based on their current status.

### 1.2 Customer Specifications and Clarification

*From the specification document:*

(page:1 - line(s):28):
The Manager is a profile that allows the user to access all projects and manage resources.

(page:3 - line(s):27-37):
The system should be equipped with a form that allows the search of projects. Searchable
fields should be at least:
* Code;
* Name;
* Start month;
* Customer;
* Business Sector;
* Project status.

Project search should be accessible to all users, albeit the users should only get information about the projects<br/>
they were/are involved. The Manager is the only profile that has access to all projects.

*From client clarification:*
> **Question:** <br>
> Qual a informação que deve constar na lista de projectos? Código, nome e status?
> 
> **Answer:** <br>
> Código, nome do projeto, cliente, status, data de início e fim (se aplicáveis).

### 1.3 Accepted Criteria
* The information that should be in the project listing is: code, project name, client, status, start and end date (if applicable).

### 1.4 Found out Dependencies
* In order to be able to list all projects, the following
  dependencies where found:
  * *US010* "As Manager, I want to register/create a new project."

### 1.5 Input and Output Data

**Input Data:**

* n/a

**Output Data:**

* *List of all projects with:*
  * code
  * name
  * description
  * customer
  * business sector
  * typology;
  * startDate;
  * endDate;
  * status;
  * sprintDuration;
  * numberOfPlannedSprints;
  * budget;

### 1.6 Use-Case Diagram (UCD)
[UC015_ListAllProjects.puml](UC015_ListAllProjects.puml)

### 1.7 System Sequence Diagram (SSD)
[SSD015_ListAllProjects.puml](SSD015_ListAllProjects.puml)


## 2. OO Analysis

### 2.1 Relevant Domain Model Excerpt
[US015_DomainModelExcerpt.puml](DM015_ListAllProjects.puml)


## 3. Design - User Story Realization

### 3.1 Rationale

| Interaction ID | Question: Which class is responsible for... | Answer                                       | Justification (with patterns)                                                                                   |
|----------------|---------------------------------------------|----------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?             | ListAllProjectsUI                            | Pure fabrication: collecting data from the user                                                                 |
| Step 2         | ... coordinating the US?                    | ProjectController                            | Controller : coordinating and distributing the actions performed on<br/> the User Interface to the domain layer |
| Step 3         | ... validate/collect data globally?         | ListAllProjectsService                       | Pure fabrication: handle the logical part of the process                                                        |
|                | ... knowing the database?                   | Repository<ProjectCode, Project>             | Information Expert: knows/has all Projects                                                                      |
|                | ... knowing the database?                   | Repository<CustomerID, Customer>             | Information Expert: knows/has all Customers                                                                     |
|                | ... knowing the database?                   | Repository<TypologyID, Typology>             | Information Expert: knows/has all Typologies                                                                    |
|                | ... knowing the database?                   | Repository<BusinessSectorID, BusinessSector> | Information Expert: knows/has all Business Sectors                                                              |
|                | ... shows the data?                         | Project                                      | Information Expert: knows its own data                                                                          |
|                | ... shows the data?                         | Customer                                     | Information Expert: knows its own data                                                                          |
|                | ... shows the data?                         | Typology                                     | Information Expert: knows its own data                                                                          |
|                | ... shows the data?                         | BusinessSector                               | Information Expert: knows its own data                                                                          |
|                | ... maps the data?                          | ProjectMapper                                | Pure fabrication: maps the domain objects to DTOs                                                               |
| Step 4         | ... present the project list?               | UI                                           | Information Expert: responsible for user interaction                                                            |


### 3.2 Sequence Diagram
[SD015_ListAllProjects.puml](SD015_ListAllProjects.puml)

### 3.3 Class Diagram
[CD015_ListAllProjects.puml](CD015_ListAllProjects.puml)

## 4. Unit Tests

* *Success*

  * {@link ListAllProjectsServiceImplTest#ensureGetProjects_ensureTheListHasOneProject()}

  * {@link ProjectsControllerTest#ensureGetListOfAllProjectsDTO()}


## 5. Observations

* It will be necessary later on to ensure that only the account with the associated "manager" profile
can request the project list.