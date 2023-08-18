package com.example.demo;

import com.example.demo.dtos.ExpenseReq;
import com.example.demo.models.Settlement;
import com.example.demo.models.Transaction;
import com.example.demo.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public String index() {
        return "Hello world from Spring Boot!";
    }
    @PostMapping("/expenses")
    public ResponseEntity<String> addExpense(@RequestBody ExpenseReq expenseReq) {
        expenseService.addExpense(expenseReq.getFrom(),expenseReq.getDividedBy(),expenseReq.getAmount(),expenseReq.getDescription());
         return new ResponseEntity<>(
                "Expense saved successfully!",
                HttpStatus.OK);
    }
    @GetMapping(value = "/expenses/result",produces = "application/json")
    public ResponseEntity<?> calculateResult() {
        Set<Settlement> settlements=expenseService.calculateExpense();
        return new ResponseEntity<>(
                settlements,
                HttpStatus.OK);
    }

}
