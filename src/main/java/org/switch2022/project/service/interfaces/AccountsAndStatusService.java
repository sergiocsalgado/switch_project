package org.switch2022.project.service.interfaces;

import org.switch2022.project.mapper.AccountDTO;

import java.util.List;

public interface AccountsAndStatusService {

    List<AccountDTO> getAccountAndStatus();
}
