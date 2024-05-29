package com.example.DAO;

import com.example.model.Person;
import com.example.model.Teacher;
import com.example.model.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeachersDaoImplTest {
    private TeachersDaoImpl teachersDao;
    private SubjectsDaoImpl subjectsDao;
    private PersonDaoImpl personDao; // Assuming you've created this DAO
    private Teacher teacher;
    private Subject subject;
    private Person person; // Create a Person object

    @BeforeEach
    public void setUp() {
        teachersDao = new TeachersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        personDao = new PersonDaoImpl(); // Initialize the PersonDao
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        person = new Person(); // Initialize the Person object
        person.setPersonId(1);
        person.setName("Test Person");
        personDao.insert(person); // Insert the Person object into the database

        teacher = new Teacher();
        teacher.setTeacherId(1);
        teacher.setPersonId(1); // Set the PersonID instead of the name
        teacher.setSubjectId(1);
        teachersDao.insert(teacher);
    }

    @AfterEach
    public void tearDown() {
        teachersDao.deleteById(1);
        subjectsDao.deleteById(1);
        personDao.deleteById(1); // Delete the Person object from the database
    }

    @Test
    void insert() {
        Teacher insertedTeacher = teachersDao.findById(1);
        assertNotNull(insertedTeacher);
        assertEquals(1, insertedTeacher.getTeacherId());
        assertEquals(1, insertedTeacher.getPersonId()); // Check the PersonID instead of the name
        assertEquals(1, insertedTeacher.getSubjectId());
    }

    @Test
    void deleteById() {
        teachersDao.deleteById(1);
        Teacher deletedTeacher = teachersDao.findById(1);
        assertNull(deletedTeacher);
    }

    @Test
    void findById() {
        Teacher foundTeacher = teachersDao.findById(1);
        assertNotNull(foundTeacher);
        assertEquals(1, foundTeacher.getTeacherId());
        assertEquals(1, foundTeacher.getPersonId()); // Check the PersonID instead of the name
        assertEquals(1, foundTeacher.getSubjectId());
    }

    @Test
    void findAll() {
        List<Teacher> teachers = teachersDao.findAll();
        assertFalse(teachers.isEmpty());
        assertTrue(teachers.stream().anyMatch(t -> t.getTeacherId() == 1));
    }
}