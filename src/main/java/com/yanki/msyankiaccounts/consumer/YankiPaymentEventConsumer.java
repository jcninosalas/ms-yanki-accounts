package com.yanki.msyankiaccounts.consumer;

import com.yanki.msyankiaccounts.event.YankiPaymentEvent;
import com.yanki.msyankiaccounts.repository.YankiAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class YankiPaymentEventConsumer implements EventConsumer<YankiPaymentEvent>{

    @Autowired
    private YankiAccountRepository repository;

    @Override
    public void consumeEvent(YankiPaymentEvent event) {
        repository.findByPhoneNumber(event.getPhoneNumber())
                .flatMap(account -> {
                    account.setBalance(account.getBalance().subtract(event.getAmmount()));
                    return repository.save(account); //TODO publicar evento para el servicio de transacciones
                });
                //.defaultIfEmpty(); TODO publicar evento cuenta no existe!
    }
}
