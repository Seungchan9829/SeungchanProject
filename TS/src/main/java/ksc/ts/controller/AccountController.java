package ksc.ts.controller;


import ksc.ts.dto.CreateAccountRequest;
import ksc.ts.dto.CreateAccountResponse;
import ksc.ts.model.Account;
import ksc.ts.service.AccountService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
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




}

// 컨트롤러에서 requestDTO service로 넘겨줌
// service에서 requestDTO 이용해서 Entity 생성 -> 리포지토리 작업 -> entity받아서 responseDTO 컨트롤러에 반환