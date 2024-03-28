# Business Sector Controller

The controller class for managing business sectors.

## Constructor

### BusinessSectorController

Constructs a new BusinessSectorController with the specified services.

#### Parameters

- `addBusinessSectorService` (AddBusinessSectorService): The service responsible for adding Business Sectors.
- `listBusinessSectorsService` (ListBusinessSectorsService): The service responsible for listing all Business Sectors.

## Endpoints

### POST /business-sectors

Creates a new BusinessSector.

#### Request

- HTTP Method: POST
- Path: `/business-sectors`
- Request Body: BusinessSectorDTO (JSON)
> `businessSectorID` (string, required): The ID of the Business Sector. <br>
> `description` (string): The description of the Business Sector.

#### Responses

- Status Code: 201 (Created)
    - Response Body: BusinessSectorDTO (JSON)
> `businessSectorID` (string): The ID of the Business Sector. <br>
> `description` (string): The description of the Business Sector.


- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)


| Status Code | Response Body            | Description                                                            |
|-------------|--------------------------|------------------------------------------------------------------------|
| 201         | BusinessSectorDTO (JSON) | The Business Sector was created successfully.                          |
| 400         | Error message (string)   | Could not create Business Sector, this Business Sector already exists. |
| 400         | Error message (string)   | Business Sector ID cannot be null.                                     |
| 400         | Error message (string)   | Business Sector ID cannot be empty.                                    |
| 400         | Error message (string)   | Description cannot be null.                                            |
| 400         | Error message (string)   | Description cannot be empty.                                           |


### GET /business-sectors

Retrieves all Business Sectors.

#### Request

- HTTP Method: GET
- Path: `/business-sectors`

#### Response

- Status Code: 200 (OK)
    - Response Body: List of BusinessSectorDTOs (JSON)
> [ <br>
> &emsp; `businessSectorID` (string): The ID of the Business Sector 1. <br>
> &emsp; `description` (string): The description of the Business Sector 1. , <br>
> &emsp; `businessSectorID` (string): The ID of the Business Sector 2. <br>
> &emsp; `description` (string): The description of the Business Sector 2. ,<br>
> &emsp; ... <br>
> ]


| Status Code | Response Body                   | Description |
|-------------|---------------------------------|-------------|
| 200         | List\<BusinessSectorDTO> (JSON) |             |

## Dependencies

- `AddBusinessSectorService` (Service): Responsible for creating Business Sectors.
- `ListBusinessSectorsService` (Service): Responsible for listing all Business Sectors.
