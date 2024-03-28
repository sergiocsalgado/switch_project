package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.value_objects.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link AccountMapper}.
 */
class AccountMapperTest {

    /**
     * Test private constructor for {@link AccountMapper}.
     */
    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = AccountMapper.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    /**
     * Test class for {@link AccountMapper#listAccountsDTO(List)}.
     */
    @Test
    void listAccountsDTO_Regular_Success() {
        String emailInput1 = "email1@email.com";
        String nameInput1 = "name1";
        String phone1 = "912345678";
        String statusInput1 = "active";

        String emailInput2 = "email2@email.com";
        String nameInput2 = "name2";
        String phone2 = "912345679";
        String statusInput2 = "active";

        Email email1= mock(Email.class);
        when(email1.getEmail()).thenReturn(emailInput1);
        Email email2= mock(Email.class);
        when(email2.getEmail()).thenReturn(emailInput2);

        Name name1 = mock(Name.class);
        when(name1.getValue()).thenReturn(nameInput1);
        Name name2 = mock(Name.class);
        when(name2.getValue()).thenReturn(nameInput2);

        PhoneNumber phoneNumber1 = mock(PhoneNumber.class);
        when(phoneNumber1.getPhoneNumber()).thenReturn(phone1);
        PhoneNumber phoneNumber2 = mock(PhoneNumber.class);
        when(phoneNumber2.getPhoneNumber()).thenReturn(phone2);

        AccountStatus accountStatus1 = mock(AccountStatus.class);
        when(accountStatus1.getStatus()).thenReturn(statusInput1);
        AccountStatus accountStatus2 = mock(AccountStatus.class);
        when(accountStatus2.getStatus()).thenReturn(statusInput2);

        Account act_1 = mock(Account.class);
        when(act_1.getEmail()).thenReturn(email1);
        when(act_1.getName()).thenReturn(name1);
        when(act_1.getPhoneNumber()).thenReturn(phoneNumber1);
        when(act_1.getStatus()).thenReturn(accountStatus1);

        Account act_2 = mock(Account.class);
        when(act_2.getEmail()).thenReturn(email2);
        when(act_2.getName()).thenReturn(name2);
        when(act_2.getPhoneNumber()).thenReturn(phoneNumber2);
        when(act_2.getStatus()).thenReturn(accountStatus2);

        List<Account> accounts = new ArrayList<>();
        accounts.add(act_1);
        accounts.add(act_2);

        AccountDTO actDTO_1 = new AccountDTO();
        actDTO_1.setEmail(emailInput1);
        actDTO_1.setName(nameInput1);
        actDTO_1.setPhoneNumber(phone1);
        actDTO_1.setStatus(statusInput1);

        AccountDTO actDTO_2 = new AccountDTO();
        actDTO_2.setEmail(emailInput2);
        actDTO_2.setName(nameInput2);
        actDTO_2.setPhoneNumber(phone2);
        actDTO_2.setStatus(statusInput2);

        List<AccountDTO> expected = new ArrayList<>();
        expected.add(actDTO_1);
        expected.add(actDTO_2);

        //Act
        List<AccountDTO> result = AccountMapper.listAccountsDTO(accounts);

        //Assert
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getEmail(), result.get(i).getEmail());
            assertEquals(expected.get(i).getName(), result.get(i).getName());
            assertEquals(expected.get(i).getPhoneNumber(), result.get(i).getPhoneNumber());
            assertEquals(expected.get(i).getStatus(), result.get(i).getStatus());
        }

        assertEquals(expected, result);
        assertEquals(expected.size(), result.size());
    }
    @Test
    void listAccountsDTOWithDifferentValues_Success() {
        String emailInput1 = "email1@email.com";
        String nameInput1 = "name1";
        String phone1 = "912345678";
        String statusInput1 = "active";

        String emailInput2 = "email2@email.com";
        String nameInput2 = "name2";
        String phone2 = "912345679";
        String statusInput2 = "active";

        Email email1= mock(Email.class);
        when(email1.getEmail()).thenReturn(emailInput1);
        Email email2= mock(Email.class);
        when(email2.getEmail()).thenReturn(emailInput2);

        Name name1 = mock(Name.class);
        when(name1.getValue()).thenReturn(nameInput1);
        Name name2 = mock(Name.class);
        when(name2.getValue()).thenReturn(nameInput2);

        PhoneNumber phoneNumber1 = mock(PhoneNumber.class);
        when(phoneNumber1.getPhoneNumber()).thenReturn(phone1);
        PhoneNumber phoneNumber2 = mock(PhoneNumber.class);
        when(phoneNumber2.getPhoneNumber()).thenReturn(phone2);

        AccountStatus accountStatus1 = mock(AccountStatus.class);
        when(accountStatus1.getStatus()).thenReturn(statusInput1);
        AccountStatus accountStatus2 = mock(AccountStatus.class);
        when(accountStatus2.getStatus()).thenReturn(statusInput2);

        Account act_1 = mock(Account.class);
        when(act_1.getEmail()).thenReturn(email1);
        when(act_1.getName()).thenReturn(name1);
        when(act_1.getPhoneNumber()).thenReturn(phoneNumber1);
        when(act_1.getStatus()).thenReturn(accountStatus1);

        Account act_2 = mock(Account.class);
        when(act_2.getEmail()).thenReturn(email2);
        when(act_2.getName()).thenReturn(name2);
        when(act_2.getPhoneNumber()).thenReturn(phoneNumber2);
        when(act_2.getStatus()).thenReturn(accountStatus2);

        List<Account> accounts = new ArrayList<>();
        accounts.add(act_1);
        accounts.add(act_2);

        AccountDTO actDTO_1 = new AccountDTO();
        actDTO_1.setEmail(emailInput1);
        actDTO_1.setName(nameInput1);
        actDTO_1.setPhoneNumber(phone1);
        actDTO_1.setStatus(statusInput1);

        AccountDTO actDTO_2 = new AccountDTO();
        actDTO_2.setEmail(emailInput1);
        actDTO_2.setName(nameInput1);
        actDTO_2.setPhoneNumber(phone1);
        actDTO_2.setStatus(statusInput1);

        List<AccountDTO> expected = new ArrayList<>();
        expected.add(actDTO_1);
        expected.add(actDTO_2);

        //Act
        List<AccountDTO> result = AccountMapper.listAccountsDTO(accounts);

        //Assert
        assertNotEquals(expected, result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test class for {@link AccountMapper#toDTO(Account)}.
     */
    @Test
    void toDTO_shouldReturnValidAccountDTOOfAccountDTOPassed() {
        String emailInput1 = "email1@email.com";
        String nameInput1 = "name1";
        String phoneInput1 = "912345678";
        String statusInput1 = "active";

        Email email1= mock(Email.class);
        Name name1 = mock(Name.class);
        PhoneNumber phoneNumber1 = mock(PhoneNumber.class);
        AccountStatus accountStatus1 = mock(AccountStatus.class);

        Account mock_Account = mock(Account.class);
        when(mock_Account.getEmail()).thenReturn(email1);
        when(email1.getEmail()).thenReturn(emailInput1);
        when(mock_Account.getName()).thenReturn(name1);
        when(name1.getValue()).thenReturn(nameInput1);
        when(mock_Account.getPhoneNumber()).thenReturn(phoneNumber1);
        when(phoneNumber1.getPhoneNumber()).thenReturn(phoneInput1);
        when(mock_Account.getStatus()).thenReturn(accountStatus1);
        when(accountStatus1.getStatus()).thenReturn(statusInput1);

        AccountDTO comparable = mock(AccountDTO.class);
        when(comparable.getEmail()).thenReturn(emailInput1);
        when(comparable.getName()).thenReturn(nameInput1);
        when(comparable.getPhoneNumber()).thenReturn(phoneInput1);
        when(comparable.getStatus()).thenReturn(statusInput1);

        AccountDTO resultDTO = AccountMapper.toDTO(mock_Account);

        assertEquals(comparable.getEmail(), resultDTO.getEmail());
        assertEquals(comparable.getName(), resultDTO.getName());
        assertEquals(comparable.getPhoneNumber(), resultDTO.getPhoneNumber());
        assertEquals(comparable.getStatus(), resultDTO.getStatus());
    }
}