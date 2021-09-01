package com.yanki.msyankiaccounts.event;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class YankiPaymentEvent implements Event {

    private static final String EVENT = "PaymentRequiredEvent";
    private BigDecimal ammount;
    private String phoneNumber;

    @Override
    public String getEvent() {
        return null;
    }
}
