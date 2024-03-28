package org.switch2022.project.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.Description;
import org.switch2022.project.model.value_objects.TypologyID;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TypologyMapperTest {

    /**
     * Test class for {@link TypologyMapper private constructor}.
     */

    @Test
    void assertIsThrowsExceptionWhenCallThePrivateConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        final Constructor<?>[] constructors = TypologyMapper.class.getDeclaredConstructors();
        // check that all constructors are 'private':
        for (final Constructor<?> constructor : constructors) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
        // call the private constructor:
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[]) null);
    }

    @Test
    void testListTypologyDTO_EmptyList() {
        List<Typology> typologies = Collections.emptyList();
        List<TypologyDTO> typologiesDTO = TypologyMapper.listTypologyDTO(typologies);
        assertEquals(0, typologiesDTO.size());
    }

    @Test
    void testListTypologyDTO_SingleTypology() {
        TypologyID typologyID = new TypologyID("1");
        Description description = new Description("Test Description");

        Typology typology = new Typology(typologyID, description);
        List<Typology> typologies = Collections.singletonList(typology);

        List<TypologyDTO> typologiesDTO = TypologyMapper.listTypologyDTO(typologies);
        assertEquals(1, typologiesDTO.size());

        TypologyDTO typologyDTO = typologiesDTO.get(0);
        assertEquals(typologyID.getIdOfTypology(), typologyDTO.getTypologyID());
        assertEquals(description.getDescription(), typologyDTO.getTypologyDescription());
    }

    @Test
    void testListTypologyDTO_MultipleTypologies() {
        TypologyID typologyID1 = new TypologyID("1");
        Description description1 = new Description("Description 1");

        TypologyID typologyID2 = new TypologyID("2");
        Description description2 = new Description("Description 2");

        Typology typology1 = new Typology(typologyID1, description1);
        Typology typology2 = new Typology(typologyID2, description2);

        List<Typology> typologies = new ArrayList<>();
        typologies.add(typology1);
        typologies.add(typology2);

        List<TypologyDTO> typologiesDTO = TypologyMapper.listTypologyDTO(typologies);
        assertEquals(2, typologiesDTO.size());

        TypologyDTO typologyDTO1 = typologiesDTO.get(0);
        assertEquals(typologyID1.getIdOfTypology(), typologyDTO1.getTypologyID());
        assertEquals(description1.getDescription(), typologyDTO1.getTypologyDescription());

        TypologyDTO typologyDTO2 = typologiesDTO.get(1);
        assertEquals(typologyID2.getIdOfTypology(), typologyDTO2.getTypologyID());
        assertEquals(description2.getDescription(), typologyDTO2.getTypologyDescription());
    }

    @Test
    void testToDTO_NullTypology() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            TypologyMapper.toDTO(null);
        });
    }

    @Test
    void testToDTO_ValidTypology() {
        TypologyID typologyID =new  TypologyID("1");
        Description description = new Description("Test Description");

        Typology typology = new Typology(typologyID, description);
        TypologyDTO typologyDTO = TypologyMapper.toDTO(typology);

        assertEquals(typologyID.getIdOfTypology(), typologyDTO.getTypologyID());
        assertEquals(description.getDescription(), typologyDTO.getTypologyDescription());
    }
}
