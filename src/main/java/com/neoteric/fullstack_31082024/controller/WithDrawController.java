package com.neoteric.fullstack_31082024.controller;


import com.neoteric.fullstack_31082024.model.WithDraw;
import com.neoteric.fullstack_31082024.service.WithDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class WithDrawController {

   @Autowired
   private WithDrawService Withdreservice;
    @PostMapping(value = "/withdraw",
            produces = "application/json",
            consumes = "application/json")
    public String withdrawAmount(@RequestBody WithDraw withdrawRequest) {
        try {

            return Withdreservice.withdrawAmount(withdrawRequest.getAccountnumber(), withdrawRequest.getAmount());
        } catch (Exception e) {
            return "Error during withdrawal: " + e.getMessage();
        }
   }

}
