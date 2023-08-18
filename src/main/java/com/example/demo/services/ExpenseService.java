package com.example.demo.services;

import com.example.demo.models.Expense;
import com.example.demo.models.Settlement;
import com.example.demo.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseService {
    public ExpenseService(){
        expenses.add(new Expense("aniket", Arrays.asList("aniket","pratik","nandan"),600,"Petrol"));
        expenses.add(new Expense("pratik", Arrays.asList("aniket","pratik","nandan"),300,"Petrol"));
        expenses.add(new Expense("divyesh", Arrays.asList("aniket","pratik","nandan","divyesh"),800,"Petrol"));
    }

    @Autowired
    private AppService appService;
    private List<Expense> expenses=new ArrayList<>();
    public void addExpense(String from,List<String> dividedBy,int amount,String description){
        expenses.add(new Expense(from,dividedBy,amount,description));
    }
    public Set<Settlement> calculateExpense(){
        Map<String,Map<String,Integer>> settlementMap=new HashMap<>();
        List<Transaction> transactionList=new ArrayList<>();
//        expenses.stream().peek(System.out::println).flatMap(expense -> Stream.of(expense.getTransactions())).;
        for(Expense expense:expenses){
            transactionList.addAll(expense.getTransactions());
        }
        for(Transaction transaction:transactionList){
            Map<String,Integer> existCreditMap=settlementMap.get(transaction.getFrom());
            if(existCreditMap==null){
                Map<String,Integer> creditMap=new HashMap<>();
                creditMap.put(transaction.getTo(),transaction.getAmount());
                settlementMap.put(transaction.getFrom(),creditMap);
            }
            else {
                Integer amount=existCreditMap.get(transaction.getTo());
                if(amount==null)
                    existCreditMap.put(transaction.getTo(),transaction.getAmount());
                else
                    existCreditMap.put(transaction.getTo(),amount+transaction.getAmount());
            }
        }
        List<String> names=appService.getNames();
        Set<Settlement> res=new LinkedHashSet<>();
        for (Map.Entry<String,Map<String,Integer>> entry : settlementMap.entrySet()){
            //get a
            String personName=entry.getKey();
            //check a's credit entry
            Map<String, Integer> creditNoteMap=entry.getValue();
            //calculate result against every member in the group
            for(String name:names){
                //Ignore if a comes against a
                if(!personName.equals(name)){
                    //if b come, check how much amount b owns me
                    Integer creditAmt=creditNoteMap.get(name);
                    //If b is not in my credit note list, go to next person
                    if(creditAmt==null){
                        continue;
                    }
                    //check b credit note
                    Map<String, Integer> dues=settlementMap.get(name);
                    System.out.println("name:"+dues);

                    //If b's credit note is not empty then check whether a present in his credit note
                    if(dues!=null){
                        //checking if me owns some amt to b
                        Integer debitAmt=dues.get(personName);
                        if(debitAmt!=null){
                            if(creditAmt-debitAmt>0){
                                //create credit note
                                res.add(new Settlement(name,personName,creditAmt-debitAmt));
                            }else{
                                //create debit note
                                res.add(new Settlement(personName,name,debitAmt-creditAmt));
                            }
                        }
                        else{
                            res.add(new Settlement(name,personName,creditAmt));
                        }
                    }
                    else//If b's credit note is empty then directly create credit note.
                    {
                        res.add(new Settlement(name,personName,creditAmt));
                    }
                }
            }
        }
            return res;
    }
}








