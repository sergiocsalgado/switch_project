package org.switch2022.project;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.project.ResourceInProject;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.sprint.SprintBacklog;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.user_story.UserStory;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.utils.DateManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DataLoader implements CommandLineRunner {
    private final Repository<Email, Account> accountRepository;
    private final Repository<ProfileID, Profile> profileRepository;
    private final Repository<BusinessSectorID, BusinessSector> businessSectorRepository;
    private final Repository<CustomerID, Customer> customerRepository;
    private final Repository<TypologyID, Typology> typologyRepository;
    private final Repository<ProjectCode, Project> projectRepository;
    private final Repository<UserStoryID, UserStory> userStoryRepository;
    private final Repository<SprintID, Sprint> sprintRepository;

    public DataLoader(
            @Qualifier("accountJPARepository") Repository<Email, Account> accountRepository,
            @Qualifier("profileJPARepository") Repository<ProfileID, Profile> profileRepository,
            @Qualifier("businessSectorJPARepository")
            Repository<BusinessSectorID, BusinessSector> businessSectorRepository,
            @Qualifier("customerJPARepository") Repository<CustomerID, Customer> customerRepository,
            @Qualifier("typologyJPARepository") Repository<TypologyID, Typology> typologyRepository,
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository,
            @Qualifier("storyJPARepository") Repository<UserStoryID, UserStory> userStoryRepository,
            @Qualifier("sprintJPARepository") Repository<SprintID, Sprint> sprintRepository) {
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
        this.businessSectorRepository = businessSectorRepository;
        this.customerRepository = customerRepository;
        this.typologyRepository = typologyRepository;
        this.projectRepository = projectRepository;
        this.userStoryRepository = userStoryRepository;
        this.sprintRepository = sprintRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("data.csv")).getFile());


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            List<ResourceInProject> resources1 = new ArrayList<>();
            List<ResourceInProject> resources2 = new ArrayList<>();

            while ((line = reader.readLine()) != null) {

                String[] columns = line.split(";");

                String entityType = columns[0];

                switch (entityType) {
                    case "Profile":
                        Profile profile = new Profile(
                                new ProfileID(columns[1]),
                                new Description(columns[2]));
                        profileRepository.save(profile);
                        break;

                    case "Account":
                        Account account = new Account(
                                new Email(columns[1]),
                                new Name(columns[2]),
                                new PhoneNumber(columns[3]),
                                new AccountStatus(columns[4]),
                                new ProfileID(columns[5])
                        );
                        accountRepository.save(account);
                        break;

                    case "BusinessSector":
                        BusinessSector businessSector = new BusinessSector(
                                new BusinessSectorID(columns[1]),
                                new Description(columns[2])
                        );
                        businessSectorRepository.save(businessSector);
                        break;

                    case "Customer":
                        Customer customer = new Customer(
                                new CustomerID(columns[1]),
                                new Name(columns[2]),
                                new Nif(columns[3])
                        );
                        customerRepository.save(customer);
                        break;

                    case "Typology":
                        Typology typology = new Typology(
                                new TypologyID(columns[1]),
                                new Description(columns[2])
                        );
                        typologyRepository.save(typology);
                        break;

                    case "ResourceInProject":
                        ProjectCode projectCodeResource = new ProjectCode(columns[9]);
                        ResourceInProject resourceInProject = new ResourceInProject(
                                new ResourceInProjectID(columns[1]),
                                new Email(columns[2]),
                                new Role(columns[3]),
                                new Period(DateManagement.toLocalDate(columns[4]),
                                        DateManagement.toLocalDate(columns[5])),
                                new Cost(Double.parseDouble(columns[6]), columns[7]),
                                new Allocation(Double.parseDouble(columns[8]))
                        );
                        if (projectCodeResource.getProjectCode().equals("PRJ1")) {
                            resources1.add(resourceInProject);
                        } else if (projectCodeResource.getProjectCode().equals("PRJ2")) {
                            resources2.add(resourceInProject);
                        }
                        break;

                    case "Project":
                        LocalDate startDate = DateManagement.toLocalDate(columns[12]);
                        LocalDate endDate = null;
                        ProjectCode projectCode = new ProjectCode(columns[1]);

                        if (columns.length > 13 && !columns[13].isEmpty()) {
                            endDate = DateManagement.toLocalDate(columns[13]);
                        }
                        Project project = new Project(
                                projectCode,
                                new Name(columns[2]),
                                new Description(columns[3]),
                                new CustomerID(columns[4]),
                                new BusinessSectorID(columns[5]),
                                new TypologyID(columns[6]),
                                new ProjectStatus(columns[7]),
                                new PositiveNumber(Integer.parseInt(columns[8])),
                                new PositiveNumber(Integer.parseInt(columns[9])),
                                new Cost(Double.parseDouble(columns[10]), columns[11]),
                                new Period(startDate, endDate)
                        );
                        if (projectCode.getProjectCode().equals("PRJ1")) {
                            project.setResources(resources1);
                        } else if (projectCode.getProjectCode().equals("PRJ2")) {
                            project.setResources(resources2);
                        }
                        projectRepository.save(project);
                        break;

                    case "UserStory":
                        UserStory userStory = new UserStory(
                                new UserStoryID(columns[1]),
                                new ProjectCode(columns[2]),
                                new UserStoryNumber(columns[3]),
                                new Name(columns[4]),
                                new Description(columns[5]),
                                new UserStoryStatus((columns[6])),
                                new Priority(Integer.parseInt(columns[7]))
                        );
                        userStoryRepository.save(userStory);
                        break;

                    case "Sprint":
                        Sprint sprint = new Sprint(
                                new ProjectCode(columns[5]),
                                new SprintID(columns[1]),
                                new SprintNumber(Integer.parseInt(columns[2])),
                                new Period(DateManagement.toLocalDate(columns[3]),
                                        DateManagement.toLocalDate(columns[4])),
                                new SprintBacklog(new SprintBacklogID("1")),
                                new SprintStatus(columns[6])
                        );
                        sprintRepository.save(sprint);
                        break;

                    default:
                        continue;
                }
            }
        }
    }
}
