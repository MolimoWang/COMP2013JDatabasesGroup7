package com.example.DAO;

import com.example.model.Person;
import java.util.List;

public interface PersonDao {
    // CRUD operations
    void insert(Person person);
    void deleteById(int personId);
    Person findById(int personId);
    List<Person> findAll();
    void update(Person person);
}