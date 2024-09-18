package com.neoteric.fullstack_31082024.controller;

import com.neoteric.fullstack_31082024.model.Deposit;
import com.neoteric.fullstack_31082024.model.WithDraw;
import com.neoteric.fullstack_31082024.service.DepositService;
import com.neoteric.fullstack_31082024.service.WithDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class DepositController {
    @Autowired
    private DepositService depositService;
    @PostMapping(value = "/deposit",
            produces = "application/json",
            consumes = "application/json")
    public String withdrawAmount(@RequestBody Deposit deposit) {
        try {
            return depositService.depositAmount(deposit.getAccountnumber(), deposit.getAmount());
        } catch (Exception e) {
            return "Error during deposit: " + e.getMessage();
        }
    }
}
