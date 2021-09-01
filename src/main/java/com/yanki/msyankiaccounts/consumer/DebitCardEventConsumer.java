package com.yanki.msyankiaccounts.consumer;

import com.yanki.msyankiaccounts.event.AddDebitAccountEvent;
import com.yanki.msyankiaccounts.repository.YankiAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DebitCardEventConsumer implements EventConsumer<AddDebitAccountEvent>{

    @Autowired
    private YankiAccountRepository repository;

    @Override
    public void consumeEvent(AddDebitAccountEvent event) {

    }
}
