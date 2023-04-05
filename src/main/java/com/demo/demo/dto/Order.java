package com.demo.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Order {
    private long id;
    private String customer;
    private Date date;
    private BigDecimal amount;

}
