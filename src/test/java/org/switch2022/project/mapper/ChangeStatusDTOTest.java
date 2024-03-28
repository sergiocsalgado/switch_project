package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.human_resource.Account;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChangeStatusDTOTest {
    /**
     * Unit Test for sameInstance.
     * Verifies that the same instance of ChangeStatusDTO is created successfully.
     */
    @Test
    void sameInstanceSuccess(){
        // Arrange
        ChangeStatusDTO changeStatusDTO= new ChangeStatusDTO();

        String email = "isep@isep.pt";
        String status = "active";

        changeStatusDTO.setStatus(status);
        changeStatusDTO.setEmailAddress(email);

        // Act

        // Assert
        assertInstanceOf(ChangeStatusDTO.class,changeStatusDTO);
    }

    /**
     * Unit Test for objectNotSame.
     * Verifies that different objects are not considered the same.
     */
    @Test
    void objectNotSame(){
        // Arrange
        Account accountDoubble = mock(Account.class);

        ChangeStatusDTO changeStatusDTO= new ChangeStatusDTO();

        String email = "isep@isep.pt";
        String status = "active";

        changeStatusDTO.setStatus(status);
        changeStatusDTO.setEmailAddress(email);

        // Act

        // Assert
        assertNotEquals(accountDoubble,changeStatusDTO);
    }

    /**
     * Unit Test for equalSameObject.
     * Verifies that the same objects are considered equal.
     */
    @Test
    void equalSameObject(){
        // Arrange
        ChangeStatusDTO changeStatusDTO= new ChangeStatusDTO();

        String email = "isep@isep.pt";
        String status = "active";

        changeStatusDTO.setStatus(status);
        changeStatusDTO.setEmailAddress(email);
        ChangeStatusDTO changeStatusDTO1=changeStatusDTO;

        // Act

        // Assert
        assertEquals(changeStatusDTO1,changeStatusDTO);
        assertEquals(changeStatusDTO1.hashCode(),changeStatusDTO.hashCode());
    }

    /**
     * Unit Test for notEqualObjectNull.
     * Verifies that the object is not equal to null.
     */
    @Test
    void notEqualObjectNull(){
        // Arrange
        ChangeStatusDTO changeStatusDTO= new ChangeStatusDTO();

        String email = "isep@isep.pt";
        String status = "active";

        changeStatusDTO.setStatus(status);
        changeStatusDTO.setEmailAddress(email);

        // Act

        // Assert
        assertNotEquals(null,changeStatusDTO);
    }

    /**
     * Unit Test for getStatus.
     * Verifies that the status is retrieved successfully.
     */
    @Test
    void getStatusSuccess() {
        // Arrange
        ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();
        String expectedStatus = "active";
        changeStatusDTO.setStatus(expectedStatus);

        // Act
        String actualStatus = changeStatusDTO.getStatus();

        // Assert
        assertEquals(expectedStatus, actualStatus);
    }

    /**
     * Unit Test for equalEmailAddresses.
     * Verifies that objects with equal email addresses are considered equal.
     */
    @Test
    void equalEmailAddressesSuccess(){
        // Arrange
        ChangeStatusDTO changeStatusDTO1 = new ChangeStatusDTO();
        changeStatusDTO1.setEmailAddress("isep@isep.pt");

        ChangeStatusDTO changeStatusDTO2 = new ChangeStatusDTO();
        changeStatusDTO2.setEmailAddress("isep@isep.pt");

        // Act

        // Assert
        assertEquals(changeStatusDTO1, changeStatusDTO2);
        assertEquals(changeStatusDTO1.hashCode(), changeStatusDTO2.hashCode());
    }

    /**
     * Unit Test for Hashcode.
     * Verifies that same objects have the same hashcode.
     */

    @Test
    public void testHashCode() {
        // Create two ProfileDTO objects with the same description and profileID
        ChangeStatusDTO changeStatusDTO1 = new ChangeStatusDTO();
        changeStatusDTO1.setEmailAddress("isep@isep.pt");

        ChangeStatusDTO changeStatusDTO2 = new ChangeStatusDTO();
        changeStatusDTO2.setEmailAddress("isep@isep.pt");

        // Assert that the hash codes of the two objects are equal
        assertEquals(changeStatusDTO1.hashCode(), changeStatusDTO2.hashCode());
    }

    /**
     * Unit Test for Hashcode.
     * Verifies that different objects have different hashcode.
     */

    @Test
    public void testHashCode_DifferentObject() {
        // Create two ProfileDTO objects with the same description and profileID
        ChangeStatusDTO changeStatusDTO1 = new ChangeStatusDTO();
        changeStatusDTO1.setEmailAddress("isep@ise.pt");

        ChangeStatusDTO changeStatusDTO2 = new ChangeStatusDTO();
        changeStatusDTO2.setEmailAddress("isep@isep.pt");

        // Assert that the hash codes of the two objects are equal
        assertNotEquals(changeStatusDTO1.hashCode(), changeStatusDTO2.hashCode());
    }

    @Test
    public void testEqualsWithSameObject() {
        ChangeStatusDTO dto = new ChangeStatusDTO();
        dto.setEmailAddress("example@example.com");
        dto.setStatus("active");

        assertTrue(dto.equals(dto));
    }

    @Test
    public void testEqualsWithNullObject() {
        ChangeStatusDTO dto = new ChangeStatusDTO();
        dto.setEmailAddress("example@example.com");
        dto.setStatus("active");

        assertNotNull(dto.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        ChangeStatusDTO dto = new ChangeStatusDTO();
        dto.setEmailAddress("example@example.com");
        dto.setStatus("active");

        assertFalse(dto.equals("example@example.com"));
    }

    @Test
    public void testEqualsWithEqualEmailAddresses() {
        ChangeStatusDTO dto1 = new ChangeStatusDTO();
        dto1.setEmailAddress("example@example.com");
        dto1.setStatus("active");

        ChangeStatusDTO dto2 = new ChangeStatusDTO();
        dto2.setEmailAddress("example@example.com");
        dto2.setStatus("inactive");

        assertEquals(dto1,dto2);
    }

    @Test
    public void testEqualsWithDifferentEmailAddresses() {
        ChangeStatusDTO dto1 = new ChangeStatusDTO();
        dto1.setEmailAddress("example1@example.com");
        dto1.setStatus("active");

        ChangeStatusDTO dto2 = new ChangeStatusDTO();
        dto2.setEmailAddress("example2@example.com");
        dto2.setStatus("active");

        assertNotEquals(dto1,dto2);
    }

}