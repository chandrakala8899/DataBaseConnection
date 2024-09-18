package com.neoteric.fullstack_31082024.controller;

import com.neoteric.fullstack_31082024.hibernate.model.AccountEntity;
import com.neoteric.fullstack_31082024.hibernate.service.HibernateService;
import com.neoteric.fullstack_31082024.hibernate.service.JpaConfigservice;
import com.neoteric.fullstack_31082024.model.Account;
import com.neoteric.fullstack_31082024.service.AccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class AccountController {

    @PostMapping(value = "/createAccount",
            produces = "application/json",
            consumes = "application/json")
    public Account getAccountNumber(@RequestBody Account account) throws Exception {
        AccountService service = new AccountService();
        String accountNumber = service.creatingAccount(account);
        account.setAccountnumber(accountNumber);
        return account;
    }

    @PostMapping(value = "/createAccount/usingUI",
            produces = "application/json",
            consumes = "application/json")
    public Account createaccountoneToManyusingUI(@RequestBody Account account) {
        HibernateService hibernateService = new HibernateService();
        String accountnumber = hibernateService.oneToManyusingHibernateFromUI(account);
        account.setAccountnumber(accountnumber);
        return account;
    }

    @PostMapping(value = "/createAccount/hibernate",
            produces = "application/json",
            consumes = "application/json")
    public Account accountcreationoneToManyusingHibernate(@RequestBody Account account) {
        HibernateService hibernateService = new HibernateService();
        String accountnumber = hibernateService.createAccountUsingHibernate(account);
        account.setAccountnumber(accountnumber);
        return account;
    }


    @GetMapping(value = "createAccount/searchaccount",
            produces = "application/json",
            consumes = "application/json")
    public Account accountcreationoneToManyusingHibernate(@RequestHeader("accountinput")
                                                          String accountnumber) {
        // List<String> accountNumberList= httpHeaders.get("accountinput");
        // String accountnumberFromHeaders = accountNumberList.get(0);
        HibernateService hibernateService = new HibernateService();
        return hibernateService.searchAccount(accountnumber);
    }
    @PostMapping(value = "/createAccount/Jpa",
            produces = "application/json",
            consumes = "application/json")
    public Account createaccountNumberfromJpa(@RequestBody Account account){
        JpaConfigservice configservice = new JpaConfigservice();
      //  String accountnumber =  configservice.creatingAccountFromJpa(account);
       //
        // account.setAccountnumber(accountnumber);

        return account;
    }


}
