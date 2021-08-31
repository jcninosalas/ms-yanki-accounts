package com.yanki.msyankiaccounts.config;

import com.yanki.msyankiaccounts.model.AddDebitCardEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class DebitCardConfig {

    @Bean
    Sinks.Many<AddDebitCardEvent> cardSink() {
        return Sinks.many()
                .multicast()
                .directBestEffort();
    }

    @Bean
    public Supplier<Flux<AddDebitCardEvent>> addDebitCardEventPublisher(Sinks.Many<AddDebitCardEvent> publisher) {
        return publisher::asFlux;
    }
}
