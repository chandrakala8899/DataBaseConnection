package com.neoteric.fullstack_31082024.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class Atm {
 private String cardNumber;
 private String pin;
 private  String accountnumber;
 private String cvv;
 private Date expiry;

}
