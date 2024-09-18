package com.neoteric.fullstack_31082024.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class WithDraw {
    private String accountnumber;

    private double amount;

}
