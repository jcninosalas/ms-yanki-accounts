package com.yanki.msyankiaccounts.config;

import com.yanki.msyankiaccounts.consumer.EventConsumer;
import com.yanki.msyankiaccounts.model.AddDebitCardEvent;
import com.yanki.msyankiaccounts.model.TransactionYankiEvent;
import com.yanki.msyankiaccounts.model.YankiCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class YankiServiceConfig {

//    private final EventConsumer<YankiCreatedEvent> eventConsumer;
//
//    @Autowired
//    public YankiServiceConfig(EventConsumer<YankiCreatedEvent> eventConsumer) {
//        this.eventConsumer = eventConsumer;
//    }

    private final EventConsumer<TransactionYankiEvent> transactionEventConsumer;

    @Autowired
    public YankiServiceConfig(EventConsumer<TransactionYankiEvent> transactionEventConsumer) {
        this.transactionEventConsumer = transactionEventConsumer;
    }

    @Bean
    Sinks.Many<YankiCreatedEvent> sink() {
        return Sinks.many()
                .multicast()
                .directBestEffort();
    }

    @Bean
    public Supplier<Flux<YankiCreatedEvent>> yankiCreatedEventPublisher(Sinks.Many<YankiCreatedEvent> publisher) {
        return publisher::asFlux;
    }

    @Bean
    public Consumer<TransactionYankiEvent> transactionEventProcessor() {
        return transactionEventConsumer::consumeEvent;
    }


}
