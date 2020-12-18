package com.cybertek.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass // annotation used for parent class.
public class Account {

    @Id
    private Long id;
    private String owner;
    private BigDecimal balance;
    private BigDecimal interestRate;
}
