package com.yanki.msyankiaccounts.controller;

import com.yanki.msyankiaccounts.model.YankiAccount;
import com.yanki.msyankiaccounts.service.YankiAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("yanki-accounts")
public class YankiAccountController {

    @Autowired
    private YankiAccountService service;

    @PostMapping
    public Mono<YankiAccount> createYankiAccount(@RequestBody YankiAccount account) {
        return service.create(account);
    }

    @GetMapping("/yanki")
    public Mono<YankiAccount> findByPhoneNumber(@RequestParam String phoneNumber) {
        return service.findByPhoneNumber(phoneNumber);
    }

    @PostMapping("/debit-card")
    public void addDebitCard(@RequestParam String cardNumber,
                             @RequestParam String yankId) {
        service.addDebitCard(cardNumber, yankId);
    }
}
