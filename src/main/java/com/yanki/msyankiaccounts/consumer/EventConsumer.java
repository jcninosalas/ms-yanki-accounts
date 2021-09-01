package com.yanki.msyankiaccounts.consumer;

import com.yanki.msyankiaccounts.event.Event;

public interface EventConsumer<T extends Event> {

    void consumeEvent(T event);
}
