package ksc.ts.service;

import jakarta.transaction.Transactional;
import ksc.ts.dto.account.*;
import ksc.ts.dto.transaction.TransactionRequest;
import ksc.ts.dto.transaction.TransactionResponse;
import ksc.ts.exception.ResourceNotFoundException;
import ksc.ts.exception.ResourceConflictException;
import ksc.ts.exception.UnauthorizedException;
import ksc.ts.mapper.AccountMapper;
import ksc.ts.model.Account;
import ksc.ts.model.MoneyTransaction;
import ksc.ts.model.User;
import ksc.ts.repository.AccountRepository;
import ksc.ts.repository.MoneyTransactionRepository;
import ksc.ts.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final MoneyTransactionRepository moneyTransactionRepository;

    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository, AccountMapper accountMapper, MoneyTransactionRepository moneyTransactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
        this.moneyTransactionRepository = moneyTransactionRepository;
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
    @PreAuthorize("@accountService.getUserEmailByAccount(#accountId) == authentication.principal.username")
    public GetAccountResponse getAccount(Long accountId) {

        Account findAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("계좌를 찾을 수 없습니다."));

        return accountMapper.toGetAccountResponse(findAccount);

    }

    // AccountId -> userEmail 뽑아내는 함수
    public String getUserEmailByAccount(Long accountId) {
        Account findAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("계좌를 찾을 수 없습니다."));

        return findAccount.getUser().getUserEmail();
    }

    @Transactional
    public UpdateAccountResponse updateAccount(User user, Long accountId, UpdateAccountRequest request) {
        // 1. 계좌 조회
        Account findAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("계좌를 찾을 수 없습니다"));

        // 2. 권한체크
        if (!findAccount.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("권한이 없습니다.");
        }

        // 3. 계좌 정보 업데이트. 추후 매핑으로 구현
        findAccount.setAccountNumber(request.getAccountNumber());
        findAccount.setAccountPassword(request.getAccountPassword());

        Account updatedAccount = accountRepository.save(findAccount);

        //  4. 응답 객체 생성
        UpdateAccountResponse response = UpdateAccountResponse.builder()
                .id(updatedAccount.getId())
                .accountNumber(updatedAccount.getAccountNumber())
                .accountPassword(updatedAccount.getAccountPassword())
                .build();

        return response;

    }

    @Transactional
    public void deleteAccount(User user, Long accountId) {
        // 계좌 조회
        Account findAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("계좌를 찾을 수 없습니다"));
        // 권한 조회
        if (!findAccount.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("권한이 없습니다.");
        }
        // 삭제
        accountRepository.delete(findAccount);

    }

    // 계좌 입금 기능
    @Transactional
    public TransactionResponse deposit(User user, Long accountId, TransactionRequest request) {
        BigDecimal depositAmount = request.getAmount();
        // 계좌 조회
        Account findAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("계좌를 찾을 수 없습니다"));

        // 권한 조회
        if (!findAccount.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("권한이 없습니다.");
        }

        // 입금
        findAccount.setAccountBalance(findAccount.getAccountBalance().add(depositAmount));
        Account savedAccount = accountRepository.save(findAccount);

        // 입금 내역 저장
        MoneyTransaction moneyTransaction = MoneyTransaction.builder()
                .account(findAccount)
                .amount(depositAmount)
                .type(request.getType())
                .build();

        MoneyTransaction savedMoneyTransaction = moneyTransactionRepository.save(moneyTransaction);

        return TransactionResponse.builder()
                .id(savedMoneyTransaction.getId())
                .amount(savedMoneyTransaction.getAmount())
                .type(savedMoneyTransaction.getType())
                .transactionDate(savedMoneyTransaction.getTransactionDate())
                .updatedBalance(savedAccount.getAccountBalance())
                .build();


    }

    @Transactional
    public TransactionResponse withdrawal(User user, Long accountId, TransactionRequest request) {
        return deposit(user, accountId, request);
    }
}
