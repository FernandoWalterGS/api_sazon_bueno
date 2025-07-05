package sazondelbueno.web.Service;

import sazondelbueno.web.Dto.AccountResponse;
import sazondelbueno.web.Model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account saveAccount(Account account);

    List<Account> listAccounts();
    boolean restore(Long id);

    Optional<Account> existAccount(String email);

    Optional<Account> getById(Long id);

    boolean deleteById(Long id);

    AccountResponse updateById(Long id, Account account);
}
