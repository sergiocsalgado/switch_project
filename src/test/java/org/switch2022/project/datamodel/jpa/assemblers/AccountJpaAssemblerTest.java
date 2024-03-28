package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.datamodel.jpa.AccountJpa;
import org.switch2022.project.model.human_resource.Account;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * This class contains test methods to verify the behavior of the AccountJpaAssembler class.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AccountJpaAssembler.class
)
class AccountJpaAssemblerTest {

    @MockBean
    AccountJpa accountJpa;

    @MockBean
    Account account;

    @MockBean
    Email email;

    @MockBean
    Name name;

    @MockBean
    PhoneNumber phoneNumber;

    @MockBean
    AccountStatus accountStatus;

    @MockBean
    ProfileID profileID;

    /**
     * Test method to ensure that the toDataModel() method of AccountJpaAssembler converts an Account object to an AccountJpa object.
     * It mocks the necessary dependencies, creates an Account object, and verifies that the converted AccountJpa object contains the expected values.
     */
    @Test
    void ensureGetAccountToDataModel() {
        // Arrange
        String email1 = "josemanuel@gmail.com";
        String name1 = "Jose Manuel";
        String phoneNumber1 = "912345678";
        String accountStatus1 = "ACTIVE";
        String profileID1 = "123456789";

        when(account.getEmail()).thenReturn(email);
        when(account.getName()).thenReturn(name);
        when(account.getPhoneNumber()).thenReturn(phoneNumber);
        when(account.getStatus()).thenReturn(accountStatus);
        when(account.getProfileID()).thenReturn(profileID);

        when(email.getEmail()).thenReturn(email1);
        when(name.getValue()).thenReturn(name1);
        when(phoneNumber.getPhoneNumber()).thenReturn(phoneNumber1);
        when(accountStatus.getStatus()).thenReturn(accountStatus1);
        when(profileID.getProfileID()).thenReturn(profileID1);
        // Act
        AccountJpa accountJpa = AccountJpaAssembler.toDataModel(account);
        // Assert
        assertEquals(email1, accountJpa.getEmail());
        assertEquals(name1, accountJpa.getName());
        assertEquals(phoneNumber1, accountJpa.getPhoneNumber());
        assertEquals(accountStatus1, accountJpa.getStatus());
        assertEquals(profileID1, accountJpa.getProfileID());
    }

    /**
     * Test method to ensure that the toDomain() method of AccountJpaAssembler converts an AccountJpa object to an Account object.
     * It mocks the necessary dependencies, creates an AccountJpa object, and verifies that the converted Account object contains the expected values.
     */
    @Test
    void ensureGetAccountToDomain() {
        // Arrange
        String email = "josemanuel@gmail.com";
        String name = "Jose Manuel";
        String phoneNumber = "912345678";
        String accountStatus = "active";
        String profileID = "123456789";

        when(accountJpa.getEmail()).thenReturn(email);
        when(accountJpa.getName()).thenReturn(name);
        when(accountJpa.getPhoneNumber()).thenReturn(phoneNumber);
        when(accountJpa.getStatus()).thenReturn(accountStatus);
        when(accountJpa.getProfileID()).thenReturn(profileID);
        // Act
        Account account = AccountJpaAssembler.toDomain(accountJpa);
        // Assert
        assertEquals(email, account.getEmail().getEmail());
        assertEquals(name, account.getName().getValue());
        assertEquals(phoneNumber, account.getPhoneNumber().getPhoneNumber());
        assertEquals(accountStatus, account.getStatus().getStatus());
        assertEquals(profileID, account.getProfileID().getProfileID());
    }
}