package com.example.springsecuritycustom;

import com.example.springsecuritycustom.account.Account;
import com.example.springsecuritycustom.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String username = "daeho";
        String password = "pass";

        Account account = accountService.createAccount(username, password);

    }
}
