package com.yanki.msyankiaccounts.processor;

import com.yanki.msyankiaccounts.model.YankiAccount;
import com.yanki.msyankiaccounts.event.YankiCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
@Slf4j
public class YankiAccountProcessor {

    private final Sinks.Many<YankiCreatedEvent> sink;

    @Autowired
    public YankiAccountProcessor(Sinks.Many<YankiCreatedEvent> sink) {
        this.sink = sink;
    }

    public void process(YankiAccount account) {
        var yankiCreatedEvent = new YankiCreatedEvent();
        yankiCreatedEvent.setYankiId(account.getId());
        yankiCreatedEvent.setPhoneNumber(account.getPhoneNumber());
        log.info("Sending yankiCreatedEvent : {}", yankiCreatedEvent);
        sink.emitNext(yankiCreatedEvent, Sinks.EmitFailureHandler.FAIL_FAST);
    }

}
