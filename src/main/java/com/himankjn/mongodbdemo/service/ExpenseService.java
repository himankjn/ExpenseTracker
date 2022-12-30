package com.himankjn.mongodbdemo.service;

import com.himankjn.mongodbdemo.dao.ExpenseDao;
import com.himankjn.mongodbdemo.model.Expense;
import com.himankjn.mongodbdemo.model.ExpenseCategory;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseDao expenseDao;

    public ExpenseService(ExpenseDao expenseDao){
        this.expenseDao= expenseDao;
    }
    public void addExpense(Expense expense){
        expenseDao.insert(expense);
    }

    public void updateExpense(Expense expense) throws Throwable {
        Expense savedExpense= (Expense) expenseDao.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("cannot find expense by ID %s", expense.getId())));

        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseDao.save(savedExpense);
    }

    public List<Expense> getAllExpense(){
        return expenseDao.findAll();
    }

    public Expense getExpenseByName(String name){
        return expenseDao.findByName(name);
    }

    public void deleteExpense(String id){
        expenseDao.deleteById(id);
    }


}
