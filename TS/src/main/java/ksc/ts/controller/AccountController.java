package ksc.ts.controller;


import ksc.ts.model.Account;
import ksc.ts.service.AccountService;
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

    @PostMapping("/deposit")// @RequestBody Or RequestParams ?
    public Account deposit(@RequestParam String accountNumber, @RequestParam BigDecimal amount) {
        return accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam String accountNumber, @RequestParam BigDecimal amount) {
        return accountService.withdraw(accountNumber, amount);
    }
}
