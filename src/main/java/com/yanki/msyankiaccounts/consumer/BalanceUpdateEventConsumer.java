package com.yanki.msyankiaccounts.consumer;

import com.yanki.msyankiaccounts.event.TransactionYankiEvent;
import com.yanki.msyankiaccounts.repository.YankiAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class BalanceUpdateEventConsumer implements EventConsumer<TransactionYankiEvent>{

    @Autowired
    private YankiAccountRepository repository;

    @Override
    public void consumeEvent(TransactionYankiEvent event) {
        log.info("Transaction event recieved: {}", event.toString());
//        repository.findByPhoneNumber(event.getPhoneNumber())
//                .doOnSuccess( account -> {
//                    account.setBalance(BigDecimal.valueOf(event.getBalance()));
//                    repository.save(account).subscribe();
//                });
        repository.findByPhoneNumber(event.getPhoneNumber())
                .flatMap( account -> {
                    account.setBalance(BigDecimal.valueOf(event.getBalance()));
                    return repository.save(account);
                }).subscribe();

    }
}
