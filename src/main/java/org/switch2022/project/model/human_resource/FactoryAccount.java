package org.switch2022.project.model.human_resource;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.*;

@Component
public interface FactoryAccount {
    Account createAccount(Email email, Name name, PhoneNumber phoneNumber,
                          AccountStatus status, ProfileID profile );
}
