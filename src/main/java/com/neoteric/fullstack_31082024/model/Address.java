package com.neoteric.fullstack_31082024.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Address {
    private  String add1;
    private  String add2;
    private  String city;
    private  String pincode;
    private  String state;


}
