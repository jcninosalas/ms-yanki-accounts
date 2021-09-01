package com.yanki.msyankiaccounts.event;

import com.yanki.msyankiaccounts.model.Transactions;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;

@Getter
@ToString
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
