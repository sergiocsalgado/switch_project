# US003 - As Administrator, I want to change the profile of a user account.

## 1. Requirements Engineering
### 1.1 User Story Description
As Administrator, I want to change the profile of a user account. Using two parameters, 
the email to determine the account, and the profileID to set the profile, the 
User Story will verify if the both parameters, and therefore it will change the profile.

### 1.2 Customer Specifications and Clarification
*From the specification document:* 
The system should allow the configuration of profiles associated with users, in order to restrict 
access to features. Each profile will have permissions associated with available actions and 
functionalities. A user account can have only one profile, but multiple roles. 

When registration is made, it is automatically associated with the User profile, and then the 
Administrator can change it to another available profile. 

The Administrator profile allows associating profiles with the remaining users of the system, and 
therefore an account with this profile should be automatically created at the time the system is 
installed. 

The Manager is a profile that allows the user to access all projects and manage resources. A Manager 
does not participate in projects.

The User profile corresponds to the resources that perform tasks/effort in the activities of the 
projects.


*From client clarification:*
>- Question: The roles Team Member, Scrum master(SM), Project manager(PM) e Product owner(PO) are 
>specific functions?
>Or the SM, PM and PO are also Team Members?
>
>
>
>- Answer: The SM, PM and PO are not Team Members.


### 1.3 Acceptance Criteria
* N/A.

### 1.4 Found out Dependencies
* To be able to change the profile of a user account, the following dependencies where found:
  * US001 - As Administrator, I want to create user profiles.
  * US002 - As Administrator, I want to register a user.

### 1.5 Input and Output Data
*Input data:*
* *Typed data:*
  * email
  * profileID
* *Selected data:*
  * N/A

*Output Data:*
* Response with the result of the operation, and info about the account where the profile was changed.
* Info about the account includes:
  * email
  * name
  * phoneNumber
  * status

### 1.6 Use case Diagram (UCD)
[UC003_SetUserProfile.puml](UC003_SetUserProfile.puml)

### 1.7 System Sequence Diagram (SSD)
[SSD003_SetUserProfile.puml](SSD003_SetUserProfile.puml)


## 2. OO Analysis
### 2.1 Relevant Domain Model Excerpt (DDD)
[DM003_SetUserProfile_DDD.puml](DM003_SetUserProfile_DDD.puml)


## 3. Design - User Story Realization
### 3.1 Rationale
| Interaction ID | Question: Which class is responsible for...                        | Answer                   | Justification                                                                                                |
|----------------|--------------------------------------------------------------------|--------------------------|--------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                                    | UI                       | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model |
| Step 2         | ... interacting with the UI layer and coordinating the user story? | AccountController        | The Controller is responsible for receiving or handling a system operation to coordinate the user story      |
| Step 3         | ... validate/collect data globally?                                | SetAccountProfileService | Pure fabrication: handle the logical part of the process                                                     |
| Step 4         | ... knowing the Profiles of the system?                            | ProfileRepository        | Information Expert: knows every profile of the system                                                        |
| Step 5         | ... knowing the Accounts of the system?                            | AccountRepository        | Information Expert: knows every account of the system                                                        |
| Step 6         | ... maps the data?                                                 | AccountMapper            | Pure fabrication: maps the domain objects to DTOs                                                            |
| Step 7         | ... providing data to the UI?                                      | AccountController        | Controller: returns to the UI the operation result                                                           |
| Step 8         | ... providing data to the Actor?                                   | UI                       | Information Expert: Is responsible for user interactions                                                     |


### 3.2 Sequence Diagram
[SD003_SetUserProfile.puml](SD003_SetUserProfile.puml)

### 3.3 Class Diagram
[CD003_SetUserProfile.puml](CD003_SetUserProfile.puml)

## 4. Tests
* *Controller Unit Test Success*
```java
@Test
void setAccountProfile_ensureProfileOfAccountIsChangedWithValidGivenParameters_Unit() {
    //Arrange
    String emailInput = "email@email.com";
    String profileIdInput = "Profile1";
    String profileIdInput = "Profile1";

    String nameAccount = "name";
    String phoneAccount = "912345678";
    String statusAccount = "active";

    when(account.getEmail()).thenReturn(email);
    when(account.getName()).thenReturn(name);
    when(account.getPhoneNumber()).thenReturn(phoneNumber);
    when(account.getStatus()).thenReturn(accountStatus);
    when(account.getProfileID()).thenReturn(profileId);

    when(email.getEmail()).thenReturn(emailInput);
    when(name.getValue()).thenReturn(nameAccount);
    when(phoneNumber.getPhoneNumber()).thenReturn(phoneAccount);
    when(accountStatus.getStatus()).thenReturn(statusAccount);
    when(profile.getProfileID()).thenReturn(profileId);
    when(profileId.getProfileID()).thenReturn(profileIdInput);

    AccountDTO expectedAccountDTO = new AccountDTO();
    expectedAccountDTO.setEmail(emailInput);
    expectedAccountDTO.setName(nameAccount);
    expectedAccountDTO.setPhoneNumber(phoneAccount);
    expectedAccountDTO.setStatus(statusAccount);

    when(setAccountProfileService.changeProfile(emailInput, profileIdInput)).thenReturn(account);

    ResponseEntity<Object> expected = new ResponseEntity<>(expectedAccountDTO, HttpStatus.OK);

    try (MockedStatic<AccountMapper> mapperDouble = Mockito.mockStatic(AccountMapper.class)) {
        mapperDouble.when(() -> AccountMapper.toDTO(account)).thenReturn(expectedAccountDTO);

        //Act
        ResponseEntity<Object> result = controllerUnderTest.setAccountProfile(emailInput, profileIdInput);

        //Assert
        assertEquals(expected, result);
    }
}
```

* *Service Unit Test Success*
```java
@Test
void changeProfile_shouldReturnAValidAccountDTOWhenTheEmailAndProfileIDAreValidAndExistent_Unit() {
  // Arrange
  String emailInput = "email@email.com";
  String profileId = "P1";

        ProfileID profileID1 =  new ProfileID(profileId);
        Email email1 = new Email(emailInput);

        when(profile.getProfileID()).thenReturn(profileID1);
        when(profileRepository.containsOfIdentity(profileID1)).thenReturn(true);

        Optional<Account> accountOptional = mock(Optional.class);
        when(accountOptional.isPresent()).thenReturn(true);
        when(accountOptional.get()).thenReturn(account);
        when(accountRepository.ofIdentity(email1)).thenReturn(accountOptional);

        when(account.setProfile(profileID1)).thenReturn(true);
        when(accountRepository.save(account)).thenReturn(account);

        //Act
        Account accountResult = serviceUnderTest.changeProfile(emailInput, profileId);

        //Assert
        assertEquals(account, accountResult);
  }
```

## 5. Observations
* It will be necessary to ensure that only the administrator can change the profiles.
