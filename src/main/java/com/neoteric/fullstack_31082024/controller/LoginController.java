package com.neoteric.fullstack_31082024.controller;

import com.neoteric.fullstack_31082024.model.Login;
import com.neoteric.fullstack_31082024.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LoginController {
@PostMapping(value = "/loginvalidate",
            produces = "application/json",
           consumes = "application/json")
   public  String loginvalidate(@RequestBody Login login){
    LoginService loginService = new LoginService();
  return  loginService.login(login);
}
}
