package com.orders.repo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

@Entity
public class OrderPersist {

    @Id
    @GeneratedValue
    Long id;
    String coin;
    double amount;
    String extra1;
    String extra2;

    public OrderPersist(String coin, double amount, String extra1, String extra2) {
        this.coin = coin;
        this.amount = amount;
        this.extra1 = extra1;
        this.extra2 = extra2;
    }

    public OrderPersist() {
    }

    @Pattern(regexp="(ETH|ADA|XLM)")
    public String getCoin() {
        return coin;
    }

    @DecimalMin("0.0")
    public double getAmount() {
        return amount;
    }

    public String getExtra1() {
        return extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public Long getId() {
        return this.id;
    }
}
