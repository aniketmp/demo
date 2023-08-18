package com.example.demo;

import com.example.demo.dtos.ExpenseReq;
import com.example.demo.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppSettingsController {
    @Autowired
    private AppService appService;
    @PostMapping("/names")
    public ResponseEntity<String> addName(@RequestParam String name) {
        appService.addName(name);
        return new ResponseEntity<>(
                "Expense saved successfully!"+appService.getNames(),
                HttpStatus.OK);
    }
}
