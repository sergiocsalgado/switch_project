# US006 - As Administrator, I want to activate an user account

## 1. Requirements Engineering

### 1.1 User Story Description

As Administrator, I want to inactivate a user account.
For this, the actor will need to enter the user's email and status he want´s the account to be ("active").
If the typed email exists, the correspondent user account will change the status.

### 1.2 Customer Specifications and Clarification

*From the specification document:*

*2.3.3 User Management*

The system should have an administration area, which allows the Administrator to perform the following activities:

List all system users;

Search for users with availability of at least the following fields:

    E-mail;

    Profile;

Associate user accounts with existing profiles;

Activate and disable user accounts;

Edit other user account information.

*From client clarification:*

* n/a

### 1.3 Acceptance Criteria

* n/a

### 1.4 Found out Dependencies

* n/a

### 1.5 Input and Output Data

*Input Data:*

* *Typed data:*
    * email
    * status

* *Selected data:*
    * n/a

*Output Data:*

* Account Inactive

### 1.6 Use Case Diagram (UCD)

[UC006_ActivateUserAccount.puml](UC006_ActivateUserAccount.puml)

### 1.7 System Sequence Diagram (SSD)

[SSD006_ActivateUserAccount.puml](SSD006_ActivateUserAccount.puml)

### 1.8 Other Relevant Remarks

* n/a

## 2. OO Analysis

### 2.1 Relevant Domain Model Excerpt (DDD)

[US006_RelevantDomainModelExcerpt.puml](US006_RelevantDomainModelExcerpt.puml)

### 2.2 Other Remarks

* n/a

## 3. Design - User Story Realization

### 3.1 Rationale

| Interaction ID | Question: Which class is responsible for... | Answer                     | Justification                                                                                        |
|----------------|---------------------------------------------|----------------------------|------------------------------------------------------------------------------------------------------|
| Step 1         | ... coordinating the US?                    | AccountController          | Controller: is responsible for receiving or handling a system operation to coordinate the user story |      
| Step 3         | ... validate/collect data globally?         | ChangeStatusService        | Pure fabrication: handle the logical part of the process                                             |
| Step 4         | ... knowing the database?                   | Repository<Email, Account> | Information Expert: knows/has information about all the accounts                                     |
| Step 5         | ... shows the data?                         | Account                    | Information Expert: knows its own data                                                               |

### 3.2 Sequence Diagram

[SD006_ActivateUserAccount.puml](SD006_ActivateUserAccount.puml)

### 3.3 Class Diagram

[CD006_ActivateUserAccount.puml](CD006_ActivateUserAccount.puml)

## 4. Tests

```java
/**
 * Unit Tests class for {@link AccountController#changeStatus(ChangeStatusDTO)}  .
 */
@Test
    void inactivateAccountChangeStatusToActivate() {
            // Arrange
            String email = "isep@isep.pt";
            String status = "active";
            ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();
            changeStatusDTO.setEmailAddress(email);
            changeStatusDTO.setStatus(status);
            when(changeStatusService.changeStatus(email, status)).thenReturn(true);

            ResponseEntity<Object> expected = new ResponseEntity<>(HttpStatus.OK);

        // Act
        ResponseEntity<Object> result = controllerUnderTest.changeStatus(changeStatusDTO);

        // Assert
        assertEquals(expected.getStatusCode(), result.getStatusCode());
        }
```

## 5. Integration and Demo

* n/a

## 6. Observations

* n/a


