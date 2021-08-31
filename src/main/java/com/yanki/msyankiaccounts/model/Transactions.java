package com.yanki.msyankiaccounts.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transactions {

    private String numberInvolved; //numero involucrado
    private String type; //tipo transaccion
    private double ammount; //monto
    private Date createdAt;
}