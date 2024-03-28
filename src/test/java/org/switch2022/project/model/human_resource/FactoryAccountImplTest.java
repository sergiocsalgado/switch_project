package org.switch2022.project.model.human_resource;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.value_objects.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class FactoryAccountImplTest {
    @Test
    void createAccount_Success_UnitTest() {
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account account = new Account(email, name, phoneNumber, accountStatus, prf);
        FactoryAccountImpl factoryAccount = new FactoryAccountImpl();

        Account expected =account ;
        Account result = factoryAccount.createAccount(email, name, phoneNumber, accountStatus, prf);
        assertEquals(expected,result);
    }

    @Test
    void createAccount_Fail_UnitTest() {
        ProfileID prf = mock(ProfileID.class);
        Email email = mock(Email.class);
        Email email2 = mock(Email.class);
        Name name = mock(Name.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account account = new Account(email2, name, phoneNumber, accountStatus, prf);
        FactoryAccountImpl factoryAccount = new FactoryAccountImpl();

        Account expected =account ;
        Account result = factoryAccount.createAccount(email, name, phoneNumber, accountStatus, prf);
        assertNotEquals(expected,result);
    }
}