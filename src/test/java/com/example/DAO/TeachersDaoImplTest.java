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
    private PersonDaoImpl personDao;
    private TeacherSubjectsDaoImpl teacherSubjectsDao;

    private Teacher teacher;
    private Subject subject;
    private Person person; // Create a Person object


    @BeforeEach
    public void setUp() {
        // Initialize the DAO objects
        teachersDao = new TeachersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        personDao = new PersonDaoImpl(); // Initialize the PersonDao
        teacherSubjectsDao = new TeacherSubjectsDaoImpl();

        // Create and insert a test Subject object into the database
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        // Create and insert a test Person object into the database
        person = new Person(); // Initialize the Person object
        person.setPersonId(1);
        person.setName("Test Person");
        personDao.insert(person); // Insert the Person object into the database

        // Create and insert a test Teacher object into the database
        teacher = new Teacher();
        teacher.setTeacherId(1);
        teacher.setPersonId(1); // Set the PersonID instead of the name
        teachersDao.insert(teacher);

        // Insert the Teacher-Subject relationship into the database
        teacherSubjectsDao.insert(1, 1);  // Add this line
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test data by deleting the test Teacher, Subject, and Person objects from the database
        teacherSubjectsDao.deleteByTeacherIdAndSubjectId(1, 1);  // Add this line
        teachersDao.deleteById(1);
        subjectsDao.deleteById(1);
        personDao.deleteById(1);  // Add this line
    }

    @Test
    void insert() {
        // Retrieve the inserted Teacher object from the database
        Teacher insertedTeacher = teachersDao.findById(1);

        // Assert that the retrieved Teacher object is not null
        assertNotNull(insertedTeacher);

        // Assert that the retrieved Teacher object has the expected properties
        assertEquals(1, insertedTeacher.getTeacherId());
        assertEquals(1, insertedTeacher.getPersonId()); // Check the PersonID instead of the name

        // Retrieve the associated Subject IDs from the database
        List<Integer> subjectIds = teacherSubjectsDao.findSubjectIdsByTeacherId(1);

        // Assert that the retrieved Subject IDs include the expected Subject ID
        assertTrue(subjectIds.contains(1));
    }

    @Test
    void deleteById() {
        // Delete the inserted Teacher object from the database
        teachersDao.deleteById(1);

        // Try to retrieve the deleted Teacher object from the database
        Teacher deletedTeacher = teachersDao.findById(1);

        // Assert that the deleted Teacher object cannot be retrieved (is null)
        assertNull(deletedTeacher);
    }

    @Test
    void findById() {
        // Retrieve the inserted Teacher object from the database
        Teacher foundTeacher = teachersDao.findById(1);

        // Assert that the retrieved Teacher object is not null
        assertNotNull(foundTeacher);

        // Assert that the retrieved Teacher object has the expected properties
        assertEquals(1, foundTeacher.getTeacherId());
        assertEquals(1, foundTeacher.getPersonId()); // Check the PersonID instead of the name
        assertEquals(1, foundTeacher.getSubjectId());

        // Retrieve the associated Person and Subject objects from the database
        Person associatedPerson = personDao.findById(foundTeacher.getPersonId());
        Subject associatedSubject = subjectsDao.findById(foundTeacher.getSubjectId());

        // Assert that the retrieved Person and Subject objects have the expected properties
        assertEquals("Test Person", associatedPerson.getName());
        assertEquals("Test Subject", associatedSubject.getName());
    }

    @Test
    void findAll() {
        // Retrieve all Teacher objects from the database
        List<Teacher> teachers = teachersDao.findAll();

        // Assert that the retrieved list of Teacher objects is not empty
        assertFalse(teachers.isEmpty());

        // Assert that the retrieved list of Teacher objects contains the inserted Teacher object
        assertTrue(teachers.stream().anyMatch(t -> t.getTeacherId() == 1));
    }
}