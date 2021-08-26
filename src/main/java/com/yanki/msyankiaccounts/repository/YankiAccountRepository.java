package com.yanki.msyankiaccounts.repository;

import com.yanki.msyankiaccounts.model.YankiAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface YankiAccountRepository extends ReactiveMongoRepository<YankiAccount, String> {

    Mono<YankiAccount> findByPhoneNumber(String phoneNumber);
}
