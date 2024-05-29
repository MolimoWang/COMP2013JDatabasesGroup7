package com.example.DAO;

import com.example.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoImplTest {
    private PersonDaoImpl personDao; // Assuming you've created this DAO
    private Person person; // Create a Person object

    @BeforeEach
    void setUp() {
        // Initialize the DAO object
        personDao = new PersonDaoImpl();

        // Create and insert a test Person object into the database
        person = new Person();
        person.setPersonId(1);
        person.setName("Test Person");
        personDao.insert(person);
    }

    @AfterEach
    void tearDown() {
        // Clean up the test data by deleting the test Person object from the database
        personDao.deleteById(1);
    }

    @Test
    void insert() {
        // Retrieve the inserted Person object from the database
        Person insertedPerson = personDao.findById(1);

        // Assert that the retrieved Person object is not null
        assertNotNull(insertedPerson);

        // Assert that the retrieved Person object has the expected properties
        assertEquals(1, insertedPerson.getPersonId());
        assertEquals("Test Person", insertedPerson.getName());
    }

    @Test
    void deleteById() {
        // Delete the inserted Person object from the database
        personDao.deleteById(1);

        // Try to retrieve the deleted Person object from the database
        Person deletedPerson = personDao.findById(1);

        // Assert that the deleted Person object cannot be retrieved (is null)
        assertNull(deletedPerson);
    }

    @Test
    void findById() {
        // Retrieve the inserted Person object from the database
        Person foundPerson = personDao.findById(1);

        // Assert that the retrieved Person object is not null
        assertNotNull(foundPerson);

        // Assert that the retrieved Person object has the expected properties
        assertEquals(1, foundPerson.getPersonId());
        assertEquals("Test Person", foundPerson.getName());
    }

    @Test
    void findAll() {
        // Retrieve all Person objects from the database
        List<Person> persons = personDao.findAll();

        // Assert that the retrieved list of Person objects is not empty
        assertFalse(persons.isEmpty());

        // Assert that the retrieved list of Person objects contains the inserted Person object
        assertTrue(persons.stream().anyMatch(p -> p.getPersonId() == 1));
    }

    @Test
    void update() {
        // Update the inserted Person object in the database
        person.setName("Updated Person");
        personDao.update(person);

        // Retrieve the updated Person object from the database
        Person updatedPerson = personDao.findById(1);

        // Assert that the retrieved Person object is not null
        assertNotNull(updatedPerson);

        // Assert that the retrieved Person object has the updated properties
        assertEquals(1, updatedPerson.getPersonId());
        assertEquals("Updated Person", updatedPerson.getName());
    }
}