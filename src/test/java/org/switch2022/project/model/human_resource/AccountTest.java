package org.switch2022.project.model.human_resource;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccountTest {

    @Test
    void ensureThrowExceptionForNullEmailSuccessWithProfileMock() {
        //Arrange; Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            ProfileID prf = mock(ProfileID.class);
            Name name = mock(Name.class);
            PhoneNumber phoneNumber = mock(PhoneNumber.class);
            AccountStatus accountStatus = mock(AccountStatus.class);

            new Account(null, name, phoneNumber, accountStatus, prf);
        });

        //Assert
        assertEquals("Email cannot be null", exception.getMessage());
    }
    @Test
    void ensureThrowExceptionForNullNameSuccessWithProfileMock() {
        //Arrange; Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Email email = mock(Email.class);
            ProfileID prf = mock(ProfileID.class);
            PhoneNumber phoneNumber = mock(PhoneNumber.class);
            AccountStatus accountStatus = mock(AccountStatus.class);

            new Account(email, null, phoneNumber, accountStatus, prf);
        });

        //Assert
        assertEquals("Name cannot be null", exception.getMessage());
    }
    @Test
    void ensureThrowExceptionForNullPhoneNumberSuccessWithProfileMock() {
        //Arrange; Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Email email = mock(Email.class);
            Name name = mock(Name.class);
            ProfileID prf = mock(ProfileID.class);
            AccountStatus accountStatus = mock(AccountStatus.class);

            new Account(email, name, null, accountStatus, prf);
        });

        //Assert
        assertEquals("Phone number cannot be null", exception.getMessage());
    }
    @Test
    void ensureThrowExceptionForNullAccountStatusSuccessWithProfileMock() {
        //Arrange; Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Email email = mock(Email.class);
            Name name = mock(Name.class);
            PhoneNumber phoneNumber = mock(PhoneNumber.class);
            ProfileID prf = mock(ProfileID.class);

            new Account(email, name, phoneNumber, null, prf);
        });

        //Assert
        assertEquals("Status cannot be null", exception.getMessage());
    }
    @Test
    void ensureThrowExceptionForNullAProfileSuccessWithProfileMock() {
        //Arrange; Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Email email = mock(Email.class);
            Name name = mock(Name.class);
            PhoneNumber phoneNumber = mock(PhoneNumber.class);
            AccountStatus accountStatus = mock(AccountStatus.class);


            new Account(email, name, phoneNumber, accountStatus, null);
        });

        //Assert
        assertEquals("ProfileID cannot be null", exception.getMessage());
    }


    @Test
    void ensureObjectIsInstanceOfClassSuccessWithProfileMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        //Act
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Assert
        assertInstanceOf(Account.class, acc);
    }

    @Test
    void ensureObjectIsNotInstanceOfClassSuccessWithProfileMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        //Act
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Assert
        assertNotEquals(acc, prf);
    }

    @Test
    void ensureGetEmailSuccessWithProfileMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Email expected = email;

        //Act
        Email result = acc.getEmail();

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void ensureGetNameSuccessWithProfileMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Name expected = name;

        //Act
        Name result = acc.getName();

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void ensureGetPhoneNumberSuccessWithProfileMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        PhoneNumber expected = phoneNumber;

        //Act
        PhoneNumber result = acc.getPhoneNumber();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetStatusSuccessWithProfileMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        AccountStatus expected = accountStatus;

        //Act
        AccountStatus result = acc.getStatus();

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void ensureSetNameSetsNewNameWithProfileMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Act
        Name nameSet = mock(Name.class);
        boolean result = acc.setName(nameSet);

        //Assert
        assertTrue(result);
        assertEquals(nameSet, acc.getName());
    }

    @Test
    void ensureReturnFalseSetNameWithNullValue() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Act
        Name nameComp = mock(Name.class);
        boolean result = acc.setName(null);

        //Assert
        assertFalse(result);
        assertNotEquals(nameComp, acc.getName());
    }

    @Test
    void ensureSetPhoneNumberSetsNewPhoneNumberWithProfileMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Act
        PhoneNumber phoneSet = mock(PhoneNumber.class);
        boolean result = acc.setPhoneNumber(phoneSet);

        //Assert
        assertTrue(result);
        assertEquals(phoneSet , acc.getPhoneNumber());
    }

    @Test
    void ensureReturnFalsePhoneNumberWithNullValue() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Act
        PhoneNumber phoneComp = mock(PhoneNumber.class);
        boolean result = acc.setPhoneNumber(null);

        //Assert
        assertFalse(result);
        assertNotEquals(phoneComp, acc.getPhoneNumber());
    }

    @Test
    void ensureSetStatusSetsNewStatusWithProfileMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Act
        AccountStatus statusSet = mock(AccountStatus.class);
        boolean result = acc.setStatus(statusSet);

        //Assert
        assertTrue(result);
        assertEquals(statusSet, acc.getStatus());
    }


    @Test
    void ensureSetStatusIsDifferentThanGetStatusMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Act
        AccountStatus statusComp = mock(AccountStatus.class);
        boolean result = acc.setStatus(null);

        //Assert
        assertFalse(result);
        assertNotEquals(statusComp ,acc.getStatus());
    }

    @Test
    void compareThatProfileHasBeenSetMock() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Act
        ProfileID profileSet = mock(ProfileID.class);
        boolean result = acc.setProfile(profileSet);

        //Assert
        assertTrue(result);
        assertEquals(profileSet, acc.getProfileID());
    }


    @Test
    void ensureReturnFalseProfileIsNullValue() {
        //Arrange
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //Act
        Profile profileComp = mock(Profile.class);
        boolean result = acc.setProfile(null);

        //Assert
        assertFalse(result);
        assertNotEquals(profileComp, acc.getProfileID());
    }


    @Test
    void verifyEmail_SuccessMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        boolean expected = true;

        //ACT
        boolean result = acc.verifyEmail(email);

        //ASSERT
        assertEquals(expected, result);
    }


    @Test
    void verifyEmail_FailMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Email email2 = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        boolean expected = false;

        //ACT
        boolean result = acc.verifyEmail(email2);

        //ASSERT
        assertEquals(expected, result);
    }


    @Test
    void ensureAccountsAreTheSameMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Account acc2 = new Account(email, name, phoneNumber, accountStatus, prf);


        boolean expected = true;

        //ACT
        boolean result = acc.equals(acc2);

        //ASSERT
        assertEquals(expected, result);
    }


    @Test
    void objectsAreEqualMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Account acc2 = new Account(email, name, phoneNumber, accountStatus, prf);

        //ASSERT
        assertEquals(acc, acc2);
    }


    @Test
    void objectsAreTheSameMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Account acc2 = acc;

        //ASSERT
        assertSame(acc, acc2);
        assertEquals(acc, acc2);
    }

    @Test
    void objectsNotEqualsMock() {
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);

        //ASSERT
        assertNotEquals(acc, prf);
    }


    @Test
    void differentAccountsMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Email email1 = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Account acc2 = new Account(email1, name, phoneNumber, accountStatus, prf);
        boolean expected = false;

        //ACT
        boolean result = acc.equals(acc2);

        //ASSERT
        assertEquals(expected, result);
    }


    @Test
    void testHashCodeSuccessMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Account acc2 = new Account(email, name, phoneNumber, accountStatus, prf);
        //ASSERT
        assertEquals(acc.hashCode(), acc2.hashCode());
    }


    @Test
    void testHashCodeFailMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Email email2 = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Account acc2 = new Account(email2, name, phoneNumber, accountStatus, prf);
        //ASSERT
        assertNotEquals(acc.hashCode(), acc2.hashCode());
    }


    @Test
    void copyAccount_equals_SuccessMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Account acc2 = acc.copy();

        //ASSERT
        assertEquals(acc, acc2);
    }


    @Test
    void copyAccount_equals_FalseMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Email email2 = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Account acc2 = new Account(email2, name, phoneNumber, accountStatus, prf);
        //ASSERT
        assertNotEquals(acc, acc2);
    }


    @Test
    void testEquals_nullMock() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Account acc2 = null;

        //ASSERT
        assertFalse(acc.equals(acc2));
    }

    @Test
    void identity_UnitTest() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        Email id = acc.identity();

        //ASSERT
        assertEquals(email, id);
    }

    @Test
    void sameIDAs_UnitTest() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email, name, phoneNumber, accountStatus, prf);
        boolean expected = true;
        boolean result = acc.sameIDAs(email);

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void sameIDAs_False_UnitTest() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Email email2 = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email2, name, phoneNumber, accountStatus, prf);
        boolean expected = false;
        boolean result = acc.sameIDAs(email);

        //ASSERT
        assertEquals(expected, result);
    }
    @Test
    void sameIDAs_FalseNull_UnitTest() {
        //ARRANGE
        ProfileID prf = mock(ProfileID.class);
        Email email = null;
        Email email2 = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account acc = new Account(email2, name, phoneNumber, accountStatus, prf);
        boolean expected = false;
        boolean result = acc.sameIDAs(email);

        //ASSERT
        assertEquals(expected, result);
    }
}