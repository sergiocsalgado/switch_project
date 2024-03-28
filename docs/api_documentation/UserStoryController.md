# UserStory Controller
Sprint class responsible for:
* Creating user stories
* Moving a user story from Product Backlog to Sprint Backlog
* List Product Backlog
* View Scrum Board

## Constructor
### UserStoryController
Constructs a new UserStoryController with the specified service.

#### Parameters
- `userStoryService` (CreateUserStoryService): Responsible for creating a user story.
- `addUserStoryToSprintBacklogService` (AddUserStoryToSprintBacklogService): Responsible for adding a user story to the product backlog.
- `listUserStoriesService` (ListUserStoriesService): Responsible for get a list of user stories in a specific project.
- `viewScrumBoardService` (ViewScrumBoardService): Responsible for get a list of user stories with their status in a specific project.
- `updateUserStoryStatusService` (UpdateUserStoryStatusService): Responsible for update the user story status in scrum board.
- `updateProductBacklogService` (UpdateProductBacklogService): Responsible for update the product backlog with the unfinished user stories in the sprint.

## Endpoints
### POST /projects
Creates and saves a user story based on the provided UserStoryDTO.

#### Request
- HTTP Method: POST
- Path: `/projects/{projectCode}/product-backlog`
- Path Variable:
    * projectCode (String)
- Request Body:
    * UserStoryDTO (JSON)

> `projectCode` (String): The project code of the project to which the user story belongs. <br>
> `userStoryID` (String): The ID of the user story. <br>
> `userStoryNumber` (int): The number of the user story. <br>
> `actor` (String): The name of the actor in the user story. <br>
> `userStoryText` (String): The description of the user story. <br>
> `priority` (String): The priority of the user story. <br>

#### Responses
- Status Code: 201 (Created)
    - Response Body: UserStoryDTO (JSON)

> `projectCode` (String): The project code of the project to which the user story belongs. <br>
> `userStoryID` (String): The ID of the user story. <br>
> `userStoryNumber` (int): The number of the user story. <br>
> `actor` (String): The name of the actor in the user story. <br>
> `userStoryText` (String): The description of the user story. <br>
> `priority` (String): The priority of the user story. <br>
> `status` (String): The status of the user story.

- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)

| Status Code | Response Body             | Description                                           |
|-------------|---------------------------|-------------------------------------------------------|
| 200         | UserStoryDTO (JSON)       | The user story was created successfully.              |
| 400         | Error message (string)    | Project code cannot be null                           |
| 400         | Error message (string)    | Project code cannot be empty                          |
| 400         | Error message (string)    | User story ID cannot contain special characters.      |
| 400         | Error message (string)    | User story ID cannot be null                          |
| 400         | Error message (string)    | User story ID cannot be empty                         |
| 400         | Error message (string)    | UserStoryNumber must be a positive number.            |
| 400         | Error message (string)    | Actor cannot be null                                  |
| 400         | Error message (string)    | Actor cannot be empty                                 |
| 400         | Error message (string)    | Description cannot be more than 140 characters.       |
| 400         | Error message (string)    | Description cannot be null                            |
| 400         | Error message (string)    | Description cannot be empty                           |
| 400         | Error message (string)    | description cannot be null                            |
| 400         | Error message (string)    | description cannot be empty                           |
| 400         | Error message (string)    | description is not valid.                             |
| 400         | Error message (string)    | Priority cannot be lower than one.                    |
| 400         | Error message (string)    | UserStory already exist.                              |
| 404         | Error message (string)    | Project does not exist.                               |
| 404         | Error message (string)    | ProjectCode does not exist!                           |

### PATCH /projects
Moves a user story from the product backlog to the sprint backlog of the project.

#### Request
- HTTP Method: PATCH
- Path: `/{projectCode}/product-backlog/{userStoryID}/{sprintID}`
- Path Variable:
  * projectCode (String)
  * userStoryID (String)
  * sprintID (String)
  * 
#### Responses
- Status Code: 200 (OK)
  - Response Body: status (boolean)

> `true` (boolean): If the user story has been moved from the product backlog to the sprint backlog of the project. <br>

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)


| Status Code | Response Body          | Description                                           |
|-------------|------------------------|-------------------------------------------------------|
| 200         | Boolean                | True                                                  |
| 400         | Error message (String) | User Story ID cannot be null.                         |
| 400         | Error message (String) | User Story ID cannot be empty.                        |
| 400         | Error message (string) | User story ID cannot contain special characters.      |
| 400         | Error message (String) | Sprint ID cannot be null.                             |
| 400         | Error message (String) | Sprint ID cannot be empty.                            |
| 400         | Error message (String) | Project code cannot be null.                          |
| 400         | Error message (String) | Project code cannot be empty.                         |


### GET /projects
Retrieves the Product Backlog that belong to a project.

#### Request
- HTTP Method: GET
- Path: `/projects/{projectCode}/product-backlog"`
- Path Variable:
* projectCode (String)

#### Response
- Status Code: 200 (OK)
    - Response Body: List of UserStoryDTO (JSON)

> [ <br>
> &emsp; `userStoryID` (string): The ID of the user story; <br>
> &emsp; `projectCode` (string): The code of the project where the user story belongs to. <br>
> &emsp; `userStoryNumber` (int): The number of the user story; <br>
> &emsp; `actor` (string): The actor of the user story; <br>
> &emsp; `userStoryText` (string): The description of the user story; <br>
> &emsp; `status` (string): The status of the user story; <br>
> &emsp; `priority` (string): The priority of the user story; <br>
> ]

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

| Status Code | Response Body              | Description                                           |
|-------------|----------------------------|-------------------------------------------------------|
| 200         | List\<UserStoryDTO> (JSON) |                                                       |
| 400         | Error message (String)     | Project code cannot be null.                          |
| 400         | Error message (String)     | Project code cannot be empty.                         |


### GET /projects
Retrieves Scrum Board that belong to a project.

#### Request
- HTTP Method: GET
- Path: `/projects/{projectCode}/scrum-board"`
- Path Variable:
* projectCode (String)

#### Response
- Status Code: 200 (OK)
  - Response Body: List of UserStoryDTO (JSON)

> [ <br>
> &emsp; `userStoryID` (string): The ID of the user story; <br>
> &emsp; `projectCode` (string): The code of the project where the user story belongs to. <br>
> &emsp; `userStoryNumber` (int): The number of the user story; <br>
> &emsp; `actor` (string): The actor of the user story; <br>
> &emsp; `userStoryText` (string): The description of the user story; <br>
> &emsp; `status` (string): The status of the user story; <br>
> &emsp; `priority` (string): The priority of the user story; <br>
> ]

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)

| Status Code | Response Body              | Description                                           |
|-------------|----------------------------|-------------------------------------------------------|
| 200         | List\<UserStoryDTO> (JSON) |                                                       |
| 400         | Error message (String)     | Project code cannot be null.                          |
| 400         | Error message (String)     | Project code cannot be empty.                         |
| 404         | Error message (String)     | There is no sprint going on.                          |
| 404         | Error message (String)     | Project does not exists.                              |


### PATCH /projects
Change the user story status.

#### Request
- HTTP Method: PATCH
- Path: `/{projectCode}/scrum-board/{userStoryID}`
- Path Variable:
  * projectCode (String)
  * userStoryID (String)
- Request Body:
  * UserStoryDTO (JSON)

> `projectCode` (String): The project code of the project to which the user story belongs. <br>
> `userStoryID` (String): The ID of the user story. <br>
> `userStoryNumber` (int): The number of the user story. <br>
> `actor` (String): The name of the actor in the user story. <br>
> `userStoryText` (String): The description of the user story. <br>
> `priority` (String): The priority of the user story. <br>
> `status` (String): The status of the user story.

#### Responses
- Status Code: 200 (OK)
  - Response Body: UserStoryDTO (JSON)

> `projectCode` (String): The project code of the project to which the user story belongs. <br>
> `userStoryID` (String): The ID of the user story. <br>
> `userStoryNumber` (int): The number of the user story. <br>
> `actor` (String): The name of the actor in the user story. <br>
> `userStoryText` (String): The description of the user story. <br>
> `priority` (String): The priority of the user story. <br>
> `status` (String): The status of the user story.

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)


| Status Code | Response Body          | Description                                      |
|-------------|------------------------|--------------------------------------------------|
| 200         | UserStoryDTO (JSON)    |                                                  |
| 400         | Error message (String) | User Story ID cannot be null.                    |
| 400         | Error message (String) | User Story ID cannot be empty.                   |
| 400         | Error message (string) | User story ID cannot contain special characters. |
| 400         | Error message (String) | Project code cannot be null.                     |
| 400         | Error message (String) | Project code cannot be empty.                    |
| 404         | Error message (String) | User Story does not exists.                      |
| 404         | Error message (String) | Project does not exists.                         |


### PATCH /projects
Update the product backlog with the unfinished user stories in the sprint.

#### Request
- HTTP Method: PATCH
- Path: `/{projectCode}/product-backlog`
- Path Variable:
  * projectCode (String)

#### Responses
- Status Code: 200 (OK)
  - Response Body: List of UserStoryDTO (JSON)

> [ <br>
> &emsp; `userStoryID` (string): The ID of the user story; <br>
> &emsp; `projectCode` (string): The code of the project where the user story belongs to. <br>
> &emsp; `userStoryNumber` (int): The number of the user story; <br>
> &emsp; `actor` (string): The actor of the user story; <br>
> &emsp; `userStoryText` (string): The description of the user story; <br>
> &emsp; `status` (string): The status of the user story; <br>
> &emsp; `priority` (string): The priority of the user story; <br>
> ]
> 
- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)

| Status Code | Response Body              | Description                   |
|-------------|----------------------------|-------------------------------|
| 200         | List\<UserStoryDTO> (JSON) |                               |
| 400         | Error message (String)     | Project code cannot be null.  |
| 400         | Error message (String)     | Project code cannot be empty. |
| 404         | Error message (String)     | Project does not exists.      |


## Dependencies
- `CreateUserStoryService` (Service): Responsible for creating user stories.
- `AddUserStoryToSprintBacklogService` (Service): Responsible to move user stories to Sprint Backlog.
- `ListUserStoriesService` (Service): Responsible for get a list of Product Backlog in a specific project.
- `ViewScrumBoardService` (Service): Responsible for get a list of all Scrum Board in a specific project.
- `UpdateUserStoryStatusService` (Service): Responsible for update the user story status in scrum board.
- `UpdateProductBacklogService` (Service): Responsible for update the product backlog with the unfinished user stories in the sprint.
