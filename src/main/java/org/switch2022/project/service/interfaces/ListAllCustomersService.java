package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.CustomerDTO;

import java.util.List;

public interface ListAllCustomersService {
    List<CustomerDTO> getCustomers();

}
