package ksc.ts.controller;


import ksc.ts.dto.account.CreateAccountRequest;
import ksc.ts.dto.account.CreateAccountResponse;
import ksc.ts.dto.account.GetAccountResponse;
import ksc.ts.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> addAccount(@RequestBody CreateAccountRequest request) {
        CreateAccountResponse response = accountService.createAccount(request);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{accountId}")
    public ResponseEntity<GetAccountResponse> getAccount(@PathVariable long accountId) {
        GetAccountResponse response = accountService.getAccount(accountId);

        return ResponseEntity.ok(response);
    }



}

// 컨트롤러에서 requestDTO service로 넘겨줌
// service에서 requestDTO 이용해서 Entity 생성 -> 리포지토리 작업 -> entity받아서 responseDTO 컨트롤러에 반환