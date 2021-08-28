package com.yanki.msyankiaccounts.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class AddDebitCardEvent implements Event{

    private static final String EVENT = "YankiAccountCreated";
    private BigDecimal yankiBalance;
    private String yankiAccountId;
    private String  debitCardNumber;

    @Override
    public String getEvent() {
        return EVENT;
    }
}
