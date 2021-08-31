package com.yanki.msyankiaccounts.consumer;

import com.yanki.msyankiaccounts.model.Event;
import com.yanki.msyankiaccounts.model.TransactionYankiEvent;
import com.yanki.msyankiaccounts.repository.YankiAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BalanceUpdateEventConsumer implements EventConsumer<TransactionYankiEvent>{

    @Autowired
    private YankiAccountRepository repository;

    @Override
    public void consumeEvent(TransactionYankiEvent event) {
        repository.findByPhoneNumber(event.getPhoneNumber())
                .flatMap( yanki -> {
                    var balance = BigDecimal.valueOf(event.getBalance());
                    yanki.setBalance(balance);
                    return repository.save(yanki);
                });
    }
}
