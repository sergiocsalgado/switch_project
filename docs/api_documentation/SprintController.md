# Sprint Controller
Sprint class for creating sprints.

## Constructor
### SprintController
Constructs a new SprintController with the specified services.

#### Parameters
- `sprintService` (SprintService): Responsible for creating sprints.
- `listAllSprintsService` (ListSprintsInProjectService): Responsible for get a list of all sprints in a specific project.
- `listSprintBacklog` (ListSprintBacklog): Responsible for get a list of all user stories in sprint backlog.
- `setSprintStatusService` (SetSprintStatusService): Responsible for set the sprint status.

## Endpoints
### POST /projects
Creates and saves a sprint based on the provided SprintDTO.

#### Request
- HTTP Method: POST
- Path: `/projects/projects/{projectCode}/sprints`
- Path Variable:
  * projectCode (String)
- Request Body: 
  * SprintDTO (JSON)

> `sprintID` (String): The ID of the sprint. <br>
> `sprintNumber` (int): The number of the sprint. <br>
> `startDate` (String): The start date of the sprint. <br>
> `endDate` (String): The end date of the sprint. <br>
> `projectCode` (String): The project code of the project to which the sprint belongs. <br>

#### Responses
- Status Code: 201 (Created)
    - Response Body: SprintDTO (JSON)

> `sprintID` (String): The ID of the sprint. <br>
> `sprintNumber` (int): The number of the sprint. <br>
> `startDate` (String): The start date of the sprint. <br>
> `endDate` (String): The end date of the sprint. <br>
> `projectCode` (String): The project code of the project to which the sprint belongs. <br>

- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)

- Status Code: 404 (Internal Server Error)
    - Response Body: Error message (string)

| Status Code | Response Body           | Description                                           |
|-------------|-------------------------|-------------------------------------------------------|
| 201         | SprintDTO (JSON)        | The sprint was created successfully.                  |
| 400         | Error message (string)  | ProjectCode must start with PRJ followed by a number. |
| 400         | Error message (string)  | Not a valid date.                                     |
| 400         | Error message (string)  | Sprint ID cannot be null.                             |
| 400         | Error message (string)  | Sprint ID cannot be empty.                            |
| 400         | Error message (string)  | Sprint Number cannot be below 0.                      |
| 400         | Error message (string)  | SprintBacklog ID cannot be null.                      |
| 400         | Error message (string)  | SprintBacklog ID cannot be empty.                     |
| 400         | Error message (string)  | Could not create sprint.                              |
| 404         | Error message (string)  | Project doesn't exist.                                |


### GET /projects
Retrieves all sprints that belong to a project.

#### Request
- HTTP Method: GET
- Path: `/projects/{projectCode}/sprints`

#### Response
- Status Code: 200 (OK)
  - Response Body: List of sprintDTOs (JSON)
  
> [ <br>
> &emsp; `sprintID` (string): The ID of the sprint; <br>
> &emsp; `sprintNumber` (int): The number of the sprint; <br>
> &emsp; `startDate` (string): The date when the sprint start; <br>
> &emsp; `endDate` (string): The date when the sprint end; <br>
> &emsp; `projectCode` (string): The code of the project where the sprint belongs to.; <br>
> ]

| Status Code | Response Body           | Description |
|-------------|-------------------------|-------------|
| 200         | List\<SprintDTO> (JSON) |             |


### GET /projects
Retrieves all user stories in sprint backlog.

#### Request
- HTTP Method: GET
- Path: `/projects/{projectCode}/sprints/{sprintID}/sprint-backlog`

#### Response
- Status Code: 200 (OK)
  - Response Body: List of UserStoryDTOs (JSON)

> [ <br>
> &emsp; `projectCode` (string): The project code; <br>
> &emsp; `userStoryID` (int): The user story ID; <br>
> &emsp; `userStoryText` (string): The user story text; <br>
> &emsp; `status` (string): The user story status; <br>
> &emsp; `userStoryNumber` (string): The user story number; <br>
> &emsp; `actor` (string): The actor of the user story; <br>
> &emsp; `priority` (int): The user story priority; <br>
> ]

| Status Code | Response Body                | Description |
|-------------|------------------------------|-------------|
| 200         | List\<UserStoryDTO> (JSON)   |             |


### PATCH /projects
Set the user stories status.

#### Request
- HTTP Method: Patch
- Path: `/{projectCode}/sprints`

#### Response
- Status Code: 200 (OK)
  - Response Body: SprintDTO (JSON)

> `sprintID` (String): The ID of the sprint. <br>
> `sprintNumber` (int): The number of the sprint. <br>
> `startDate` (String): The start date of the sprint. <br>
> `endDate` (String): The end date of the sprint. <br>
> `projectCode` (String): The project code of the project to which the sprint belongs. <br>

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Internal Server Error)
  - Response Body: Error message (string)

| Status Code | Response Body           | Description                                        |
|-------------|-------------------------|----------------------------------------------------|
| 201         | SprintDTO (JSON)        | The status of the sprint was changed successfully. |
| 400         | Error message (string)  | Sprint ID cannot be null.                          |
| 400         | Error message (string)  | Sprint ID cannot be empty.                         |
| 404         | Error message (string)  | Sprint doesn't exist.                              |


## Dependencies
- `SprintService` (Service): Responsible for creating sprints.
- `ListAllSprintsInProject` (Service): Responsible for get a list of all sprints in a specific project.
- `ListSprintBacklog` (Service): Responsible for get a list of all user stories in sprint backlog.
- `SetSprintStatusService` (Service): Responsible for set the sprint status.
