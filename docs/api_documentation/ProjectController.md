# Project Controller

Controller class for registering and managing projects and resources in project.

## Constructor

### ProjectController

Constructs a new ProjectController with the specified services.

#### Parameters

- `registerProjectService` (RegisterProjectService): The service responsible for registering projects.
- `defineRoleService` (DefineRoleService): The service responsible for creating resource in project.
- `listAllProjectsService` (ListAllProjectsService): The service responsible for list all projects.
- `listResourcesService` (ListResourcesService): The service responsible for list all resources in project.

## Endpoints

### POST /projects

Register and saves a project based on the provided projectDTO.

#### Request

- HTTP Method: POST
- Path: `/projects`
- Request Body: projectDTO (JSON)

> `projectCode`(string,required): The code of the project. <br>
> `name` (string, required): The name of the project. <br>
> `description` (string,required): The description of the project. <br>
> `customer` (string, required): The customer's name of the project. <br>
> `businessSector` (string, required): The business Sector's description of project. <br>
> `typology` (string, required): The typology's description of the project. <br>
> `startDate` (string, required): The start date of the project. <br>
> `endDate` (string,required): The end date of the project. <br>
> `sprintDuration` (int, required): The duration of the sprins of the project. <br>
> `numberOfPlannedSprints` (int, required): The number of planned sprints of the project. <br>
> `budget` (string, required): The budget of the project.

#### Responses

- Status Code: 201 (Created)
    - Response Body: AccountDTO (JSON)

> `projectCode`(string,required): The code of the project. <br>
> `name` (string, required): The name of the project. <br>
> `description` (string,required): The description of the project. <br>
> `customer` (string, required): The customer ID of the project. <br>
> `businessSector` (string, required): The business Sector ID of project. <br>
> `typology` (string, required): The typology ID of the project. <br>
> `startDate` (string, required): The start date of the project. <br>
> `endDate` (string,required): The end date of the project. <br>
> `status` (string,required): The status of the project. <br>
> `sprintDuration` (int, required): The duration of the sprins of the project. <br>
> `numberOfPlannedSprints` (int, required): The number of planned sprints of the project. <br>
> `budget` (string, required): The budget of the project.

- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)

- Status Code: 404 (Not Found)
    - Response Body: Error message (string)

| Status Code | Response Body          | Description                                          |
|-------------|------------------------|------------------------------------------------------|
| 201         | ProjectDTO (JSON)      | The project was registered successfully.             |
| 400         | Error message (string) | Project code cannot be null                          |
| 400         | Error message (string) | Project code cannot be empty                         |
| 400         | Error message (string) | ProjectCode must start with PRJ followed by a number |
| 400         | Error message (string) | Name cannot be null                                  |
| 400         | Error message (string) | Name cannot be empty                                 |
| 400         | Error message (string) | Description cannot be null                           |
| 400         | Error message (string) | Description cannot be empty                          |
| 400         | Error message (string) | Description cannot be more than 140 characters.      |
| 400         | Error message (string) | it cannot be lower than zero                         |
| 400         | Error message (string) | invalid cost value                                   |
| 400         | Error message (string) | currency cannot be null                              |
| 400         | Error message (string) | currency cannot be empty                             |
| 400         | Error message (string) | invalid currency                                     |
| 400         | Error message (string) | Not a valid date                                     |
| 400         | Error message (string) | Project already exists.                              |
| 404         | Error message (string) | CustomerID not found for creating a project          |
| 404         | Error message (string) | BusinessSectorID not found for creating a project    |
| 404         | Error message (string) | TypologyID not found for creating a project          |


### POST /projects/{projectCode}/resources"

Create and save a resource to an especific project based on the provided project code and resourceInProjectDTO

#### Request

- HTTP Method: POST
- Path: `/projects/{projectCode}/resources`
- Path Parameters:
  - `projectCode` (string): The code of the project.
- Request Body: resourcesInProjectDTO (JSON)

> `resourceInProjectID`(string,required): The ID of the resource in the project. <br>
> `email` (string, required): The email of the resource. <br>
> `role` (string, required): The role of the resource in the project. <br>
> `allocation` (double, required): The allocation of the resource in the project. <br>
> `costPerHour` (double, required): The cost per hour of the resource. <br>
> `currency` (string, required): The currency of the payment. <br>
> `startDate` (string, required): The start date of the resource in the project. <br>
> `endDate` (string,required): The end date of the resource in the project. <br>

#### Responses

- Status Code: 201 (Created)
  - Response Body: resourcesInProjectDTO (JSON)

> `projectCode`(string,required): The code of the project. <br>
> `resourceInProjectID`(string,required): The ID of the resource in the project. <br>
> `email` (string, required): The email of the resource. <br>
> `role` (string, required): The role of the resource in the project. <br>
> `allocation` (double, required): The allocation of the resource in the project. <br>
> `costPerHour` (double, required): The cost per hour of the resource. <br>
> `currency` (string, required): The currency of the payment. <br>
> `startDate` (string, required): The start date of the resource in the project. <br>
> `endDate` (string,required): The end date of the resource in the project. <br>

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)



| Status Code | Response Body          | Description                                                                   |
|-------------|------------------------|-------------------------------------------------------------------------------|
| 201         | ProjectDTO (JSON)      | The resourceInProject was registered successfully.                            |
| 400         | Error message (string) | Project code cannot be null                                                   |
| 400         | Error message (string) | Project code cannot be empty                                                  |
| 400         | Error message (string) | ProjectCode must start with PRJ followed by a number                          |
| 400         | Error message (string) | Resource in Project ID cannot be null                                         |
| 400         | Error message (string) | Resource in Project ID cannot be empty                                        |
| 400         | Error message (string) | Resource In Project ID cannot be more than 18 characters                      |
| 400         | Error message (string) | Resource In Project ID must be in the format 'resourceX', where X is a number |
| 400         | Error message (string) | Email cannot be null                                                          |
| 400         | Error message (string) | Email cannot be empty                                                         |
| 400         | Error message (string) | Email is not valid                                                            |
| 400         | Error message (string) | Role cannot be null                                                           |
| 400         | Error message (string) | Role cannot be empty                                                          |
| 400         | Error message (string) | Role is not valid, see valid roles for more information                       |
| 400         | Error message (string) | Allocation value must be between 0 and 100                                    |
| 400         | Error message (string) | invalid cost value                                                            |
| 400         | Error message (string) | currency cannot be null                                                       |
| 400         | Error message (string) | currency cannot be empty                                                      |
| 400         | Error message (string) | invalid currency                                                              |
| 400         | Error message (string) | Not a valid date                                                              |
| 400         | Error message (string) | Resource in Project not created                                               |
| 404         | Error message (string) | Project does not exists                                                       |


### GET /projects

Retrieves all projects

#### Request

- HTTP Method: GET
- Path: `/projects`


#### Response

- Status Code: 200 (OK)
  - Response Body: List of ProjectDTOs (JSON)

> [ <br>
> &emsp; `code` (string): The code of the project 1. <br>
> &emsp; `name` (string): The name of the project 1. <br>
> &emsp; `description` (string): The description of the project 1. <br>
> &emsp; `customer` (string): The customer ID of the project 1. <br>
> &emsp; `businessSector` (string): The business Sector ID of the project 1. <br>
> &emsp; `typology` (string):  The typology ID of the project 1. <br>
> &emsp; `startDate` (string): The start date of the project 1. <br>
> &emsp; `endDate` (string): The end date of the project 1. <br>
> &emsp; `status` (string): The status of the project 1. <br>
> &emsp; `sprintDuration` (int): The duration of the sprins of the project 1. <br>
> &emsp; `numberOfPlannedSprints` (int): The number of planned sprints of the project 1. <br>
> &emsp; `budget` (string): The budget of the project 1. ,<br>
> &emsp; `code` (string): The code of the project 2. <br>
> &emsp; `name` (string): The name of the project 2. <br>
> &emsp; `description` (string): The description of the project 2. <br>
> &emsp; `customer` (string): The customer ID of the project 2. <br>
> &emsp; `businessSector` (string): The business Sector ID of the project 2. <br>
> &emsp; `typology` (string):  The typology ID of the project 2. <br>
> &emsp; `startDate` (string): The start date of the project 2. <br>
> &emsp; `endDate` (string): The end date of the project 2. <br>
> &emsp; `status` (string): The status of the project 2. <br>
> &emsp; `sprintDuration` (int): The duration of the sprins of the project 2. <br>
> &emsp; `numberOfPlannedSprints` (int): The number of planned sprints of the project 2. <br>
> &emsp; `budget` (string): The budget of the project 2. ,<br>
 ,<br>
> &emsp; ... <br>
> ]


| Status Code | Response Body            | Description |
|-------------|--------------------------|-------------|
| 200         | List\<ProjectDTO> (JSON) |             |


### GET /projects/{projectCode}/resources

Retrieves all resources in a project based on the provided project code and date

#### Request

- HTTP Method: GET
- Path: `/projects/{projectCode}/resources`


#### Response

- Status Code: 200 (OK)
  - Response Body: List of ResourcesInProjectDTOs (JSON)
  -
> [ <br> 
> &emsp; `projectCode`(string,required): The code of the project 1. <br>
> &emsp; `resourceInProjectID` (string): The ID of the resource in the project 1. <br>
> &emsp; `email` (string): The email of the resource 1. <br>
> &emsp; `role` (string): The role of the resource in the project 1. <br>
> &emsp; `allocation` (string): The allocation of the resource in the project 1. <br>
> &emsp; `costPerHour` (string): The currency of the payment 1. <br>
> &emsp; `currency` (string):  The typology ID of the project 1. <br>
> &emsp; `startDate` (string): The start date of the resource in the project 1. <br>
> &emsp; `endDate` (string): The end date of the resource in the project 1. ,<br>
> &emsp; `projectCode`(string,required): The code of the project 1. <br>
> &emsp; `resourceInProjectID` (string): The ID of the resource in the project 1. <br>
> &emsp; `email` (string): The email of the resource 1. <br>
> &emsp; `role` (string): The role of the resource in the project 1. <br>
> &emsp; `allocation` (string): The allocation of the resource in the project 1. <br>
> &emsp; `costPerHour` (string): The currency of the payment 1. <br>
> &emsp; `currency` (string):  The typology ID of the project 1. <br>
> &emsp; `startDate` (string): The start date of the resource in the project 1. <br>
> &emsp; `endDate` (string): The end date of the resource in the project 1.
,<br>
> &emsp; ... <br>
> ]


| Status Code | Response Body                       | Description                                          |
|-------------|-------------------------------------|------------------------------------------------------|
| 200         | List\<ResourcesInProjectDTO> (JSON) |                                                      |
| 400         | Error message (string)              | Project code cannot be null                          |
| 400         | Error message (string)              | Project code cannot be empty                         |
| 400         | Error message (string)              | ProjectCode must start with PRJ followed by a number |
| 404         | Error message (string)              | Project does not exists                              |


## Dependencies

- `RegisterProjectService` (RegisterProjectService): The service responsible for registering projects.
- `DefineRoleService` (DefineRoleService): The service responsible for creating resource in project.
- `ListAllProjectsService` (ListAllProjectsService): The service responsible for list all projects.
- `ListResourcesService` (ListResourcesService): The service responsible for list all resources in project.
