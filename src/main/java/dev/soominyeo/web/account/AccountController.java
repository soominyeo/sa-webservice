package dev.soominyeo.web.account;

import dev.soominyeo.web.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Account findAccount(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "email", required = false) String email,
                            @RequestParam(value="username", required = false) String username) {
        logger.info("account-service findAccount() invoked: ");
        Account account = null;
        if (id != null) {
            account = accountRepository.findById(id);
        } else if (email != null) {
            account = accountRepository.findByEmail(email);
        } else if (username != null) {
            account = accountRepository.findByUsername(username);
        }

        if (account == null)
            throw new AccountNotFoundException(email);
        logger.info("account-service findAccount() result: " + account);
        return account;
    }
}
