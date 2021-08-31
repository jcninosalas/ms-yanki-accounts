package com.yanki.msyankiaccounts.repository;

import com.yanki.msyankiaccounts.model.YankiAccount;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class RedisRepository {

    private final ReactiveRedisOperations<String, String> operations;

    public RedisRepository(ReactiveRedisOperations<String, String> operations) {
        this.operations = operations;
    }

    public Mono<YankiAccount> save (YankiAccount account) {
        return operations.opsForValue()
                .set(account.getId(), account.getImei())
                .map(c -> account);
    }

    public Mono<YankiAccount> findByKey(String key) {
        return operations.opsForValue()
                .get(key)
                .map(result -> YankiAccount.builder()
                        .id(key)
                        .imei(result)
                        .build());
    }
}
