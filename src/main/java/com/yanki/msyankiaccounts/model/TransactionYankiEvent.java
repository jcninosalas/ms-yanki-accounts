package com.yanki.msyankiaccounts.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class TransactionYankiEvent implements Event{

    private static final String EVENT = "TransactionYanki";
    private String phoneNumber;
    private double balance; //saldo
    private String yankiAccountId;
    private String debitCardNumber; //numero tarjeta debito
    private ArrayList<Transactions> transactions;
    private Date createdAt;

    @Override
    public String getEvent() {
        return EVENT;
    }
}
