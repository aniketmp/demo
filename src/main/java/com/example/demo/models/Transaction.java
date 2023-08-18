package com.example.demo.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;

public class Transaction implements Serializable {
    private final String from;
    private final String to;
    private final int amount;
    private final String description;

    public Transaction(String from, String to, int amount, String description) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.description = description;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
