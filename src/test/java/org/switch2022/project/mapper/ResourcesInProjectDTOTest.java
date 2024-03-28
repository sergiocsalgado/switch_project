package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.value_objects.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ResourcesInProjectDTO}
 */
class ResourcesInProjectDTOTest {

    /**
     * Test class for {@link ResourcesInProjectDTO#getEmail()}
     */
    @Test
    void ensureSetsAndGetsEmailCorrectly() {
        //ARRANGE
        String emailInput = "email@gmail.com";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setEmail(emailInput);

        String expected = emailInput;

        //ACT
        String result = resourcesInProjectDTO.getEmail();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#getRole()}
     */
    @Test
    void ensureSetsAndGetsRoleCorrectly() {
        //ARRANGE
        String roleInput = "product Owner";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setRole(roleInput);

        String expected = roleInput;

        //ACT
        String result = resourcesInProjectDTO.getRole();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#getResourceInProjectID()} .
     */
    @Test
    void ensureSetAndGetsResourceInProjectIDCorrectly() {
        //ARRANGE
        String resourceInProjectIDInput = "resource1";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID(resourceInProjectIDInput);

        String expected = resourceInProjectIDInput;

        //ACT
        String result = resourcesInProjectDTO.getResourceInProjectID();

        //ASSERT
        assertEquals(expected, result);
        assertEquals(expected.hashCode(), result.hashCode());
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#getProjectCode()}.
     */
    @Test
    void ensureSetAndGetsProjectCodeCorrectly() {
        //ARRANGE
        String projectCodeInput = "PRJ-1";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setProjectCode(projectCodeInput);

        String expected = projectCodeInput;

        //ACT
        String result = resourcesInProjectDTO.getProjectCode();

        //ASSERT
        assertEquals(expected, result);
        assertEquals(expected.hashCode(), result.hashCode());
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */

    @Test
    void ensureObjectAreTheSameInstance_Success() {
        //ARRANGE
        String resourceInProjectIDInput = "resource1";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID(resourceInProjectIDInput);

        //ASSERT
        assertInstanceOf(ResourcesInProjectDTO.class, resourcesInProjectDTO);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */

    @Test
    void ensureObjectAreNotFromTheSameInstance_Success() {
        String profileIDInput = "1";
        String descriptionInput = "user";

        ProfileID profileID = new ProfileID(profileIDInput);
        Description description = new Description(descriptionInput);
        Profile profile = new Profile(profileID, description);

        ResourcesInProjectDTO resourcesInProjectDTO1 = new ResourcesInProjectDTO();

        assertNotEquals(profile, resourcesInProjectDTO1);
        assertEquals(false, resourcesInProjectDTO1.equals(profile));
    }


    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */
    @Test
    void ensureObjectsAreEqual_Success() {
        //ARRANGE
        String resourceInProjectIDInput = "resource1";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID(resourceInProjectIDInput);

        //ACT
        ResourcesInProjectDTO resourcesInProjectDTO2 = resourcesInProjectDTO;

        //ASSERT
        assertEquals(resourcesInProjectDTO, resourcesInProjectDTO2);
        assertEquals(resourcesInProjectDTO.hashCode(), resourcesInProjectDTO2.hashCode());
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */

    @Test
    void ensureObjectsAreEqualWithSameAttributes_Success() {
        //ARRANGE
        String resourceInProjectIDInput = "resource1";
        String emailInput = "email@gmail.com";
        String roleInput = "product Owner";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID(resourceInProjectIDInput);
        resourcesInProjectDTO.setEmail(emailInput);
        resourcesInProjectDTO.setRole(roleInput);

        ResourcesInProjectDTO resourcesInProjectDTO2 = new ResourcesInProjectDTO();
        resourcesInProjectDTO2.setResourceInProjectID(resourceInProjectIDInput);
        resourcesInProjectDTO2.setEmail(emailInput);
        resourcesInProjectDTO2.setRole(roleInput);

        //ASSERT
        assertEquals(resourcesInProjectDTO, resourcesInProjectDTO2);
        assertEquals(resourcesInProjectDTO.hashCode(), resourcesInProjectDTO2.hashCode());
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */
    @Test
    void ensureObjectsAreEqual_Fail() {
        //ARRANGE

        String resourceInProjectIDInput = "resource1";
        String emailInput = "email@gmail.com";
        String roleInput = "product Owner";

        String resourceInProjectIDInput2 = "resource2";
        String emailInput2 = "email2@gmail.com";
        String roleInput2 = "Scrum Master";


        ResourcesInProjectDTO resourcesInProjectDTO1 = new ResourcesInProjectDTO();
        resourcesInProjectDTO1.setResourceInProjectID(resourceInProjectIDInput);
        resourcesInProjectDTO1.setEmail(emailInput);
        resourcesInProjectDTO1.setRole(roleInput);

        ResourcesInProjectDTO resourcesInProjectDTO2 = new ResourcesInProjectDTO();
        resourcesInProjectDTO2.setResourceInProjectID(resourceInProjectIDInput2);
        resourcesInProjectDTO2.setEmail(emailInput2);
        resourcesInProjectDTO2.setRole(roleInput2);

        //ASSERT
        assertNotEquals(resourcesInProjectDTO1, resourcesInProjectDTO2);
        assertNotEquals(resourcesInProjectDTO1.hashCode(), resourcesInProjectDTO2.hashCode());
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */

    @Test
    void ensureObjectsAreEqualDifferentEmail_Fail() {
        //ARRANGE        //ASSERT

        String resourceInProjectIDInput = "resource1";
        String emailInput = "email@gmail.com";
        String roleInput = "Scrum Master";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID(resourceInProjectIDInput);
        resourcesInProjectDTO.setEmail(emailInput);
        resourcesInProjectDTO.setRole(roleInput);

        String resourceInProjectIDInput2 = "resource1";
        String emailInput2 = "email2@gmail.com";
        String roleInput2 = "Scrum Master";

        ResourcesInProjectDTO resourcesInProjectDTO2 = new ResourcesInProjectDTO();
        resourcesInProjectDTO2.setResourceInProjectID(resourceInProjectIDInput2);
        resourcesInProjectDTO2.setEmail(emailInput2);
        resourcesInProjectDTO2.setRole(roleInput2);

        //ASSERT
        assertNotEquals(resourcesInProjectDTO, resourcesInProjectDTO2);
        assertNotEquals(resourcesInProjectDTO.hashCode(), resourcesInProjectDTO2.hashCode());
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */

    @Test
    void ensureObjectsAreEqualDifferentResourceInProjectID_Fail() {
        //ARRANGE
        String resourceInProjectIDInput = "resource1";
        String emailInput = "email@gmail.com";
        String roleInput = "Scrum Master";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID(resourceInProjectIDInput);
        resourcesInProjectDTO.setEmail(emailInput);
        resourcesInProjectDTO.setRole(roleInput);

        String resourceInProjectIDInput2 = "resource2";
        String emailInput2 = "email@gmail.com";
        String roleInput2 = "Scrum Master";

        ResourcesInProjectDTO resourcesInProjectDTO2 = new ResourcesInProjectDTO();
        resourcesInProjectDTO2.setResourceInProjectID(resourceInProjectIDInput2);
        resourcesInProjectDTO2.setEmail(emailInput2);
        resourcesInProjectDTO2.setRole(roleInput2);

        //ASSERT
        assertNotEquals(resourcesInProjectDTO, resourcesInProjectDTO2);
        assertNotEquals(resourcesInProjectDTO.hashCode(), resourcesInProjectDTO2.hashCode());
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */

    @Test
    void ensureObjectsAreEqualDifferentRole_Fail() {
        //ARRANGE
        String resourceInProjectIDInput = "resource1";
        String emailInput = "email@gmail.com";
        String roleInput = "Product Owner";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID(resourceInProjectIDInput);
        resourcesInProjectDTO.setEmail(emailInput);
        resourcesInProjectDTO.setRole(roleInput);

        String resourceInProjectIDInput2 = "resource1";
        String emailInput2 = "email@gmail.com";
        String roleInput2 = "Scrum Master";

        ResourcesInProjectDTO resourcesInProjectDTO2 = new ResourcesInProjectDTO();
        resourcesInProjectDTO2.setResourceInProjectID(resourceInProjectIDInput2);
        resourcesInProjectDTO2.setEmail(emailInput2);
        resourcesInProjectDTO2.setRole(roleInput2);

        //ASSERT
        assertNotEquals(resourcesInProjectDTO, resourcesInProjectDTO2);
        assertNotEquals(resourcesInProjectDTO.hashCode(), resourcesInProjectDTO2.hashCode());
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#(Object)}
     */

    @Test
    void ensureObjectsCanNotBeNull_Success() {
        //Arrange
        String resourceInProjectIDInput = "resource1";
        String emailInput = "email@gmail.com";
        String roleInput = "product Owner";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID(resourceInProjectIDInput);
        resourcesInProjectDTO.setEmail(emailInput);
        resourcesInProjectDTO.setRole(roleInput);

        //ASSERT
        assertNotEquals(null, resourcesInProjectDTO);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */

    @Test
    void ensureObjectsCanNotHaveNullAttributes_Success() {
        //ARRANGE
        String resourceInProjectIDInput = "resource1";
        String emailInput = "email@gmail.com";
        String roleInput = "Product Owner";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setResourceInProjectID(resourceInProjectIDInput);
        resourcesInProjectDTO.setEmail(emailInput);
        resourcesInProjectDTO.setRole(roleInput);

        String resourceInProjectIDInput2 = "resource1";
        String emailInput2 = null;
        String roleInput2 = "Scrum Master";

        ResourcesInProjectDTO resourcesInProjectDTO2 = new ResourcesInProjectDTO();
        resourcesInProjectDTO2.setResourceInProjectID(resourceInProjectIDInput2);
        resourcesInProjectDTO2.setEmail(emailInput2);
        resourcesInProjectDTO2.setRole(roleInput2);

        boolean exp = false;

        //ACT
        boolean res = resourcesInProjectDTO.getEmail().equals(resourcesInProjectDTO2.getEmail());

        //ASSERT
        assertEquals(exp, res);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#getAllocation()}
     */

    @Test
    void getAllocation_test() {

        //ARRANGE
        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();

        //ACT
        Double result = resourcesInProjectDTO.getAllocation();

        //ASSERT
        assertEquals(null, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#setAllocation(Double)}
     */
    @Test
    void setAllocation_test() {

        //ARRANGE
        Double allocationInput = 0.5;

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setAllocation(allocationInput);

        //ACT
        Double result = resourcesInProjectDTO.getAllocation();

        //ASSERT
        assertEquals(allocationInput, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#getCostPerHour()}
     */

    @Test
    void getCostPerHour_test() {

        //ARRANGE
        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();

        //ACT
        Double result = resourcesInProjectDTO.getCostPerHour();

        //ASSERT
        assertEquals(null, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#setCostPerHour(Double)}
     */

    @Test
    void setCostPerHour_test() {

        //ARRANGE
        Double costPerHourInput = 0.5;

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setCostPerHour(costPerHourInput);

        //ACT
        Double result = resourcesInProjectDTO.getCostPerHour();

        //ASSERT
        assertEquals(costPerHourInput, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#getCurrency()}
     */

    @Test
    void getCurrent_test() {

        //ARRANGE
        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();

        //ACT
        String result = resourcesInProjectDTO.getCurrency();

        //ASSERT
        assertEquals(null, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#setCurrency(String)}
     */

    @Test
    void setCurrency_test() {

        //ARRANGE
        String currencyInput = "EUR";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setCurrency(currencyInput);

        //ACT
        String result = resourcesInProjectDTO.getCurrency();

        //ASSERT
        assertEquals(currencyInput, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#getStartDate()}
     */

    @Test
    void getStartDate_test() {

        //ARRANGE
        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();

        //ACT
        String result = resourcesInProjectDTO.getStartDate();

        //ASSERT
        assertEquals(null, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#setStartDate(String)}
     */

    @Test
    void setStartDate_test() {

        //ARRANGE
        String startDateInput = "2020-01-01";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setStartDate(startDateInput);

        //ACT
        String result = resourcesInProjectDTO.getStartDate();

        //ASSERT
        assertEquals(startDateInput, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#getEndDate()}
     */

    @Test
    void getEndDate_test() {

        //ARRANGE
        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();

        //ACT
        String result = resourcesInProjectDTO.getEndDate();

        //ASSERT
        assertEquals(null, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#setEndDate(String)}
     */

    @Test
    void setEndDate_test() {

        //ARRANGE
        String endDateInput = "2020-01-01";

        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();
        resourcesInProjectDTO.setEndDate(endDateInput);

        //ACT
        String result = resourcesInProjectDTO.getEndDate();

        //ASSERT
        assertEquals(endDateInput, result);
    }

    /**
     * Test class for {@link ResourcesInProjectDTO#equals(Object)}
     */

    @Test
    void OverrideOneObjectNull_False() {
        //ARRANGE
        ResourcesInProjectDTO resourcesInProjectDTO = new ResourcesInProjectDTO();

        //ACT
        boolean result = resourcesInProjectDTO.equals(null);

        //ASSERT
        assertFalse(result);
    }
}