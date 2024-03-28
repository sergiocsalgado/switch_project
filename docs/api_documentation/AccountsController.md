# Account Controller

Controller class for registering and managing user accounts.

## Constructor

### AccountController

Constructs a new AccountController with the specified services.

#### Parameters

- `registerAccountService` (RegisterAccountService): The service responsible for registering accounts.
- `setAccountProfileService` (SetAccountProfileService): The service responsible for setting account profiles.
- `changeStatusService` (ChangeStatusService): The service responsible for setting account status.
- `accountsAndStatusService` (AccountsAndStatusService): The service responsible for listing the accounts.

## Endpoints

### POST /accounts

Creates and saves an account based on the provided AccountDTO.

#### Request

- HTTP Method: POST
- Path: `/accounts`
- Request Body: AccountDTO (JSON)

> `email` (string, required): The email address of the account. <br>
> `name` (string, required): The name of the account holder. <br>
> `phoneNumber` (string): The phone number of the account holder. <br>
> `status` (string): The status of the account.

#### Responses

- Status Code: 201 (Created)
  - Response Body: AccountDTO (JSON)

> `email` (string, required): The email address of the account. <br>
> `name` (string, required): The name of the account holder. <br>
> `phoneNumber` (string): The phone number of the account holder. <br>
> `status` (string): The status of the account.

- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)

- Status Code: 404 (Not Found)
    - Response Body: Error message (string)

| Status Code | Response Body          | Description                                      |
|-------------|------------------------|--------------------------------------------------|
| 201         | AccountDTO (JSON)      | The account was created successfully.            |
| 400         | Error message (string) | Email cannot be null.                            |
| 400         | Error message (string) | Email cannot be empty.                           |
| 400         | Error message (string) | Email is not valid.                              |
| 400         | Error message (string) | Name cannot be null.                             |
| 400         | Error message (string) | Name cannot be empty.                            |
| 400         | Error message (string) | Phone Number cannot be null.                     |
| 400         | Error message (string) | Phone Number cannot be empty.                    |
| 400         | Error message (string) | The phone number should have nine digits.        |
| 400         | Error message (string) | The phone number can only contain numbers.       |
| 400         | Error message (string) | The phone number can only start with a 6 or a 9. |
| 400         | Error message (string) | Account status cannot be null.                   |
| 400         | Error message (string) | Account status cannot be empty.                  |
| 400         | Error message (string) | Invalid Account Status.                          |
| 404         | Error message (string) | User profile not found for creating an account.  |


### PATCH /accounts/{emailAddress}

Sets the profile of an account.

#### Request

- HTTP Method: PATCH
- Path: `/accounts/{emailAddress}/profile`
- Path Parameters:
    - `emailAddress` (string): The email address of the account.
- Request Body: Profile ID (string)
    - `profileID` (string): The profile ID to set to the account.

#### Responses

- Status Code: 200 (OK)
    - Response Body: AccountDTO (JSON)

> `email` (string): The email address of the account.
> `name` (string): The name of the account.
> `phoneNumber` (string): The phone number of the account.
> `status` (string): The status of the account.

- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)

- Status Code: 404 (Not Found)
    - Response Body: Error message (string)

| Status Code | Response Body          | Description                               |
|-------------|------------------------|-------------------------------------------|
| 201         | AccountDTO (JSON)      | The account was created successfully.     |
| 400         | Error message (string) | Email cannot be null.                     |
| 400         | Error message (string) | Email cannot be empty.                    |
| 400         | Error message (string) | Email is not valid.                       |
| 400         | Error message (string) | Profile ID cannot be null.                |
| 400         | Error message (string) | Profile ID cannot be empty.               |
| 404         | Error message (string) | Account with that email does not exists.  |
| 404         | Error message (string) | User profile does not exist.              |

### PATCH /accounts/emailAddress

Sets the status of an account.

#### Request

- HTTP Method: PATCH
- Path: `/accounts/emailAddress/status`
- Request Body: ChangeStatusDTO (JSON)

> `email` (string, required): The email address of the account. <br>
> `status` (string, required): The status to set the account. <br>

#### Responses

- Status Code: 200 (OK)
  - Response Body: {}

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)

| Status Code | Response Body          | Description                              |
|-------------|------------------------|------------------------------------------|
| 200         | {}                     |                                          |
| 400         | Error message (string) | Email cannot be null.                    |
| 400         | Error message (string) | Email cannot be empty.                   |
| 400         | Error message (string) | Email is not valid.                      |
| 400         | Error message (string) | Account status cannot be null.           |
| 400         | Error message (string) | Account status cannot be empty.          |
| 400         | Error message (string) | Invalid Account Status.                  |
| 404         | Error message (string) | Account with that email does not exists. |

### PATCH /accounts

Sets the status of an account.

#### Request

- HTTP Method: GET
- Path: `/accounts`
- Request Body: 
  -  {}

#### Responses

- Status Code: 200 (OK)
  - Response Body: List\<AccountDTO> (JSON)
> [ <br>
> &emsp; `email` (string): The email of the account 1. <br>
> &emsp; `name` (string): The description of the account 1. <br>
> &emsp; `phoneNumber` (string): The phone number of the account 1. <br>
> &emsp; `status` (string): The status of the account 1. , <br>
> &emsp; `email` (string): The email of the account 2. <br>
> &emsp; `name` (string): The description of the account 2. <br>
> &emsp; `phoneNumber` (string): The phone number of the account 2. <br>
> &emsp; `status` (string): The status of the account 2.,
> &emsp; ... <br>
> ]

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)

| Status Code | Response Body            | Description                              |
|-------------|--------------------------|------------------------------------------|
| 200         | List\<AccountDTO> (JSON) |                                          |


## Dependencies

- `RegisterAccountService` (Service): Responsible for registering accounts.
- `SetAccountProfileService` (Service): Responsible for setting account profiles.
- `ChangeStatusService` (Service): Responsible for setting account status.
- `AccountsAndStatusService` (Service): Responsible for listing the accounts.
