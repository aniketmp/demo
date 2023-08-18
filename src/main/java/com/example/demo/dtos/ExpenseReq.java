package com.example.demo.dtos;

import java.io.Serializable;
import java.util.List;

public class ExpenseReq implements Serializable {
    private String from;
    private List<String> dividedBy;
    private int amount;
    private String description;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getDividedBy() {
        return dividedBy;
    }

    public void setDividedBy(List<String> dividedBy) {
        this.dividedBy = dividedBy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExpenseReq{" +
                "from='" + from + '\'' +
                ", dividedBy=" + dividedBy +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
