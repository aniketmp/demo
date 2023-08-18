package com.example.demo.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Expense implements Serializable {
    private final String from;
    private final  List<String> dividedBy;
    private final  int amount;
    private final String description;
    private final List<Transaction> transactions;

    public Expense(String from, List<String> dividedBy, int amount, String description) {
        this.from = from;
        this.dividedBy = dividedBy;
        this.amount = amount;
        this.description = description;
        transactions=new ArrayList<>();
        int share=amount/dividedBy.size();
        for(String to:dividedBy){
            if(!from.equals(to))
                transactions.add(new Transaction(from,to,share,description));
        }
    }

    public String getFrom() {
        return from;
    }

    public List<String>  getDividedBy() {
        return dividedBy;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "from='" + from + '\'' +
                ", dividedBy=" + dividedBy +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}
