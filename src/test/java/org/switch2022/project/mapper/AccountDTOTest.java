package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.AccountStatus;
import org.switch2022.project.model.value_objects.Email;
import org.switch2022.project.model.value_objects.Name;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Test class for {@link AccountDTOTest}
 */
class AccountDTOTest {

    /**
     * Test class for {@link ProjectDTO#equals(Object)}
     */
    @Test
    void equals_SameInstance_Success() {
        //ARRANGE
        AccountDTO accountDTO = new AccountDTO();
        Email email = new Email("andre@isep.ipp.pt");
        Name name = new Name("name");
        AccountStatus status = new AccountStatus("active");

        accountDTO.setEmail(email.getEmail());
        accountDTO.setName(name.getValue());
        accountDTO.setStatus(status.getStatus());

        //ASSERT
        assertInstanceOf(AccountDTO.class, accountDTO);
    }

    @Test
    void assertObjectDifferentInstance_Fail() {
        //ARRANGE
        Profile profile = mock(Profile.class);

        AccountDTO accountDTO = new AccountDTO();
        Email email = new Email("andre@isep.ipp.pt");
        Name name = new Name("name");
        AccountStatus status = new AccountStatus("active");

        accountDTO.setEmail(email.getEmail());
        accountDTO.setName(name.getValue());
        accountDTO.setStatus(status.getStatus());

        //ASSERT
        assertNotEquals(profile, accountDTO);
        assertNotEquals(accountDTO, profile);
    }

    @Test
    void equals_SameObject_Success() {
        //ARRANGE
        AccountDTO accountDTO1 = new AccountDTO();
        Email email = new Email("andre@isep.ipp.pt");
        Name name = new Name("name");
        AccountStatus status = new AccountStatus("active");

        accountDTO1.setEmail(email.getEmail());
        accountDTO1.setName(name.getValue());
        accountDTO1.setStatus(status.getStatus());

        AccountDTO accountDTO2 = accountDTO1;

        //ASSERT
        assertEquals(accountDTO1, accountDTO2);
    }

    @Test
    void equals_SameObject_Fail() {
        //ARRANGE
        AccountDTO accountDTO1 = new AccountDTO();
        Email email = new Email("andre@isep.ipp.pt");
        Name name1 = new Name("name1");
        AccountStatus status1 = new AccountStatus("active");

        AccountDTO accountDTO2 = new AccountDTO();
        Email email2 = new Email("tiago@isep.ipp.pt");
        Name name2 = new Name("name2");
        AccountStatus status2 = new AccountStatus("active");

        accountDTO1.setEmail(email.getEmail());
        accountDTO1.setName(name1.getValue());
        accountDTO1.setStatus(status1.getStatus());

        accountDTO2.setEmail(email2.getEmail());
        accountDTO2.setName(name2.getValue());
        accountDTO2.setStatus(status2.getStatus());

        //ASSERT
        assertNotEquals(accountDTO1, accountDTO2);
    }

    @Test
    void equals_NullObject_Fail() {
        //ARRANGE
        AccountDTO accountDTO1 = new AccountDTO();

        AccountDTO accountDTO2 = null;

        //ASSERT
        assertNotEquals(accountDTO1, accountDTO2);
    }

    @Test
    void ensureGetEmailReturnCorrectEmail_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("email@mail.com");

        String expected = "email@mail.com";

        //Act
        String result = accountDTO.getEmail();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetEmailReturnWrongEmail_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("email@mail.com");

        String expected = "wrong@mail.com";

        //Act
        String result = accountDTO.getEmail();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureSetEmailReturnSettedEmail_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("email@mail.com");
        accountDTO.setEmail("newEmail@mail.com");

        String expected = "newEmail@mail.com";

        //Act
        String result = accountDTO.getEmail();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSetEmailDoNotReturnSettedEmail_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("email@mail.com");
        accountDTO.setEmail("newEmail@mail.com");

        String expected = "email@mail.com";

        //Act
        String result = accountDTO.getEmail();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureGetNameReturnCorrectName_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("name");

        String expected = "name";

        //Act
        String result = accountDTO.getName();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetNameReturnWrongName_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("name");

        String expected = "wrong";

        //Act
        String result = accountDTO.getName();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureSetNameReturnSettedName_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("name");
        accountDTO.setName("newName");

        String expected = "newName";

        //Act
        String result = accountDTO.getName();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSetNameDoNotReturnSettedName_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("name");
        accountDTO.setName("newName");

        String expected = "name";

        //Act
        String result = accountDTO.getName();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureGetAccountStatusReturnCorrectAccountStatus_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setStatus("active");

        String expected = "active";

        //Act
        String result = accountDTO.getStatus();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureGetAccountStatusReturnWrongAccountStatus_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setStatus("active");

        String expected = "wrong";

        //Act
        String result = accountDTO.getStatus();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureSetAccountStatusReturnDefinedAccountStatus_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setStatus("active");
        accountDTO.setStatus("inactive");

        String expected = "inactive";

        //Act
        String result = accountDTO.getStatus();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSetAccountStatusDoNotReturnDefinedAccountStatus_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setStatus("inactive");
        accountDTO.setStatus("active");

        String expected = "inactive";

        //Act
        String result = accountDTO.getStatus();

        //Assert
        assertNotEquals(expected, result);
    }


    /**
     * Test class for {@link AccountDTO#hashCode()}
     */
    @Test
    void ensureSameObjectHaveEqualHashCode_Success() {
        //Arrange
        AccountDTO accountDTO = new AccountDTO();
        String email = "andre@isep.ipp.pt";

        accountDTO.setEmail(email);

        AccountDTO accountDTO2 = accountDTO;

        //Assert
        assertEquals(accountDTO.hashCode(), accountDTO2.hashCode());
    }

    @Test
    void ensureSameAttributesObjectHaveEqualHashCode_Success() {
        //Arrange
        String email = "andre@isep.ipp.pt";
        String name = "name";
        String phone = "912345678";
        String status = "active";

        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setEmail(email);
        accountDTO1.setName(name);
        accountDTO1.setPhoneNumber(phone);
        accountDTO1.setStatus(status);

        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setEmail(email);
        accountDTO2.setName(name);
        accountDTO2.setPhoneNumber(phone);
        accountDTO2.setStatus(status);

        //Assert
        assertEquals(accountDTO1.hashCode(), accountDTO2.hashCode());
        assertEquals(accountDTO1, accountDTO2);
    }

    @Test
    void ensureDifferentAttributesObjectHaveDifferentHashCode_Success() {
        //Arrange
        String email1 = "andre@isep.ipp.pt";
        String name1 = "name1";
        String status1 = "active";

        String email2 = "tiago@isep.ipp.pt";
        String name2 = "name2";
        String status2 = "inactive";

        AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setEmail(email1);
        accountDTO1.setName(name1);
        accountDTO1.setStatus(status1);

        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setEmail(email2);
        accountDTO2.setName(name2);
        accountDTO2.setStatus(status2);

        //Assert
        assertNotEquals(accountDTO1.hashCode(), accountDTO2.hashCode());
        assertNotEquals(accountDTO1, accountDTO2);
    }

    @Test
    public void testEquals_DifferentEmail_ReturnsFalse() {
        AccountDTO account1 = new AccountDTO();
        account1.setEmail("example@isep.ipp.pt");
        account1.setName("John Doe");
        account1.setPhoneNumber("123456789");
        account1.setStatus("active");

        AccountDTO account2 = new AccountDTO();
        account2.setEmail("example2@isep.ipp.pt"); // Different email
        account2.setName("John Doe");
        account2.setPhoneNumber("123456789");
        account2.setStatus("active");

        assertNotEquals(account1, account2);
    }

    @Test
    public void testEquals_DifferentName_ReturnsFalse() {
        AccountDTO account1 = new AccountDTO();
        account1.setEmail("example@isep.ipp.pt");
        account1.setName("John Doe");
        account1.setPhoneNumber("123456789");
        account1.setStatus("active");

        AccountDTO account2 = new AccountDTO();
        account2.setEmail("example@isep.ipp.pt");
        account2.setName("Jane Smith"); // Different name
        account2.setPhoneNumber("123456789");
        account2.setStatus("active");

        assertNotEquals(account1,account2);
    }

    @Test
    public void testEquals_DifferentPhoneNumber_ReturnsFalse() {
        AccountDTO account1 = new AccountDTO();
        account1.setEmail("example@isep.ipp.pt");
        account1.setName("John Doe");
        account1.setPhoneNumber("123456789");
        account1.setStatus("active");

        AccountDTO account2 = new AccountDTO();
        account2.setEmail("example@isep.ipp.pt");
        account2.setName("John Doe");
        account2.setPhoneNumber("987654321"); // Different phone number
        account2.setStatus("active");

        assertNotEquals(account1,account2);
    }

    @Test
    public void testEquals_DifferentStatus_ReturnsFalse() {
        AccountDTO account1 = new AccountDTO();
        account1.setEmail("example@isep.ipp.pt");
        account1.setName("John Doe");
        account1.setPhoneNumber("123456789");
        account1.setStatus("active");

        AccountDTO account2 = new AccountDTO();
        account2.setEmail("example@isep.ipp.pt");
        account2.setName("John Doe");
        account2.setPhoneNumber("123456789");
        account2.setStatus("inactive"); // Different status

        assertNotEquals(account1,account2);
    }

    @Test
    public void testEquals_NullFieldInOneObject_ReturnsFalse() {
        AccountDTO account1 = new AccountDTO();
        account1.setEmail("example@isep.ipp.pt");
        account1.setName("John Doe");
        account1.setPhoneNumber("123456789");
        account1.setStatus("active");

        AccountDTO account2 = new AccountDTO();
        account2.setEmail("example@isep.ipp.pt");
        account2.setName("John Doe");
        account2.setPhoneNumber(null); // Null phone number
        account2.setStatus("active");

        assertNotEquals(account1,account2);
    }

}
