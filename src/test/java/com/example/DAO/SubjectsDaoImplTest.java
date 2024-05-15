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
        subjectsDao = new SubjectsDaoImpl();
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);
    }

    @AfterEach
    public void tearDown() {
        subjectsDao.deleteById(1);
    }

    @Test
    void insert() {
        Subject insertedSubject = subjectsDao.findById(1);
        assertNotNull(insertedSubject);
        assertEquals(1, insertedSubject.getSubjectId());
        assertEquals("Test Subject", insertedSubject.getName());
    }

    @Test
    void deleteById() {
        subjectsDao.deleteById(1);
        Subject deletedSubject = subjectsDao.findById(1);
        assertNull(deletedSubject);
    }

    @Test
    void findById() {
        Subject foundSubject = subjectsDao.findById(1);
        assertNotNull(foundSubject);
        assertEquals(1, foundSubject.getSubjectId());
        assertEquals("Test Subject", foundSubject.getName());
    }

    @Test
    void findAll() {
        List<Subject> subjects = subjectsDao.findAll();
        assertFalse(subjects.isEmpty());
        assertTrue(subjects.stream().anyMatch(s -> s.getSubjectId() == 1));
    }
}