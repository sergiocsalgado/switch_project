package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.user_story.FactoryUserStory;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InMemoryUserStoryRepositoryTest.class)
class InMemoryUserStoryRepositoryTest {
    @MockBean
    FactoryUserStory factoryUserStory;

    @MockBean
    UserStory userStory;

    @MockBean
    UserStoryID userStoryID;

    @MockBean
    List<UserStory> userStories;

    @MockBean
    Priority priority;


    @MockBean
    Map<Email, Account> map;

    @InjectMocks
    InMemoryUserStoryRepository inMemoryUserStoryRepository;

    @BeforeEach
    void cleanup() {
        InMemoryUserStoryRepository.resetRepo();
    }

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        inMemoryUserStoryRepository = new InMemoryUserStoryRepository();
    }


    @Test
    void saveUserStoryToRepository_Success_Unit() {
        UserStory userStory = mock(UserStory.class);
        InMemoryUserStoryRepository inMemoryUserStoryRepository = new InMemoryUserStoryRepository();
        UserStory savedUserStory = inMemoryUserStoryRepository.save(userStory);

        assertEquals(userStory, savedUserStory);
    }

    @Test
    void saveUserStoryToRepository_Success_Fail_Unit() {
        UserStory userStory = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);
        InMemoryUserStoryRepository inMemoryUserStoryRepository = new InMemoryUserStoryRepository();
        UserStory savedUserStory = inMemoryUserStoryRepository.save(userStory);

        assertNotEquals(userStory2, savedUserStory);
    }

    @Test
    void saveUserStoryToRepository_Success() {
        ProjectCode projectCode = new ProjectCode("PRJ1");
        UserStoryNumber userStoryNumber = new UserStoryNumber("1");
        UserStoryID userStoryID = new UserStoryID("US01");
        Name name = new Name("Name2");
        Description description = new Description("Description");
        UserStoryStatus userStoryStatus = new UserStoryStatus("planned");
        Priority priority = new Priority(1);
        UserStory userStory = new UserStory(userStoryID, projectCode,
                userStoryNumber, name, description, userStoryStatus, priority);

        InMemoryUserStoryRepository inMemoryUserStoryRepository = new InMemoryUserStoryRepository();
        UserStory savedUserStory = inMemoryUserStoryRepository.save(userStory);

        assertEquals(userStory, savedUserStory);
    }

    @Test
    void saveUserStoryToRepository_Fail() {
        ProjectCode projectCode = new ProjectCode("PRJ1");
        UserStoryID userStoryID = new UserStoryID("US02");
        UserStoryNumber userStoryNumber1 = new UserStoryNumber("1");
        Name name = new Name("Name2");
        Description description = new Description("Description");
        UserStoryStatus userStoryStatus = new UserStoryStatus("planned");
        Priority priority = new Priority(1);

        ProjectCode projectCode2 = new ProjectCode("PRJ2");
        UserStoryID userStoryID2 = new UserStoryID("US03");
        UserStoryNumber userStoryNumber2 = new UserStoryNumber("2");

        Name name2 = new Name("Name3");
        Description description2 = new Description("Description3");
        UserStoryStatus userStoryStatus2 = new UserStoryStatus("planned");
        Priority priority2 = new Priority(1);

        UserStory userStory = new UserStory(userStoryID, projectCode, userStoryNumber1,
                name, description, userStoryStatus, priority);
        UserStory userStory2 = new UserStory(userStoryID2, projectCode2, userStoryNumber2,
                name2, description2, userStoryStatus2, priority2);

        InMemoryUserStoryRepository inMemoryUserStoryRepository = new InMemoryUserStoryRepository();
        UserStory savedUserStory = inMemoryUserStoryRepository.save(userStory);

        assertNotEquals(userStory2, savedUserStory);
    }


    @Test
    void findAll_Success() {
        ProjectCode projectCode = new ProjectCode("PRJ1");
        UserStoryID userStoryID = new UserStoryID("US04");
        UserStoryNumber userStoryNumber1 = new UserStoryNumber("1");
        Name name = new Name("Name04");
        Description description = new Description("Description4");
        UserStoryStatus userStoryStatus = new UserStoryStatus("planned");
        Priority priority = new Priority(1);


        ProjectCode projectCode2 = new ProjectCode("PRJ2");
        UserStoryID userStoryID2 = new UserStoryID("US05");
        UserStoryNumber userStoryNumber2 = new UserStoryNumber("2");
        Name name2 = new Name("Name05");
        Description description2 = new Description("Description5");
        UserStoryStatus userStoryStatus2 = new UserStoryStatus("planned");
        Priority priority2 = new Priority(1);


        UserStory userStory1 = new UserStory(userStoryID, projectCode, userStoryNumber1,
                name, description, userStoryStatus, priority);
        UserStory userStory2 = new UserStory(userStoryID2, projectCode2, userStoryNumber2,
                name2, description2, userStoryStatus2, priority2);

        InMemoryUserStoryRepository inMemoryUserStoryRepository = new InMemoryUserStoryRepository();
        UserStory savedUserStory1 = inMemoryUserStoryRepository.save(userStory1);
        UserStory savedUserStory2 = inMemoryUserStoryRepository.save(userStory2);

        List<UserStory> expected = List.of(savedUserStory2, savedUserStory1);
        List<UserStory> res = inMemoryUserStoryRepository.findAll();

        assertEquals(expected, res);
    }

    @Test
    void findAll_Success_Unit_1() {

        UserStory userStory1 = mock(UserStory.class);

        InMemoryUserStoryRepository inMemoryUserStoryRepository = new InMemoryUserStoryRepository();
        UserStory savedUserStory1 = inMemoryUserStoryRepository.save(userStory1);

        List<UserStory> expected = List.of(savedUserStory1);
        List<UserStory> res = inMemoryUserStoryRepository.findAll();

        assertEquals(expected, res);
    }

    @Test
    void findAll_Fail() {
        ProjectCode projectCode = new ProjectCode("PRJ1");
        UserStoryID userStoryID = new UserStoryID("US06");
        UserStoryNumber userStoryNumber1 = new UserStoryNumber("1");
        Name name = new Name("Name6");
        Description description = new Description("Description6");
        UserStoryStatus userStoryStatus = new UserStoryStatus("planned");
        Priority priority = new Priority(1);

        ProjectCode projectCode2 = new ProjectCode("PRJ2");
        UserStoryID userStoryID2 = new UserStoryID("US07");
        UserStoryNumber userStoryNumber2 = new UserStoryNumber("2");
        Name name2 = new Name("Name7");
        Description description2 = new Description("Description7");
        UserStoryStatus userStoryStatus2 = new UserStoryStatus("planned");
        Priority priority2 = new Priority(1);


        UserStory userStory1 = new UserStory(userStoryID, projectCode, userStoryNumber1,
                name, description, userStoryStatus, priority);
        UserStory userStory2 = new UserStory(userStoryID2, projectCode2, userStoryNumber2,
                name2, description2, userStoryStatus2, priority2);

        InMemoryUserStoryRepository inMemoryUserStoryRepository = new InMemoryUserStoryRepository();
        UserStory savedUserStory1 = inMemoryUserStoryRepository.save(userStory1);
        UserStory savedUserStory2 = inMemoryUserStoryRepository.save(userStory2);

        List<UserStory> expected = List.of(savedUserStory2);
        List<UserStory> res = inMemoryUserStoryRepository.findAll();

        assertNotEquals(expected, res);
    }

    @Test
    void findAll_Fail_Unit_1() {

        UserStory userStory1 = mock(UserStory.class);
        UserStory userStory2 = mock(UserStory.class);

        InMemoryUserStoryRepository inMemoryUserStoryRepository = new InMemoryUserStoryRepository();
        UserStory savedUserStory1 = inMemoryUserStoryRepository.save(userStory1);
        UserStory savedUserStory2 = inMemoryUserStoryRepository.save(userStory2);

        List<UserStory> expected = List.of(savedUserStory1);
        List<UserStory> res = inMemoryUserStoryRepository.findAll();

        assertNotEquals(expected, res);

    }

    @Test
    void ofIdentity() {
        InMemoryUserStoryRepository inMemoryUserStoryRepository = new InMemoryUserStoryRepository();

        ProjectCode projectCode2 = new ProjectCode("PRJ1");
        UserStoryID userStoryID2 = new UserStoryID("US08");
        UserStoryNumber userStoryNumber = new UserStoryNumber("1");
        Name name2 = new Name("Name8");
        Description description2 = new Description("Description8");
        UserStoryStatus userStoryStatus2 = new UserStoryStatus("planned");
        Priority priority2 = new Priority(1);
        UserStory userStory = new UserStory(userStoryID2, projectCode2, userStoryNumber,
                name2, description2, userStoryStatus2, priority2);

        UserStoryID userStoryID3 = new UserStoryID("US09");

        inMemoryUserStoryRepository.save(userStory);

        Optional<UserStory> res = inMemoryUserStoryRepository.ofIdentity(userStoryID2);
        assertTrue(res.isPresent());
        assertEquals(userStory, res.get());

        res = inMemoryUserStoryRepository.ofIdentity(userStoryID3);
        assertFalse(res.isPresent());

    }
}
