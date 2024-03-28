# Profile Controller

The controller class for managing profiles.

## Constructor

### ProfileController

Constructs a new ProfileController with the specified services.

#### Parameters

- `createProfileService` (CreateProfileService): The service responsible for creating profiles.
- `listAllProfilesService` (ListAllProfilesService): The service responsible for listing all profiles.

## Endpoints

### POST /profiles

Creates a new profile.

#### Request

- HTTP Method: POST
- Path: `/profiles`
- Request Body: ProfileDTO (JSON)
> `profileID` (string, required): The ID of the profile. <br>
> `description` (string): The description of the profile.

#### Responses

- Status Code: 201 (Created)
    - Response Body: ProfileDTO (JSON)
> `profileID` (string): The ID of the profile. <br>
> `description` (string): The description of the profile.


- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)


| Status Code | Response Body          | Description                                            |
|-------------|------------------------|--------------------------------------------------------|
| 201         | ProfileDTO (JSON)      | The profile was created successfully.                  |
| 400         | Error message (string) | Could not create Profile, this profile already exists. |
| 400         | Error message (string) | Profile ID cannot be null.                             |
| 400         | Error message (string) | Profile ID cannot be empty.                            |
| 400         | Error message (string) | Description cannot be null.                            |
| 400         | Error message (string) | Description cannot be empty.                           |
| 400         | Error message (string) | Description cannot be more than 140 characters.        |


### GET /profiles

Retrieves all profiles.

#### Request

- HTTP Method: GET
- Path: `/profiles`

#### Response

- Status Code: 200 (OK)
    - Response Body: List of ProfileDTOs (JSON)
> [ <br>
> &emsp; `profileID` (string): The ID of the profile 1. <br>
> &emsp; `description` (string): The description of the profile 1. , <br>
> &emsp; `profileID` (string): The ID of the profile 2. <br>
> &emsp; `description` (string): The description of the profile 2. ,<br>
> &emsp; ... <br>
> ] 


| Status Code | Response Body            | Description |
|-------------|--------------------------|-------------|
| 200         | List\<ProfileDTO> (JSON) |             |

## Dependencies

- `CreateProfileService` (Service): Responsible for creating profiles.
- `ListAllProfilesService` (Service): Responsible for listing all profiles.
