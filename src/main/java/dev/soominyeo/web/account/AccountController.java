package dev.soominyeo.web.account;

import dev.soominyeo.web.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    protected Logger logger = Logger.getLogger(AccountController.class.getName());
    protected AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

        logger.info("AccountController init");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Account> findAccount(
                                     @RequestParam(value = "email", required = false) String email,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "nickname", required = false) String nickname) {
        logger.info("account-service findAccount() invoked: ");
        List<Account> accounts;
        String by = null;

        if (email != null) {
            accounts = List.of(accountRepository.findByEmail(email));
            by = "email=" + email;
        } else if (username != null) {
            accounts = accountRepository.findByUsername(username);
            by = "username=" + username;
        } else if (nickname != null) {
            accounts = accountRepository.findByNickname(nickname);
            by = "nickname=" + nickname;
        } else {
            accounts = accountRepository.findAll();
            by = "none";
        }

        if (accounts.size() == 0)
            throw new AccountNotFoundException(by);
        logger.info("account-service findAccount() result: " + by);
        return accounts;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account byId(@PathVariable("id") long id) {
        logger.info("account-service byId() invoked: ");
        Account account = accountRepository.findById(id);
        if (account == null)
            throw new AccountNotFoundException(Long.toString(id));
        logger.info("account-service byId() result: " + account);
        return account;
    }

    @RequestMapping(value = "/{id}/email", method = RequestMethod.GET)
    public String getEmail(@PathVariable("id") long id) {
        logger.info("account-service getEmail() invoked: " + id);
        Account account = accountRepository.findById(id);
        if (account == null)
            throw new AccountNotFoundException(Long.toString(id));
        logger.info("account-service getEmail() result: " + account.email);
        return account.getEmail();
    }
}
