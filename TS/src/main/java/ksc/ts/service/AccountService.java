package ksc.ts.service;

import jakarta.transaction.Transactional;
import ksc.ts.dto.CreateAccountRequest;
import ksc.ts.dto.CreateAccountResponse;
import ksc.ts.exception.UserNotFoundException;
import ksc.ts.mapper.AccountMapper;
import ksc.ts.model.Account;
import ksc.ts.model.User;
import ksc.ts.repository.AccountRepository;
import ksc.ts.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public CreateAccountResponse createAccount(CreateAccountRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        accountRepository.findByAccountNumber(request.getAccountNumber()).ifPresent(account -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "중복된 계좌 번호입니다.");
        });

        Account account = accountMapper.toEntity(request);

        account.setUser(user);

        Account createdAccount = accountRepository.save(account);

        System.out.println("accountMapper.toCreateAccountResponse(createdAccount = " + accountMapper.toCreateAccountResponse(createdAccount));
        return accountMapper.toCreateAccountResponse(createdAccount);

    }





}
