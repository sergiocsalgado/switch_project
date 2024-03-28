# US001 - As Administrator, I want to create user Profiles.

## 1. Requirements Engineering

### 1.1 User Story Description

#### As Administrator, I want to create user Profiles.

To be able to create a user profile, the administrator will need to enter a description ("User" or "Manager").

### 1.2 Customer Specifications and Clarification

From the specification document:

2.1 User Profiles and Roles

The system should allow the configuration of profiles associated with users, in order to restrict
access to features. Each profile will have permissions associated with available actions and
functionalities. A user account can have only one profile, but multiple roles.
When registration is made, it is automatically associated with the User profile, and then the
Administrator can change it to another available profile.
The Administrator profile allows associating profiles with the remaining users of the system,
and therefore an account with this profile should be automatically created at the time the
system is installed.
The Manager is a profile that allows the user to access all projects and manage resources. A
Manager doesn’t participate in projects.
The User profile corresponds to the resources that perform tasks/effort in the activities of the
projects.
The Project Manager, Product Owner, Scrum Master and Team Member are roles the users
have in a particular project over a certain period. For example, when creating a project on the
system, a user is defined as the manager of that project, making that user Project Manager,
keeping the other roles the user may have in the other projects to which he is assigned.

### 1.3 Accepted Criteria

n/a

### 1.4 Found out Dependencies

* This first user story has no dependency with another user story
* as it is the first step of the process

### 1.5 Input and Output Data

*Input Data:*
* Description

*Output Data:*
* Profile created

### 1.6 Use-Case Diagram (UCD)

![UC001_CreateUserProfile_v2.0.png](Images%2FUC001_CreateUserProfile_v2.0.png)

### 1.7 System Sequence Diagram (SSD)

![SSD001_CreateUserProfile_v2.0.png](Images%2FSSD001_CreateUserProfile_v2.0.png)


## 2. OO Analysis
### 2.1 Relevant Domain Model Excerpt


### 2.2 Other Remarks

n/a

## 3. Design - User Story Realization

### 3.1 Rationale

### 3.2 Sequence Diagram

![SD001_CreateUserProfile_v2.0.png](Images%2FSD001_CreateUserProfile_v2.0.png)


### 3.3 Class Diagram

![CD001_CreateUserProfile_v2.0.png](Images%2FCD001_CreateUserProfile_v2.0.png)

## 4. Tests

* *Success*

![Test_Success.png](Images%2FTest_Success.png)

* *Fail*

![Test_Fail.png](Images%2FTest_Fail.png)