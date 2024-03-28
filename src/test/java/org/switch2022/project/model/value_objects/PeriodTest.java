package org.switch2022.project.model.value_objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {

    /**
     * Test class for {@link Period#Period(LocalDate, LocalDate)}.
     */
    @Test
    public void invalidDate_startDateIsNull() {
        //Arrange, Act and Assert
        LocalDate endDate = LocalDate.of(2024, 1, 1);
        Exception thrown = Assertions.assertThrows(
                Exception.class, () -> new Period(null, endDate));
        Assertions.assertEquals("Start date cannot be null", thrown.getMessage());
    }

    @Test
    public void ensureEndDateIsBeforeStartDate() {
        //Arrange, Act and Assert
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 1);
        Exception thrown = Assertions.assertThrows(
                Exception.class, () -> new Period(startDate, endDate));
        Assertions.assertEquals("End date must be after the start date", thrown.getMessage());
    }

    @Test
    public void invalidDate_startDateIsAfterEndDate() {
        //Arrange, Act and Assert
        LocalDate startDate = LocalDate.of(2024, 1, 15);
        LocalDate endDate = LocalDate.of(2024, 1, 1);
        Exception thrown = Assertions.assertThrows(
                Exception.class, () -> new Period(startDate, endDate));
        Assertions.assertEquals("End date must be after the start date", thrown.getMessage());
    }

    @Test
    public void invalidDate_startDateIsEqualEndDate() {
        //Arrange, Act and Assert
        LocalDate startDate = LocalDate.of(2024, 1, 10);
        LocalDate endDate = LocalDate.of(2024, 1, 10);
        Exception thrown = Assertions.assertThrows(
                Exception.class, () -> new Period(startDate, endDate));
        Assertions.assertEquals("End date must be after the start date", thrown.getMessage());
    }

    /**
     * Test class for {@link Period#getStartDate()}.
     */
    @Test
    void getStartDate() {
        //Arrange and Act
        // Create a new Period object with a known start date
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        Period period = new Period(startDate, endDate);

        // Verify that the getStartDate method returns the correct start date
        assertEquals(startDate, period.getStartDate());
    }

    /**
     * Test class for {@link Period#getEndDate()}.
     */
    @Test
    void getEndDate() {
        //Arrange and Act
        // Create a new Period object with a known start date
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        Period period = new Period(startDate, endDate);

        //Assert
        // Verify that the getStartDate method returns the correct start date
        assertEquals(endDate, period.getEndDate());
    }

    /**
     * Test class for {@link Period#equals(Object)}.
     */
    @Test
    public void periodEqualsToItself() {
        //Arrange and Act
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 2, 15);
        Period period1 = new Period(startDate, endDate);
        Period period2 = period1;

        //Assert
        assertEquals(period1, period2);
        assertEquals(period1.hashCode(), period2.hashCode());
    }

    @Test
    public void periodNotEqualsToItself() {
        //Arrange and Act
        LocalDate startDate1 = LocalDate.of(2024, 1, 1);
        LocalDate endDate1 = LocalDate.of(2024, 1, 15);
        LocalDate startDate2 = LocalDate.of(2024, 1, 10);
        LocalDate endDate2 = LocalDate.of(2024, 1, 30);
        Period period1 = new Period(startDate1, endDate1);
        Period period2 = new Period(startDate2, endDate2);

        //Assert
        assertNotEquals(period1, period2);
        assertNotEquals(period1.hashCode(), period2.hashCode());
    }

    @Test
    public void ensureObjectsHaveSameContent() {
        //Arrange and Act
        LocalDate startDate1 = LocalDate.of(2024, 1, 1);
        LocalDate endDate1 = LocalDate.of(2024, 1, 15);

        Period period1 = new Period(startDate1, endDate1);
        Period period2 = new Period(startDate1, endDate1);

        //Assert
        assertEquals(period1, period2);
        assertEquals(period1.hashCode(), period2.hashCode());
    }

    @Test
    void ensureObjectsAreNotEqual() {
        //Arrange
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        Period period = new Period(startDate, endDate);
        String o = "Period: 2024-01-01 to 2024-01-15";

        //Act
        boolean isNotEquals = period.equals(o);

        //Assert
        assertFalse(isNotEquals);
    }

    @Test
    void ensureObjectsAreNotEqual_null() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        Period period = new Period(startDate, endDate);
        Period period1 = null;

        assertFalse(period.sameAs(period1));
        assertNotEquals(period, period1);
    }

    @Test
    void ensureObjectsAreFromDifferentClasses() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        ProfileID profileIDNumber =  new ProfileID("1");
        Description description = new Description("manager");
        Period period = new Period(startDate, endDate);
        Profile profile = new Profile(profileIDNumber, description);

        assertNotEquals(period, profile);
    }

    /**
     * Test class for {@link Period#sameAs(Period)}.
     */
    @Test
    void ensureSameAs_otherObject() {
        //Arrange
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        Period period1 = new Period(startDate, endDate);
        Period period2 = period1;

        //Act
        boolean result = period1.sameAs(period2);

        //Assert
        assertTrue(result);
        assertEquals(period1, period2);
        assertEquals(period1.hashCode(), period2.hashCode());
    }

    @Test
    void ensureSameAs_null() {
        //Arrange
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        Period period1 = new Period(startDate, endDate);

        //Act
        boolean result = period1.sameAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void sameHashCodeUnsuccessful() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate startDate1 = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        Period period1 = new Period(startDate, endDate);
        Period period2 = new Period(startDate1, endDate);

        assertNotEquals(period1, period2);
        assertNotEquals(period1.hashCode(), period2.hashCode());
    }

    @Test
    void ensureSameAs_testGetEndDate() {
        //Arrange
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        Period period = new Period(startDate, endDate);

        //Assert
        assertEquals(endDate, period.getEndDate());
    }
}