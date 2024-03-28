package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    /**
     * Test class for {@link CustomerDTO#setCustomerID(String)}.
     * Test class for {@link CustomerDTO#getCustomerID()}.
     */
    @Test
    void ensureGetCustomerIDReturnsSetCustomerID() {
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerID("ctmr1");
        customerDTO.setCustomerID("ctmr2");
        String expected = "ctmr2";
        //Act
        String result = customerDTO.getCustomerID();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link CustomerDTO#setName(String)}.
     * Test class for {@link CustomerDTO#getName()}.
     */
    @Test
    void ensureGetNameReturnsSetName() {
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Adidas");
        customerDTO.setName("Nike");
        String expected = "Nike";
        //Act
        String result = customerDTO.getName();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link CustomerDTO#setNif(String)}.
     * Test class for {@link CustomerDTO#getNif()}.
     */
    @Test
    void ensureGetNifReturnsSetNif() {
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setNif("501316124");
        customerDTO.setNif("980174759");
        String expected = "980174759";
        //Act
        String result = customerDTO.getNif();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link CustomerDTO#equals(Object)}.
     */
    @Test
    void ensureObjectNotEqualsNull() {
        CustomerDTO customerDTO1 = null;
        CustomerDTO customerDTO2 = new CustomerDTO();
        assertNotEquals(customerDTO2,customerDTO1);
    }

    /**
     * Test class for {@link CustomerDTO#equals(Object)}.
     */
    @Test
    void ensureDifferentObjects() {
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        //Act
        Object o = new Object();
        //Assert
        assertNotEquals(customerDTO, o);
    }

    /**
     * Test class for {@link CustomerDTO#equals(Object)}.
     */
    @Test
    void ensureObjectsAreEqual() {
        //Arrange
        CustomerDTO customerDTO1 = new CustomerDTO();
        //Act
        CustomerDTO customerDTO2 = customerDTO1;
        //Assert
        assertEquals(customerDTO1, customerDTO2);
    }

    /**
     * Test class for {@link CustomerDTO#hashCode()}.
     * Test class for {@link CustomerDTO#equals(Object)}.
     */
    @Test
    void ensureAttributesOfObjectHaveEqualHashCode() {
        //Arrange
        String customerID = "ctmr1";
        String name = "abc.lda";
        String nif = "267978596";

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setCustomerID(customerID);
        customerDTO1.setName(name);
        customerDTO1.setNif(nif);

        //Act
        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setCustomerID(customerID);
        customerDTO2.setName(name);
        customerDTO2.setNif(nif);

        //Assert
        assertEquals(customerDTO1.hashCode(), customerDTO2.hashCode());
        assertEquals(customerDTO1, customerDTO2);
    }

    /**
     * Test class for {@link CustomerDTO#hashCode()}.
     * Test class for {@link CustomerDTO#equals(Object)}.
     */
    @Test
    void ensureAttributesOfObjectDontHaveEqualHashCode_differentCustomerID() {
        //Arrange
        String customerID1 = "ctmr1";
        String name1 = "abc.lda";
        String nif1 = "267978596";

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setCustomerID(customerID1);
        customerDTO1.setName(name1);
        customerDTO1.setNif(nif1);

        //Act
        String customerID2 = "ctmr2";
        String name2 = "abc.lda";
        String nif2 = "267978596";

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setCustomerID(customerID2);
        customerDTO2.setName(name2);
        customerDTO2.setNif(nif2);

        //Assert
        assertNotEquals(customerDTO1.hashCode(), customerDTO2.hashCode());
        assertNotEquals(customerDTO1, customerDTO2);
    }

   /**
     * Test class for {@link CustomerDTO#hashCode()}.
     * Test class for {@link CustomerDTO#equals(Object)}.
     */
    @Test
    void ensureAttributesOfObjectDontHaveEqualHashCode_differentName() {
        //Arrange
        String customerID1 = "ctmr1";
        String name1 = "abc.lda";
        String nif1 = "267978596";

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setCustomerID(customerID1);
        customerDTO1.setName(name1);
        customerDTO1.setNif(nif1);

        //Act
        String customerID2 = "ctmr1";
        String name2 = "jpd.lda";
        String nif2 = "267978596";

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setCustomerID(customerID2);
        customerDTO2.setName(name2);
        customerDTO2.setNif(nif2);

        //Assert
        assertNotEquals(customerDTO1.hashCode(), customerDTO2.hashCode());
        assertNotEquals(customerDTO1, customerDTO2);
    }

    /**
     * Test class for {@link CustomerDTO#hashCode()}.
     * Test class for {@link CustomerDTO#equals(Object)}.
     */
    @Test
    void ensureAttributesOfObjectDontHaveEqualHashCode_differentNif() {
        //Arrange
        String customerID1 = "ctmr1";
        String name1 = "abc.lda";
        String nif1 = "267978596";

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setCustomerID(customerID1);
        customerDTO1.setName(name1);
        customerDTO1.setNif(nif1);

        //Act
        String customerID2 = "ctmr1";
        String name2 = "abc.lda";
        String nif2 = "980174759";

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setCustomerID(customerID2);
        customerDTO2.setName(name2);
        customerDTO2.setNif(nif2);

        //Assert
        assertNotEquals(customerDTO1.hashCode(), customerDTO2.hashCode());
        assertNotEquals(customerDTO1, customerDTO2);
    }
}
