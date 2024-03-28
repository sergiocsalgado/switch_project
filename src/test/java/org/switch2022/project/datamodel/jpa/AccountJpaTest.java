package org.switch2022.project.datamodel.jpa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains a test method to ensure the constructor of AccountJpa creates a valid AccountJpa object.
 */
class AccountJpaTest {

    /**
     * Test method to ensure the existence of a no-argument constructor in AccountJpa.
     * It creates an instance using the no-argument constructor and verifies that the instance is not null.
     */
    @Test
    public void testNoArgsConstructor() {
        // Create an instance using the no-argument constructor
        //Act
        AccountJpa account = new AccountJpa();

        // Verify that the instance is not null
        //Assert
        assertNotNull(account);
    }

    /**
     * Test method to ensure that the constructor of AccountJpa creates a valid AccountJpa object.
     * It provides input values for the constructor, creates an AccountJpa object, and verifies that the object contains the expected values.
     */
    @Test
    void ensureContructorCreatesValidAccount() {
        // Arrange
        String email1 = "josemanuel@gmail.com";
        String name1 = "Jose Manuel";
        String phoneNumber1 = "912345678";
        String accountStatus1 = "ACTIVE";
        String profileID1 = "123456789";
        // Act
        AccountJpa account = new AccountJpa(email1, name1, phoneNumber1, accountStatus1, profileID1);
        // Assert
        assertEquals(email1, account.getEmail());
        assertEquals(name1, account.getName());
        assertEquals(phoneNumber1, account.getPhoneNumber());
        assertEquals(accountStatus1, account.getStatus());
        assertEquals(profileID1, account.getProfileID());
    }

    /**
     * Test class for {@link AccountJpa#setStatus(String)} .
     * Test class for {@link AccountJpa#setProfileID(String)} .
     */
    @Test
    public void testSettersAndGetters() {
        AccountJpa accountJpa = new AccountJpa();

        String status = "active";
        accountJpa.setStatus(status);
        assertEquals(status, accountJpa.getStatus());

        String profileID = "P1";
        accountJpa.setProfileID(profileID);
        assertEquals(profileID, accountJpa.getProfileID());
    }
}
