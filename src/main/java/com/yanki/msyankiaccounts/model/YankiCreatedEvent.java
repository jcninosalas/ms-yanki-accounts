package com.yanki.msyankiaccounts.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class YankiCreatedEvent implements Event {

    private static final String EVENT = "YankiAccountCreated";
    private String yankiId;
    private String phoneNumber;

    @Override
    public String getEvent() {
        return EVENT;
    }
}
