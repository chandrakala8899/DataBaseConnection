package com.neoteric.fullstack_31082024.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class Account {
    private String accountnumber;
    private String pan;
    private  String mobilenumber;
    private String balance;
    private String name;
    private  Address address;



}
