package com.yanki.msyankiaccounts.processor;

import com.yanki.msyankiaccounts.model.AddDebitCardEvent;
import com.yanki.msyankiaccounts.model.YankiAccount;
import com.yanki.msyankiaccounts.model.YankiCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class AddDebitCardProcessor {

    private final Sinks.Many<AddDebitCardEvent> sink;

    @Autowired
    public AddDebitCardProcessor(Sinks.Many<AddDebitCardEvent> sink) {
        this.sink = sink;
    }

    public void process(String cardNumber, YankiAccount account) {
        var addDebitCardEvent = AddDebitCardEvent.builder()
                .yankiAccountId(account.getId())
                .yankiBalance(account.getBalance())
                .debitCardNumber(cardNumber)
                .build();
        sink.emitNext(addDebitCardEvent, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
