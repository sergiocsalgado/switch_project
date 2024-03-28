package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.utils.StringValidation;

import static org.junit.jupiter.api.Assertions.*;

class AccountStatusTest {

    @Test
    void exception_Null(){
        String stringToCheck=null;
        assertThrows(IllegalArgumentException.class, () -> {
                new AccountStatus(stringToCheck);
        });

    }

    @Test
    void exception_Empty(){
        String stringToCheck="";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                new AccountStatus(stringToCheck);
        });
        assertEquals("Account status cannot be empty", exception.getMessage());
    }
    @Test
    void statusDontMatchDescription_Empty(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new AccountStatus("Not active");
        });
        assertEquals("Invalid Account Status.", exception.getMessage());
    }

    @Test
    void getStatus() {
        AccountStatus accountStatus = new AccountStatus("Active");
        String expected = "active";
        assertEquals(expected,accountStatus.getStatus());
    }

    @Test
    void ensureObjectsAreEqual(){
        AccountStatus accountStatus = new AccountStatus("active");
        AccountStatus accountStatus1 = accountStatus;

        assertEquals(accountStatus,accountStatus1);
    }
    @Test
    void ensureObjectsHaveSameContent(){
        AccountStatus accountStatus = new AccountStatus("active");
        AccountStatus accountStatus1 = new AccountStatus("active");

        assertEquals(accountStatus,accountStatus1);
    }

    @Test
    void ensureObjectsAreNotEqual(){
        AccountStatus accountStatus = new AccountStatus("active");
        AccountStatus accountStatus1 = new AccountStatus("inactive");

        assertNotEquals(accountStatus,accountStatus1);
        assertNotEquals(accountStatus.getStatus(),accountStatus1.getStatus());
    }

    @Test
    void ensureObjectsAreDifferentClasses(){
        AccountStatus accountStatus = new AccountStatus("active");
        ProfileID profileIDNumber = new ProfileID("1");
        Profile profile = new Profile(profileIDNumber, new Description("manager"));


        assertNotEquals(accountStatus,profile);
    }

    @Test
    void ensureObjectsAreNotEqual_Null(){
        AccountStatus accountStatus = new AccountStatus("active");
        AccountStatus accountStatus1= null;

        assertFalse(accountStatus.sameAs(accountStatus1));
        assertNotEquals(accountStatus,accountStatus1);
    }

    @Test
    void sameHashCodeSuccess(){
        AccountStatus accountStatus = new AccountStatus("active");
        AccountStatus accountStatus1 = accountStatus;

        assertEquals(accountStatus.hashCode(),accountStatus1.hashCode());
    }
    @Test
    void sameHashCodeUnsuccessful(){
        AccountStatus accountStatus = new AccountStatus("active");
        AccountStatus accountStatus1 = new AccountStatus("inactive");

        assertNotEquals(accountStatus.hashCode(),accountStatus1.hashCode());
    }
}
