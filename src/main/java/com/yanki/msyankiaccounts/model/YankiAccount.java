package com.yanki.msyankiaccounts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@Document
public class YankiAccount {

    private String id;
    private String imei;
    private String phoneNumber;
    private String documentNumber;
    private String email;
    private Date createdAt;
    private Date modifiedAt;
    private BigDecimal balance;
}
