package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AppService {
    private List<String> names=new ArrayList<>();
    public void addName(String name){
        names.add(name);
    }
    public List<String> getNames(){
        return Arrays.asList("aniket","pratik","nandan","divyesh");
//        return  names;
    }
}
