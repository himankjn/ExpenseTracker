package com.himankjn.mongodbdemo.dao;

import com.himankjn.mongodbdemo.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ExpenseDao extends MongoRepository<Expense,String>{
    @Query("{'name':  ?0}")
    Expense findByName(String name);

}
