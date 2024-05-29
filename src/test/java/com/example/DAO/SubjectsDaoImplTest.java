package com.example.DAO;

import com.example.model.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubjectsDaoImplTest {
    private SubjectsDaoImpl subjectsDao;
    private Subject subject;

    @BeforeEach
    public void setUp() {
        // Initialize the DAO object
        subjectsDao = new SubjectsDaoImpl();

        // Create and insert a test Subject object into the database
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test data by deleting the test Subject object from the database
        subjectsDao.deleteById(1);
    }

    @Test
    void insert() {
        // Retrieve the inserted Subject object from the database
        Subject insertedSubject = subjectsDao.findById(1);

        // Assert that the retrieved Subject object is not null
        assertNotNull(insertedSubject);

        // Assert that the retrieved Subject object has the expected properties
        assertEquals(1, insertedSubject.getSubjectId());
        assertEquals("Test Subject", insertedSubject.getName());
    }

    @Test
    void deleteById() {
        // Delete the inserted Subject object from the database
        subjectsDao.deleteById(1);

        // Try to retrieve the deleted Subject object from the database
        Subject deletedSubject = subjectsDao.findById(1);

        // Assert that the deleted Subject object cannot be retrieved (is null)
        assertNull(deletedSubject);
    }

    @Test
    void findById() {
        // Retrieve the inserted Subject object from the database
        Subject foundSubject = subjectsDao.findById(1);

        // Assert that the retrieved Subject object is not null
        assertNotNull(foundSubject);

        // Assert that the retrieved Subject object has the expected properties
        assertEquals(1, foundSubject.getSubjectId());
        assertEquals("Test Subject", foundSubject.getName());
    }

    @Test
    void findAll() {
        // Retrieve all Subject objects from the database
        List<Subject> subjects = subjectsDao.findAll();

        // Assert that the retrieved list of Subject objects is not empty
        assertFalse(subjects.isEmpty());

        // Assert that the retrieved list of Subject objects contains the inserted Subject object
        assertTrue(subjects.stream().anyMatch(s -> s.getSubjectId() == 1));
    }
}