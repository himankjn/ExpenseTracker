package com.himankjn.mongodbdemo.controller;

import com.himankjn.mongodbdemo.model.Expense;
import com.himankjn.mongodbdemo.model.ExpenseCategory;
import com.himankjn.mongodbdemo.service.ExpenseService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService=expenseService;
    }

    @PostMapping
    public ResponseEntity addExpense(@RequestBody Expense expense){
        System.out.println(expense.getId());
        System.out.println(expense.getExpenseName());
        expenseService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateExpense(@RequestBody Expense expense) throws Throwable{
        System.out.println(expense.getId());
        expenseService.updateExpense(expense);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpense(){
        return ResponseEntity.ok(expenseService.getAllExpense());
    }
    @GetMapping("/{name}")
    public ResponseEntity<Expense> getExpenseByName(@PathVariable String name){
        return ResponseEntity.ok(expenseService.getExpenseByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpense(@PathVariable String id){
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
