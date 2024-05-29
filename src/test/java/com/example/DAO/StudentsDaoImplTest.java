package com.example.DAO;

import com.example.model.Person;
import com.example.model.Student;
import com.example.model.Paper;
import com.example.model.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentsDaoImplTest {
    private StudentsDaoImpl studentsDao;
    private PapersDaoImpl papersDao;
    private SubjectsDaoImpl subjectsDao;
    private PersonDaoImpl personDao;
    private Student student;
    private Paper paper;
    private Subject subject;
    private Person person;

    @BeforeEach
    public void setUp() {
        // Initialize the DAO objects
        studentsDao = new StudentsDaoImpl();
        papersDao = new PapersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        personDao = new PersonDaoImpl();  // Assuming you have a PersonsDaoImpl class

        // Create and insert a test Subject object into the database
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        // Create and insert a test Paper object into the database
        paper = new Paper();
        paper.setPaperId(1);
        paper.setTitle("Test Paper Title");
        paper.setYear(2022);
        paper.setSubjectId(1);  // This should match the SubjectID of the inserted Subject object
        papersDao.insert(paper);

        // Create and insert a test Person object into the database
        person = new Person();
        person.setPersonId(1);
        person.setName("Test Person");
        personDao.insert(person);  // This should be done before inserting a Student

        // Create and insert a test Student object into the database
        student = new Student();
        student.setStudentId(1);
        student.setPersonId(1);  // This should match the PersonID of the inserted Person object
        studentsDao.insert(student);
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test data by deleting the test Student, Paper, Subject, and Person objects from the database
        studentsDao.deleteById(1);
        papersDao.deleteById(1);
        subjectsDao.deleteById(1);
        personDao.deleteById(1);  // This should be done after deleting a Student
    }

    @Test
    void insert() {
        // Retrieve the inserted Student object from the database
        Student insertedStudent = studentsDao.findById(1);

        // Assert that the retrieved Student object is not null
        assertNotNull(insertedStudent);

        // Assert that the retrieved Student object has the expected properties
        assertEquals(1, insertedStudent.getStudentId());
        assertEquals(1, insertedStudent.getPersonId());
    }

    @Test
    void deleteById() {
        // Delete the inserted Student object from the database
        studentsDao.deleteById(1);

        // Try to retrieve the deleted Student object from the database
        Student deletedStudent = studentsDao.findById(1);

        // Assert that the deleted Student object cannot be retrieved (is null)
        assertNull(deletedStudent);
    }

    @Test
    void findById() {
        // Retrieve the inserted Student object from the database
        Student foundStudent = studentsDao.findById(1);

        // Assert that the retrieved Student object is not null
        assertNotNull(foundStudent);

        // Assert that the retrieved Student object has the expected properties
        assertEquals(1, foundStudent.getStudentId());
        assertEquals(1, foundStudent.getPersonId());
    }

    @Test
    void findAll() {
        // Retrieve all Student objects from the database
        List<Student> students = studentsDao.findAll();

        // Assert that the retrieved list of Student objects is not empty
        assertFalse(students.isEmpty());

        // Assert that the retrieved list of Student objects contains the inserted Student object
        assertTrue(students.stream().anyMatch(s -> s.getStudentId() == 1));
    }
}