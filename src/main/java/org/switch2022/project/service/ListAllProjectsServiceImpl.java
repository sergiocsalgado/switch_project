
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
import org.switch2022.project.model.value_objects.BusinessSectorID;
import org.switch2022.project.model.value_objects.CustomerID;
import org.switch2022.project.model.value_objects.ProjectCode;
import org.switch2022.project.model.value_objects.TypologyID;
import org.switch2022.project.service.interfaces.ListAllProjectsService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class implements the ListAllProjectsService interface and provides the functionality
 * to retrieve a list of ProjectDTO objects representing all projects.
 */

@Service
public class ListAllProjectsServiceImpl implements ListAllProjectsService {
    private final Repository<ProjectCode, Project> projectRepository;
    private final Repository<CustomerID, Customer> customerRepository;
    private final Repository<BusinessSectorID, BusinessSector> businessSectorRepository;
    private final Repository<TypologyID, Typology> typologyRepository;

    /**
     * Constructs a ListAllProjectsServiceImpl object with the specified repositories.
     *
     * @param projectRepository      the repository for projects
     * @param customerRepository     the repository for customers
     * @param businessSectorRepository the repository for business sectors
     * @param typologyRepository     the repository for typologies
     */
    public ListAllProjectsServiceImpl(
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
     * Retrieves a list of ProjectDTO objects representing all projects.
     *
     * @return a list of ProjectDTO objects
     */
    public List<ProjectDTO> getProjects() {
        List<Project> projects = projectRepository.findAll();
        Map<CustomerID, String> customers = getCustomers();
        Map<TypologyID, String> typologies = getTypologies();
        Map<BusinessSectorID, String> businessSectors = getBusinessSectors();

        return projects.stream()
                .map(project -> {
                    String customerName = customers.get(project.getCustomerID());
                    String businessSectorDescription = businessSectors.get(project.getBusinessSectorID());
                    String typologyDescription = typologies.get(project.getTypologyID());

                    return ProjectDTOMapper.toDTOWithoutIDs(
                            project,
                            customerName,
                            businessSectorDescription,
                            typologyDescription);
                })
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Retrieves a mapping of CustomerID to customer names.
     *
     * @return a mapping of CustomerID to customer names
     */
    private Map<CustomerID, String> getCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .collect(Collectors.toMap(Customer::getCustomerID,
                        customer -> customer.getName().getValue()));
    }

    /**
     * Retrieves a mapping of TypologyID to typology descriptions.
     *
     * @return a mapping of TypologyID to typology descriptions
     */
    private Map<TypologyID, String> getTypologies() {
        List<Typology> typologies = typologyRepository.findAll();

        return typologies.stream()
                .collect(Collectors.toMap(Typology::getTypologyID,
                        typology -> typology.getDescription().getDescription()));
    }

    /**
     * Retrieves a mapping of BusinessSectorID to business sector descriptions.
     *
     * @return a mapping of BusinessSectorID to business sector descriptions
     */
    private Map<BusinessSectorID, String> getBusinessSectors() {
        List<BusinessSector> businessSectors = businessSectorRepository.findAll();

        return businessSectors.stream()
                .collect(Collectors.toMap(BusinessSector::getBusinessSectorID,
                        businessSector -> businessSector.getBusinessSectorDescription().getDescription()));
    }
}
