package com.yanki.msyankiaccounts.model;

import lombok.Getter;

@Getter
public class TransactionYankiEvent implements Event{

    private static final String EVENT = "TransactionYanki";
    private double balance; //saldo
    private String phoneNumber;

    @Override
    public String getEvent() {
        return EVENT;
    }
}
