
package org.switch2022.project.service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ProjectDTO;
import org.switch2022.project.mapper.ProjectDTOMapper;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.interfaces.ListUserProjectsService;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements the ListUserProjectsService interface and provides the functionality
 * to retrieve a list of ProjectDTO objects representing projects associated with a specific user.
 * It retrieves user projects based on the provided email and date, and maps the project data
 * to ProjectDTO objects using appropriate mappers.
 * Dependencies: Repository, Customer, BusinessSector, Typology, ProjectDTOMapper, Email, DateManagement
 */

@Service
public class ListUserProjectsServiceImpl implements ListUserProjectsService {
    private final Repository<ProjectCode, Project> projectRepository;
    private final Repository<CustomerID, Customer> customerRepository;
    private final Repository<BusinessSectorID, BusinessSector> businessSectorRepository;
    private final Repository<TypologyID, Typology> typologyRepository;

    /**
     * Constructs a ListUserProjectsServiceImpl object with the specified repositories.
     *
     * @param projectRepository        the repository for projects
     * @param customerRepository       the repository for customers
     * @param businessSectorRepository the repository for business sectors
     * @param typologyRepository       the repository for typologies
     */
    public ListUserProjectsServiceImpl(
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository,
            @Qualifier("customerJPARepository") Repository<CustomerID, Customer> customerRepository,
            @Qualifier("businessSectorJPARepository") Repository<BusinessSectorID, BusinessSector>
                    businessSectorRepository,
            @Qualifier("typologyJPARepository") Repository<TypologyID, Typology> typologyRepository) {

        this.projectRepository = projectRepository;
        this.customerRepository = customerRepository;
        this.businessSectorRepository = businessSectorRepository;
        this.typologyRepository = typologyRepository;
    }

    /**
     * Retrieves a list of ProjectDTO objects representing projects associated with the given user.
     *
     * @param email the email of the user
     * @param date  the date to filter the projects
     * @return a list of ProjectDTO objects
     * @throws IllegalArgumentException if the provided email is invalid
     */
    public List<ProjectDTO> listAllUserProjects(String email, String date) {
        Email emailVO;
        LocalDate localDate = DateManagement.toLocalDate(date);
        try {
            emailVO = new Email(email);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }

        Map<CustomerID, String> customers = getCustomers();
        Map<TypologyID, String> typologies = getTypologies();
        Map<BusinessSectorID, String> businessSectors = getBusinessSectors();

        List<Project> userProjects = getUserProjects(emailVO, localDate);

        List<ProjectDTO> projectsDTO = new ArrayList<>();

        for (Project project : userProjects) {
            String customerName = customers.get(project.getCustomerID());
            String businessSectorDescription = businessSectors.get(project.getBusinessSectorID());
            String typologyDescription = typologies.get(project.getTypologyID());

            projectsDTO.add(ProjectDTOMapper.toDTOWithoutIDs(
                    project,
                    customerName,
                    businessSectorDescription,
                    typologyDescription));
        }

        return projectsDTO;
    }

    /**
     * Retrieves a list of projects associated with the given user and date.
     *
     * @param emailVO   the email value object representing the user's email
     * @param localDate the local date to filter the projects
     * @return a list of projects associated with the user
     */
    private List<Project> getUserProjects(Email emailVO, LocalDate localDate) {
        List<Project> projects = projectRepository.findAll();
        List<Project> userProjects = new ArrayList<>();

        for (Project proj : projects) {
            if (proj.resourceIsInProject(emailVO, localDate)) {
                userProjects.add(proj);
            }
        }

        return userProjects;
    }

    /**
     * Retrieves a mapping of CustomerID to customer names.
     *
     * @return a mapping of CustomerID to customer names
     */
    private Map<CustomerID, String> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        Map<CustomerID, String> map = new HashMap<>();

        for (Customer customer : customers) {
            map.put(customer.getCustomerID(),
                    customer.getName().getValue());
        }

        return map;
    }

    /**
     * Retrieves a mapping of TypologyID to typology descriptions.
     *
     * @return a mapping of TypologyID to typology descriptions
     */
    private Map<TypologyID, String> getTypologies() {
        List<Typology> typologies = typologyRepository.findAll();
        Map<TypologyID, String> map = new HashMap<>();

        for (Typology typology : typologies) {
            map.put(typology.getTypologyID(),
                    typology.getDescription().getDescription());
        }

        return map;
    }

    /**
     * Retrieves a mapping of BusinessSectorID to business sector descriptions.
     *
     * @return a mapping of BusinessSectorID to business sector descriptions
     */
    private Map<BusinessSectorID, String> getBusinessSectors() {
        List<BusinessSector> businessSectors = businessSectorRepository.findAll();
        Map<BusinessSectorID, String> map = new HashMap<>();

        for (BusinessSector businessSector : businessSectors) {
            map.put(businessSector.getBusinessSectorID(),
                    businessSector.getBusinessSectorDescription().getDescription());
        }

        return map;
    }
}

