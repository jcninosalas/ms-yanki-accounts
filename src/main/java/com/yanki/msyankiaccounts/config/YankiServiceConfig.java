package com.yanki.msyankiaccounts.config;

import com.yanki.msyankiaccounts.consumer.EventConsumer;
import com.yanki.msyankiaccounts.event.TransactionYankiEvent;
import com.yanki.msyankiaccounts.event.YankiCreatedEvent;
import com.yanki.msyankiaccounts.event.YankiPaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class YankiServiceConfig {

    private final EventConsumer<TransactionYankiEvent> transactionEventConsumer;
    private final EventConsumer<YankiPaymentEvent> yankiPaymentEventEventConsumer;

    @Autowired
    public YankiServiceConfig(
            EventConsumer<TransactionYankiEvent> transactionEventConsumer,
            EventConsumer<YankiPaymentEvent> yankiPaymentEventEventConsumer) {
        this.transactionEventConsumer = transactionEventConsumer;
        this.yankiPaymentEventEventConsumer = yankiPaymentEventEventConsumer;
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

    @Bean Consumer<YankiPaymentEvent> yankiPaymentEventConsumer() {
        return yankiPaymentEventEventConsumer::consumeEvent;
    }


}
