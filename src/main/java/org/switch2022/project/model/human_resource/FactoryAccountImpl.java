package org.switch2022.project.model.human_resource;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.value_objects.*;

@Component
public class FactoryAccountImpl implements FactoryAccount{

    public Account createAccount(Email email, Name name, PhoneNumber phoneNumber, AccountStatus status,
                                 ProfileID profile) {
        return new Account(email, name, phoneNumber, status, profile);
    }

}
