package com.yanki.msyankiaccounts.service.impl;

import com.yanki.msyankiaccounts.model.YankiAccount;
import com.yanki.msyankiaccounts.processor.AddDebitCardProcessor;
import com.yanki.msyankiaccounts.processor.YankiAccountProcessor;
import com.yanki.msyankiaccounts.repository.YankiAccountRepository;
import com.yanki.msyankiaccounts.service.YankiAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

@Service
@CacheConfig(cacheNames = {"YankiAccount"})
public class YankiAccountServiceImpl implements YankiAccountService {

    @Autowired
    private YankiAccountRepository repository;

    @Autowired
    private YankiAccountProcessor yankiAccountProcessor;

    @Autowired
    private AddDebitCardProcessor addDebitCardProcessor;

    @Override
    public Mono<YankiAccount> create(YankiAccount account) {
        account.setBalance(new BigDecimal(0));
        account.setCreatedAt(new Date());
        return repository.save(account)
                .doOnSuccess(c -> yankiAccountProcessor.process(c));
    }

//    @Override
//    @Cacheable(key = "#phoneNumber")
//    public Mono<YankiAccount> findByPhoneNumber(String phoneNumber) {
//        return repository.findByPhoneNumber(phoneNumber)
//                .switchIfEmpty(Mono.empty());
//    }

    @Override
    @Cacheable(key = "#phoneNumber")
    public Mono<YankiAccount> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber).cache();
                //.block(Duration.of(1000, ChronoUnit.MILLIS))
    }



    @Override
    public void addDebitCard(String cardNumber, String yankiId) {
        repository.findById(yankiId)
                .doOnSuccess( account -> addDebitCardProcessor.process(cardNumber, account));

    }

}
