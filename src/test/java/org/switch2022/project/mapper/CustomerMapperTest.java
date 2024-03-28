package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.Name;
import org.switch2022.project.model.value_objects.Nif;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerMapperTest {

    /**
     * Test class for {@link CustomerMapper private constructor}.
     */

    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = CustomerMapper.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    /**
     * Test class for {@link CustomerMapper#listCustomerDTO(List)}.
     */
    @Test
    void ensureListCustomerDTO_success() {
        //Arrange
        String customerID1 = "ctmr1";
        String name1 = "Adidas";
        String nif1 = "501316124";

        String customerID2 = "ctmr2";
        String name2 = "Nike";
        String nif2 = "980174759";

        CustomerID customerID_1 = mock(CustomerID.class);
        CustomerID customerID_2 = mock(CustomerID.class);
        when(customerID_1.getIdOfCustomer()).thenReturn(customerID1);
        when(customerID_2.getIdOfCustomer()).thenReturn(customerID2);

        Name name_1 = mock(Name.class);
        Name name_2 = mock(Name.class);
        when(name_1.getValue()).thenReturn(name1);
        when(name_2.getValue()).thenReturn(name2);

        Nif nif_1 = mock(Nif.class);
        Nif nif_2 = mock(Nif.class);
        when(nif_1.getNif()).thenReturn(nif1);
        when(nif_2.getNif()).thenReturn(nif2);

        Customer customer1 = mock(Customer.class);
        when(customer1.getCustomerID()).thenReturn(customerID_1);
        when(customer1.getName()).thenReturn(name_1);
        when(customer1.getNif()).thenReturn(nif_1);

        Customer customer2 = mock(Customer.class);
        when(customer2.getCustomerID()).thenReturn(customerID_2);
        when(customer2.getName()).thenReturn(name_2);
        when(customer2.getNif()).thenReturn(nif_2);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setCustomerID(customerID1);
        customerDTO1.setName(name1);
        customerDTO1.setNif(nif1);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setCustomerID(customerID2);
        customerDTO2.setName(name2);
        customerDTO2.setNif(nif2);

        List<CustomerDTO> expected = new ArrayList<>();
        expected.add(customerDTO1);
        expected.add(customerDTO2);

        //Act
        List<CustomerDTO> result = CustomerMapper.listCustomerDTO(customers);

        //Assert
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getCustomerID(), result.get(i).getCustomerID());
            assertEquals(expected.get(i).getName(), result.get(i).getName());
            assertEquals(expected.get(i).getNif(), result.get(i).getNif());
        }
        assertEquals(expected, result);
        assertEquals(expected.size(), result.size());
    }

    /**
     * Test class for {@link CustomerMapper#toDTO(Customer)}.
     */
    @Test
    void ensureToDTO_success() {
        //Arrange
        String customerID0 = "ctmr";
        String name0 = "abc S.A.";
        String nif0 = "267978596";

        CustomerID customerID = mock(CustomerID.class);
        Name name = mock(Name.class);
        Nif nif = mock(Nif.class);
        Customer customer = mock(Customer.class);

        when(customer.getCustomerID()).thenReturn(customerID);
        when(customerID.getIdOfCustomer()).thenReturn(customerID0);

        when(customer.getName()).thenReturn(name);
        when(name.getValue()).thenReturn(name0);

        when(customer.getNif()).thenReturn(nif);
        when(nif.getNif()).thenReturn(nif0);

        CustomerDTO expected = mock(CustomerDTO.class);
        when(expected.getCustomerID()).thenReturn(customerID0);
        when(expected.getName()).thenReturn(name0);
        when(expected.getNif()).thenReturn(nif0);

        //Act
        CustomerDTO result = CustomerMapper.toDTO(customer);

        //Assert
        assertEquals(expected.getCustomerID(), result.getCustomerID());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getNif(), result.getNif());
    }
}