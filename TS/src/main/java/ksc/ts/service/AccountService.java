package ksc.ts.service;

import jakarta.transaction.Transactional;
import ksc.ts.dto.account.CreateAccountRequest;
import ksc.ts.dto.account.CreateAccountResponse;
import ksc.ts.dto.account.GetAccountResponse;
import ksc.ts.exception.ResourceNotFoundException;
import ksc.ts.exception.ResourceConflictException;
import ksc.ts.mapper.AccountMapper;
import ksc.ts.model.Account;
import ksc.ts.model.User;
import ksc.ts.repository.AccountRepository;
import ksc.ts.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다."));

        try {
            Account account = accountMapper.toEntity(request);
            account.setUser(user);
            Account createdAccount = accountRepository.save(account);
            
            return accountMapper.toCreateAccountResponse(createdAccount);
            
        } catch (DataIntegrityViolationException e) {
            throw new ResourceConflictException("중복된 계좌번호입니다.");
        } catch (Exception e) {
            throw new RuntimeException();
        }
        
    }

    @Transactional
    public GetAccountResponse getAccount(Long accountId) {
        // 사용자 검증
        //User user = userRepository.findById(accountId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // 추후 사용자 로직 포함해서 수정필요
        Account findAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("계좌를 찾을 수 없습니다."));

        return accountMapper.toGetAccountResponse(findAccount);




    }





}
