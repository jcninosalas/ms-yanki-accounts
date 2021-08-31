package com.yanki.msyankiaccounts.model;

import lombok.Getter;

@Getter
public class AddDebitAccountEvent implements Event {

    private static final String EVENT = "DebitAccount";

    private String debitAccountNumber;
    private String debitAccountBalance;
    private String yankiAccountId;

    @Override
    public String getEvent() {
        return EVENT;
    }
}
