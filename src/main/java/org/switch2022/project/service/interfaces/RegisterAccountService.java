package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.human_resource.Account;


public interface RegisterAccountService {

    Account createAccount(String emailInput,
                          String nameInput,
                          String phoneNumberInput,
                          String statusInput);
}
