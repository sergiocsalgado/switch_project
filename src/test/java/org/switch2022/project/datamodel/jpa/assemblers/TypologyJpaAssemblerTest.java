package org.switch2022.project.datamodel.jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.jpa.TypologyJPA;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TypologyJpaAssemblerTest {

    @Test
    void ensureReturnsAValidTypologyGivenATypologyJPA() {
        //Arrange
        String typologyID = "TP1";
        String description = "desc1";

        TypologyJPA typologyJPA = new TypologyJPA(typologyID, description);

        TypologyID typologyID1 = new TypologyID(typologyID);
        Description description1 = new Description(description);

        Typology typology = new Typology(typologyID1, description1);

        //Act
        Typology result = TypologyJpaAssembler.toDomain(typologyJPA);

        //Assert
        assertEquals(typology.getTypologyID(), result.getTypologyID());
        assertEquals(typology.getDescription(), result.getDescription());
    }

    @Test
    void ensureReturnsAValidTypologyJPAGivenATypology() {
        //Arrange
        String typologyID = "TP1";
        String description = "desc1";

        TypologyJPA typologyJPA = new TypologyJPA(typologyID, description);

        TypologyID typologyID1 = new TypologyID(typologyID);
        Description description1 = new Description(description);

        Typology typology = new Typology(typologyID1, description1);

        //Act
        TypologyJPA result = TypologyJpaAssembler.toDataModel(typology);

        //Assert
        assertEquals(typologyJPA.getTypologyID(), result.getTypologyID());
        assertEquals(typologyJPA.getTypologyDescription(), result.getTypologyDescription());
    }

    /**
     * Test private constructor for {@link TypologyJpaAssembler}.
     */
    @Test
    void assertThrowsExceptionWhenCalledThePrivateConstructor()
            throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = TypologyJpaAssembler.class.getDeclaredConstructors();

        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }

        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }
}
