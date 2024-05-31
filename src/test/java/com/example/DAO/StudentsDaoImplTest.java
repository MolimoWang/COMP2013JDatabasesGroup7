package com.example.DAO;

import com.example.model.Person;
import com.example.model.Student;
import com.example.model.Paper;
import com.example.model.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
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
    public void setUp() throws SQLException {
        studentsDao = new StudentsDaoImpl();
        papersDao = new PapersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        personDao = new PersonDaoImpl();

        subject = new Subject();
        subject.setSubjectId(1000);  // Changed to a larger number
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        paper = new Paper();
        paper.setPaperId(1000);  // Changed to a larger number
        paper.setTitle("Test Paper Title");
        paper.setYear(2022);
        paper.setSubjectId(1000);  // Changed to a larger number
        papersDao.insert(paper);

        person = new Person();
        person.setPersonId(1000);  // Changed to a larger number
        person.setName("Test Person");
        personDao.insert(person);

        student = new Student();
        student.setStudentId(1000);  // Changed to a larger number
        student.setPersonId(1000);  // Changed to a larger number
        studentsDao.insert(student);
    }

    @Test
    void insert() {
        Student insertedStudent = studentsDao.findById(1000);  // Changed to a larger number
        assertNotNull(insertedStudent);
        assertEquals(1000, insertedStudent.getStudentId());  // Changed to a larger number
        assertEquals(1000, insertedStudent.getPersonId());  // Changed to a larger number
    }

    @Test
    void deleteById() {
        studentsDao.deleteById(1000);  // Changed to a larger number
        Student deletedStudent = studentsDao.findById(1000);  // Changed to a larger number
        assertNull(deletedStudent);
    }

    @Test
    void findById() {
        Student foundStudent = studentsDao.findById(1000);  // Changed to a larger number
        assertNotNull(foundStudent);
        assertEquals(1000, foundStudent.getStudentId());  // Changed to a larger number
        assertEquals(1000, foundStudent.getPersonId());  // Changed to a larger number
    }

    @Test
    void findAll() {
        List<Student> students = studentsDao.findAll();
        assertFalse(students.isEmpty());
        assertTrue(students.stream().anyMatch(s -> s.getStudentId() == 1000));  // Changed to a larger number
    }

    @AfterEach
    public void tearDown() throws SQLException {
        studentsDao.deleteById(1000);  // Changed to a larger number
        papersDao.deleteById(1000);  // Changed to a larger number
        subjectsDao.deleteById(1000);  // Changed to a larger number
        personDao.deleteById(1000);  // Changed to a larger number
    }
}