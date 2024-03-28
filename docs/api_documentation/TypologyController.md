# Typology Controller

The Typology class for managing Typologies.

## Constructor

### Typology Controller

Constructs a new TypologyController with the specified services.

#### Parameters

- `createTypologyService` (CreateTypologyService): The service responsible for adding Typologies.
- `listTypologiesService` (ListTypologiesService): The service responsible for listing all Typologies.

## Endpoints

### POST /typologies

Creates a new Typology.

#### Request

- HTTP Method: POST
- Path: `/typologies`
- Request Body: TypologyDTO (JSON)
> `typologyID` (string, required): The ID of the Typology. <br>
> `typologyDescription` (string): The description of the Typology.

#### Responses

- Status Code: 201 (Created)
    - Response Body: TypologyDTO (JSON)
> `typologyID` (string): The ID of the Typology. <br>
> `typologydescription` (string): The description of the Typology.


- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)


| Status Code | Response Body          | Description                                              |
|-------------|------------------------|----------------------------------------------------------|
| 201         | TypologyDTO (JSON)     | The Typology was created successfully.                   |
| 400         | Error message (string) | Could not create Typology, this Typology already exists. |
| 400         | Error message (string) | Typology ID cannot be null.                              |
| 400         | Error message (string) | Typology ID cannot be empty.                             |
| 400         | Error message (string) | Description cannot be null.                              |
| 400         | Error message (string) | Description cannot be empty.                             |


### GET /typologies

Retrieves all Typologies.

#### Request

- HTTP Method: GET
- Path: `/typologies`

#### Response

- Status Code: 200 (OK)
    - Response Body: List ofTypologyDTOs (JSON)
> [ <br>
> &emsp; `typologyID` (string): The ID of the Typology 1. <br>
> &emsp; `typologyDescription` (string): The description of the Typology 1. , <br>
> &emsp; `typologyID` (string): The ID of the Typology 2. <br>
> &emsp; `typologyIDDescription` (string): The description of the Typology 2. ,<br>
> &emsp; ... <br>
> ]


| Status Code | Response Body             | Description |
|-------------|---------------------------|-------------|
| 201         | List\<TypologyDTO> (JSON) |             |

## Dependencies

- `CreateTypologyService` (Service): Responsible for creating Typologies.
- `ListTypologiesService` (Service): Responsible for listing all Typologies.
