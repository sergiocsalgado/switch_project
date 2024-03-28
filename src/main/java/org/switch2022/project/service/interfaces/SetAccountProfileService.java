package org.switch2022.project.service.interfaces;

import org.switch2022.project.model.human_resource.Account;


public interface SetAccountProfileService {
    Account changeProfile(String emailInput, String profileIdInput);
}
