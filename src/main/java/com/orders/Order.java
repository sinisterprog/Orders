package com.orders;

public class Order {
    public Order(String coin, double amount, String extra1, String extra2) {
        this.coin = coin;
        this.amount = amount;
        this.extra1 = extra1;
        this.extra2 = extra2;
    }

    private String coin;
    private  double amount;
    private  String extra1;
    private String extra2;

    public Order(){}

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }
}
