package dev.soominyeo.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {
    public static final long versionId = 1L;

    public AccountNotFoundException(String info) {
        super("Account not found: " + info);
    }
}
