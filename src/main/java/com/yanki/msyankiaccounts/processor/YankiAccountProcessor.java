package com.yanki.msyankiaccounts.processor;

import com.yanki.msyankiaccounts.model.AddDebitCardEvent;
import com.yanki.msyankiaccounts.model.YankiAccount;
import com.yanki.msyankiaccounts.model.YankiCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class YankiAccountProcessor {

    private final Sinks.Many<YankiCreatedEvent> sink;

    @Autowired
    public YankiAccountProcessor(Sinks.Many<YankiCreatedEvent> sink) {
        this.sink = sink;
    }

    public void process(String accountId) {
        var yankiCreatedEvent = new YankiCreatedEvent();
        yankiCreatedEvent.setYankiId(accountId);
        sink.emitNext(yankiCreatedEvent, Sinks.EmitFailureHandler.FAIL_FAST);
    }

}
