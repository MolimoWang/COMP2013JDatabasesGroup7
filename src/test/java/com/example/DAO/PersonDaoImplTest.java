package com.example.DAO;

import com.example.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoImplTest {
    private PersonDaoImpl personDao;
    private Person person;

    @BeforeEach
    void setUp() {
        personDao = new PersonDaoImpl();
        person = new Person();
        person.setPersonId(1000);  // Changed to a larger number
        person.setName("Test Person");
        personDao.insert(person);
    }

    @AfterEach
    void tearDown() {
        personDao.deleteById(1000);  // Changed to a larger number
    }

    @Test
    void insert() {
        Person insertedPerson = personDao.findById(1000);  // Changed to a larger number
        assertNotNull(insertedPerson);
        assertEquals(1000, insertedPerson.getPersonId());  // Changed to a larger number
        assertEquals("Test Person", insertedPerson.getName());
    }

    @Test
    void deleteById() {
        personDao.deleteById(1000);  // Changed to a larger number
        Person deletedPerson = personDao.findById(1000);  // Changed to a larger number
        assertNull(deletedPerson);
    }

    @Test
    void findById() {
        Person foundPerson = personDao.findById(1000);  // Changed to a larger number
        assertNotNull(foundPerson);
        assertEquals(1000, foundPerson.getPersonId());  // Changed to a larger number
        assertEquals("Test Person", foundPerson.getName());
    }

    @Test
    void findAll() {
        List<Person> persons = personDao.findAll();
        assertFalse(persons.isEmpty());
        assertTrue(persons.stream().anyMatch(p -> p.getPersonId() == 1000));  // Changed to a larger number
    }

    @Test
    void update() {
        person.setName("Updated Person");
        personDao.update(person);
        Person updatedPerson = personDao.findById(1000);  // Changed to a larger number
        assertNotNull(updatedPerson);
        assertEquals(1000, updatedPerson.getPersonId());  // Changed to a larger number
        assertEquals("Updated Person", updatedPerson.getName());
    }
}