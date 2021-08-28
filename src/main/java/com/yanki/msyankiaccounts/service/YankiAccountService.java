package com.yanki.msyankiaccounts.service;

import com.yanki.msyankiaccounts.model.YankiAccount;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public interface YankiAccountService {

    Mono<YankiAccount> create(YankiAccount account);

    Mono<YankiAccount> findByPhoneNumber(String phoneNumber);

    void addDebitCard(String cardNumber, String yankiId);
}
