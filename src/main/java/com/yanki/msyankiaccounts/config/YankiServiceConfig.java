package com.yanki.msyankiaccounts.config;

import com.yanki.msyankiaccounts.model.AddDebitCardEvent;
import com.yanki.msyankiaccounts.model.YankiCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class YankiServiceConfig {

//    private final EventConsumer<YankiCreatedEvent> eventConsumer;
//
//    @Autowired
//    public YankiServiceConfig(EventConsumer<YankiCreatedEvent> eventConsumer) {
//        this.eventConsumer = eventConsumer;
//    }

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


}
