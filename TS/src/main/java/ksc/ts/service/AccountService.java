package ksc.ts.service;

import jakarta.transaction.Transactional;
import ksc.ts.model.Account;
import ksc.ts.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // 트랜잭션이란 ?
    @Transactional
    public Account deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber).
                orElseThrow(()-> new RuntimeException("Account not found"));

        // 입금
        BigDecimal newBalance = account.getAccountBalance().add(amount);
        account.setAccountBalance(newBalance);

        return accountRepository.save(account);
    }

    @Transactional
    public Account withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(()-> new RuntimeException("Account not found"));

        BigDecimal newBalance = account.getAccountBalance().subtract(amount);
        account.setAccountBalance(newBalance);

        return accountRepository.save(account);
    }


}
