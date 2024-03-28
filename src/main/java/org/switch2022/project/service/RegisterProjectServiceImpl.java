package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ProjectDTO;
import org.switch2022.project.model.business_sector.BusinessSector;
import org.switch2022.project.model.customer.Customer;
import org.switch2022.project.model.project.FactoryProject;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.typology.Typology;
import org.switch2022.project.model.value_objects.*;
import org.switch2022.project.service.interfaces.RegisterProjectService;
import org.switch2022.project.utils.DateManagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class RegisterProjectServiceImpl implements RegisterProjectService {

    private static final String STATUS = "planned";
    private final Repository<ProjectCode, Project> projectRepository;
    private final Repository<CustomerID, Customer> customerRepository;
    private final Repository<BusinessSectorID, BusinessSector> businessSectorRepository;
    private final Repository<TypologyID, Typology> typologyRepository;
    private final FactoryProject factoryProject;

    public RegisterProjectServiceImpl(
            @Qualifier("projectJPARepository") Repository<ProjectCode, Project> projectRepository,
            @Qualifier("customerJPARepository") Repository<CustomerID, Customer> customerRepository,
            @Qualifier("businessSectorJPARepository")
            Repository<BusinessSectorID, BusinessSector> businessSectorRepository,
            @Qualifier("typologyJPARepository") Repository<TypologyID, Typology> typologyRepository,
            FactoryProject factoryProject) {
        this.projectRepository = projectRepository;
        this.customerRepository = customerRepository;
        this.businessSectorRepository = businessSectorRepository;
        this.typologyRepository = typologyRepository;
        this.factoryProject = factoryProject;
    }

    public Project registerProject(ProjectDTO projectDTO) {

        ProjectCode projectCode;
        Name projectName;
        Description projectDescription;
        CustomerID customerID;
        BusinessSectorID businessSectorID;
        TypologyID typologyID;
        ProjectStatus projectStatus;
        PositiveNumber sprintDuration;
        PositiveNumber numberOfPlannedSprints;
        Cost budget;
        Period period;


        String projCode = projectDTO.getProjectCode();
        String name = projectDTO.getName();
        String description = projectDTO.getDescription();
        int durationOfSprint = projectDTO.getSprintDuration();
        int numberOfSprints = projectDTO.getNumberOfPlannedSprints();
        String projectStartDate = projectDTO.getStartDate();
        String projectEndDate = projectDTO.getEndDate();
        String projectBudget = projectDTO.getBudget();

        int valueBudget = Integer.parseInt(projectBudget.replaceAll("[^0-9]", ""));
        String currencyBudget = projectBudget.replaceAll("[^A-Za-z]", "");

        LocalDate startDate = DateManagement.toLocalDate(projectStartDate);
        LocalDate endDate;

        final int MAX_YEARS = 100;

        if (projectEndDate.isEmpty()) {
            endDate = startDate.plus(MAX_YEARS, ChronoUnit.YEARS);
        } else {
            endDate = DateManagement.toLocalDate(projectEndDate);
        }

        try {
            projectCode = new ProjectCode(projCode);
            projectName = new Name(name);
            projectDescription = new Description(description);
            sprintDuration = new PositiveNumber(durationOfSprint);
            numberOfPlannedSprints = new PositiveNumber(numberOfSprints);
            budget = new Cost(valueBudget, currencyBudget);
            period = new Period(startDate, endDate);

        } catch (IllegalArgumentException argumentException) {
            throw new IllegalArgumentException(argumentException.getMessage());
        }
        projectStatus = new ProjectStatus(STATUS);

        String customer = projectDTO.getCustomer();
        String businessSector = projectDTO.getBusinessSector();
        String typology = projectDTO.getTypology();

        Optional<CustomerID> optionalCustomerID = getCustomerID(customer);

        if (optionalCustomerID.isPresent()) {
            customerID = optionalCustomerID.get();
        } else {
            throw new IllegalStateException("CustomerID not found for creating a project");
        }

        Optional<BusinessSectorID> optionalBusinessSectorID = getBusinessSectorID(businessSector);

        if (optionalBusinessSectorID.isPresent()) {
            businessSectorID = optionalBusinessSectorID.get();
        } else {
            throw new IllegalStateException("BusinessSectorID not found for creating a project");
        }

        Optional<TypologyID> optionalTypologyID = getTypologyID(typology);

        if (optionalTypologyID.isPresent()) {
            typologyID = optionalTypologyID.get();
        } else {
            throw new IllegalStateException("TypologyID not found for creating a project");
        }

        Project project = factoryProject.createProject(projectCode, projectName, projectDescription,
                customerID, businessSectorID, typologyID, projectStatus, sprintDuration,
                numberOfPlannedSprints, budget, period);

        if (projectCodeAndNameDoNotExist(projectCode,projectName)){
            return projectRepository.save(project);
        } else {
            throw new IllegalArgumentException("Project already exists.");
        }
    }

    private boolean projectCodeAndNameDoNotExist(ProjectCode projectCode, Name projectName){
        return !projectRepository.containsOfIdentity(projectCode) && !existsProjectName(projectName);
    }
    private boolean existsProjectName(Name projectName){

        List<Project> projects = projectRepository.findAll();

        for (Project project: projects) {
            if (project.getProjectName().getValue().equalsIgnoreCase(projectName.getValue())){
                return true;
            }
        }
        return false;
    }

    private Optional<CustomerID> getCustomerID(String customer) {

        List<Customer> customers = customerRepository.findAll();
        for (Customer cstmr : customers) {
            if (cstmr.getName().getValue().equals(customer)) {
                return Optional.of(cstmr.getCustomerID());
            }
        }
        return Optional.empty();
    }

    private Optional<BusinessSectorID> getBusinessSectorID(String businessSector) {

        List<BusinessSector> businessSectors = businessSectorRepository.findAll();
        for (BusinessSector bs : businessSectors) {
            if (bs.getBusinessSectorDescription().getDescription().equals(businessSector)) {
                return Optional.of(bs.getBusinessSectorID());
            }
        }
        return Optional.empty();
    }


    private Optional<TypologyID> getTypologyID(String typology) {

        List<Typology> typologies = typologyRepository.findAll();
        for (Typology typo : typologies) {
            if (typo.getDescription().getDescription().equals(typology)) {
                return Optional.of(typo.getTypologyID());
            }
        }
        return Optional.empty();
    }
}
