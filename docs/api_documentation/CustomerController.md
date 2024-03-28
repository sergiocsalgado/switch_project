# Customer Controller

The controller class for creating customers.

## Constructor

### CustomerController

Constructs a new CustomerController with the specified services.

#### Parameters

- `customerService` (CreateCustomerService): The service responsible for creating and customers.
- `listAllCustomersService` (ListAllCustomersService): The service responsible for listing all the customers.

## Endpoints

### POST /customers

Create and add a new customer.

### Request

- HTTP Method: POST
- Path: /customers
- Request Body: CustomerDTO (JSON)

> `customerID` (string, required): The ID of the customer.
> `name` (string): The name of the customer.
> `nif` (string): The nif of the customer.

#### Responses

- Status Code: 201 (Created)
    - Response Body: CustomerDTO (JSON)

> `customerID` (string, required): The ID of the customer.
> `name` (string): The name of the customer.
> `nif` (string): The nif of the customer.

- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)

| Status Code | 	Response Body          | 	Description                                              |
|-------------|-------------------------|-----------------------------------------------------------|
| 201         | 	CustomerDTO (JSON)     | 	The customer was created successfully.                   |
| 400         | 	Error message (string) | 	Could not create Customer, this customer already exists. |
| 400         | 	Error message (string) | 	Customer ID cannot be null.                              |
| 400         | 	Error message (string) | 	Customer ID cannot be more than 20 characters.           |
| 400         | 	Error message (string) | 	Name cannot be null.                                     |
| 400         | 	Error message (string) | 	Nif cannot be null.                                      |
| 400         | 	Error message (string) | 	Nif cannot be more than 9 characters.                    |

### GET /Customers

Retrieves all Customers

#### Request

- HTTP Method: GET
- Path: `/customers`

#### Response

- Status Code: 200 (OK)
  - Response Body: List of CustomerDTOs (JSON)
> [ <br>
> &emsp; `customerID` (string): The ID of the Customer 1. <br>
> &emsp; `name` (string): The name of the Customer 1. , <br>
> &emsp; `nif` (string): The nif of the Customer 1. , <br>
> &emsp; `customerID` (string): The ID of the Customer 1. <br>
> &emsp; `name` (string): The name of the Customer 1. , <br>
> &emsp; `nif` (string): The nif of the Customer 1. , <br>
> &emsp; ... <br>
> ]


| Status Code | Response Body             | Description |
|-------------|---------------------------|-------------|
| 200         | List\<CustomerDTO> (JSON) |             |


## Dependencies

- `CustomerService` (Service): Responsible for creating customers.
- `ListAllCustomersService` (Service): Responsible for listing all the customers.