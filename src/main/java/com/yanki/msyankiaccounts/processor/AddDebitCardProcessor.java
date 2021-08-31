package com.yanki.msyankiaccounts.processor;

import com.yanki.msyankiaccounts.model.AddDebitCardEvent;
import com.yanki.msyankiaccounts.model.YankiAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class AddDebitCardProcessor {

    private final Sinks.Many<AddDebitCardEvent> cardSink;

    @Autowired
    public AddDebitCardProcessor(Sinks.Many<AddDebitCardEvent> cardSink) {
        this.cardSink = cardSink;
    }

    public void process(String cardNumber, YankiAccount account) {
        var addDebitCardEvent = AddDebitCardEvent.builder()
                .yankiAccountId(account.getId())
                .yankiBalance(account.getBalance())
                .debitCardNumber(cardNumber)
                .build();
        cardSink.emitNext(addDebitCardEvent, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
