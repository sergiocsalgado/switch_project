package org.switch2022.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.AccountDTO;
import org.switch2022.project.mapper.AccountMapper;
import org.switch2022.project.mapper.ChangeStatusDTO;
import org.switch2022.project.model.human_resource.Account;
import org.switch2022.project.service.interfaces.AccountsAndStatusService;
import org.switch2022.project.service.interfaces.ChangeStatusService;
import org.switch2022.project.service.interfaces.RegisterAccountService;
import org.switch2022.project.service.interfaces.SetAccountProfileService;

import java.util.List;

/**
 * Controller class for registering and saving user accounts.
 */
@Controller
@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
    private final RegisterAccountService registerAccountService;
    private final SetAccountProfileService setAccountProfileService;
    private final ChangeStatusService changeStatusService;
    private final AccountsAndStatusService accountsAndStatusService;

    /**
     * Constructs new RegisterAccountController with the specified RegisterAccountService
     * and SetAccountProfileService.
     *
     * @param registerAccountService   the service to be used for account
     *                                 registration and saving.
     * @param setAccountProfileService the service used to change the account profile.
     * @param changeStatusService      the service to change the account status.
     * @param accountsAndStatusService The AccountsAndStatusService object to
     *                                 be used by the controller.
     */
    public AccountController(RegisterAccountService registerAccountService,
                             SetAccountProfileService setAccountProfileService,
                             ChangeStatusService changeStatusService,
                             AccountsAndStatusService accountsAndStatusService) {
        this.registerAccountService = registerAccountService;
        this.setAccountProfileService = setAccountProfileService;
        this.changeStatusService = changeStatusService;
        this.accountsAndStatusService = accountsAndStatusService;
    }

    /**
     * Creates and saves an account based on the provided AccountDTO.
     *
     * @param accountDTO the AccountDTO containing the account details.
     * @return the AccountDTO representing the created and saved account.
     * @throws IllegalArgumentException if the provided accountDTO contains
     * invalid input parameters.
     * @throws IllegalStateException    if the user profile is not found for
     * creating an account.
     */
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createAccount(@RequestBody AccountDTO accountDTO) {

        try {
            Account createdAccount = registerAccountService.createAccount(
                    accountDTO.getEmail(),
                    accountDTO.getName(),
                    accountDTO.getPhoneNumber(),
                    accountDTO.getStatus());

            AccountDTO accountCreated = AccountMapper.toDTO(createdAccount);

            return new ResponseEntity<>(accountCreated, HttpStatus.CREATED);

        } catch (IllegalArgumentException argumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(argumentException.getMessage());
        } catch (IllegalStateException stateException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(stateException.getMessage());
        }
    }

    /**
     * Sets the account profile for the specified email address.
     *
     * @param emailAddress the email address of the account.
     * @param profileID    the ID of the profile to set.
     * @return the AccountDTO representing the changed account profile.
     * @throws IllegalArgumentException if the input parameters are invalid.
     * @throws IllegalStateException    if the user profile does not exist.
     */
    @PatchMapping("/{emailAddress}/{profileID}")
    @ResponseBody
    public ResponseEntity<Object> setAccountProfile(@PathVariable("emailAddress") String emailAddress,
                                                    @PathVariable("profileID") String profileID) {
        try {
            Account changedAccount = setAccountProfileService.changeProfile(emailAddress, profileID);
            AccountDTO accountDTO = AccountMapper.toDTO(changedAccount);

            return new ResponseEntity<>(accountDTO, HttpStatus.OK);

        } catch (IllegalArgumentException argumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(argumentException.getMessage());
        } catch (IllegalStateException stateException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(stateException.getMessage());
        }
    }

    /**
     * Sets the status of the account associated with the given email address to status.
     *
     * @param changeStatusDTO The ChangeStatusDTO containing the email address and status.
     * @return A ResponseEntity indicating the success or failure of the account status change.
     */
    @PatchMapping("/status")
    @ResponseBody
    public ResponseEntity<Object> changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        String email = changeStatusDTO.getEmailAddress();
        String status = changeStatusDTO.getStatus();

        try {
            changeStatusService.changeStatus(email, status);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (IllegalArgumentException argumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(argumentException.getMessage());
        } catch (IllegalStateException stateException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(stateException.getMessage());
        }
    }

    /**
     * Retrieves the accounts and their status.
     *
     * @return the list of AccountDTO objects if successful, and the HttpStatus OK.
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAccountsAndStatus() {
        List<AccountDTO> accountDTOs = accountsAndStatusService.getAccountAndStatus();

        return new ResponseEntity<>(accountDTOs, HttpStatus.OK);
    }
}
