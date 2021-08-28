package com.yanki.msyankiaccounts.service.impl;

import com.yanki.msyankiaccounts.model.YankiAccount;
import com.yanki.msyankiaccounts.processor.AddDebitCardProcessor;
import com.yanki.msyankiaccounts.processor.YankiAccountProcessor;
import com.yanki.msyankiaccounts.repository.YankiAccountRepository;
import com.yanki.msyankiaccounts.service.YankiAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class YankiAccountServiceImpl implements YankiAccountService {

    @Autowired
    private YankiAccountRepository repository;

    @Autowired
    private YankiAccountProcessor yankiAccountProcessor;

    @Autowired
    private AddDebitCardProcessor addDebitCardProcessor;

    @Override
    public Mono<YankiAccount> create(YankiAccount account) {
        return repository.save(account)
                .doOnSuccess(c -> yankiAccountProcessor.process(c.getId()));
    }

    @Override
    public Mono<YankiAccount> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public void addDebitCard(String cardNumber, String yankiId) {
        repository.findById(yankiId)
                .doOnSuccess(yankiAccount -> addDebitCardProcessor.process(cardNumber, yankiAccount));

    }

}
