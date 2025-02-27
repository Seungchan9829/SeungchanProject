package ksc.ts.repository;

import ksc.ts.model.Account;
import ksc.ts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// repo란?
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByUser(User user);

}
