package dev.soominyeo.web.account;

import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

public interface AccountRepository extends Repository<Account, Long> {
    Account findById(long id);

    Account findByEmail(String email);

    List<Account> findByUsername(String username);

    List<Account> findByNickname(String nickname);

    List<Account> findAll();
}
