package org.switch2022.project.datamodel.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "resources_in_project")

public class ResourceInProjectJpa {
    @Id
    private String resourceInProjectID;
    private String email;
    private String role;
    private String startDate;
    private String endDate;
    private double costPerHour;
    private String currency;
    private double allocation;

    @ManyToOne()
    @JoinColumn(name = "projectJPA", nullable = false)
    private ProjectJPA projectJpa;

    public ResourceInProjectJpa(String resourceInProjectID, String email, String role, String startDate,
                                String endDate, double costPerHour, String currency, double allocation,
                                ProjectJPA projectJpa) {
        this.resourceInProjectID = resourceInProjectID;
        this.email = email;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.costPerHour = costPerHour;
        this.currency = currency;
        this.allocation = allocation;
        this.projectJpa = projectJpa;
    }

    public String getResourceInProjectID() {
        return resourceInProjectID;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAllocation() {
        return allocation;
    }
}