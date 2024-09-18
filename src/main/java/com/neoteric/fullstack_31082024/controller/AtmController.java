package com.neoteric.fullstack_31082024.controller;

import com.neoteric.fullstack_31082024.exception.AccountNotFoundException;
import com.neoteric.fullstack_31082024.model.Account;
import com.neoteric.fullstack_31082024.model.Atm;
import com.neoteric.fullstack_31082024.service.AtmService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AtmController {
    @PostMapping(value = "/createAtm",
            produces = "application/json",
            consumes = "application/json")
    public Atm createAtm(@RequestBody Account account){
        Atm atm=null;
        AtmService atmService=new AtmService();
        try {
            atm= atmService.createAtm(account);
        } catch (Exception e) {
            System.out.println("Exception Occured " +e);
        } catch (AccountNotFoundException e) {
            System.out.println(( "AccountNotFoundException" +e));
        }
        return atm;
    }
}
