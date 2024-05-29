package com.example.DAO;

import com.example.model.Student;
import com.example.model.Paper;
import com.example.model.Person;
import com.example.model.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentPapersDaoImplTest {
    private StudentPapersDaoImpl studentPapersDao;
    private StudentsDaoImpl studentsDao;
    private PapersDaoImpl papersDao;
    private PersonDaoImpl personDao;
    private SubjectsDaoImpl subjectsDao;

    private int testStudentId = 1;
    private int testPaperId = 1;
    private int testPersonId = 1;
    private int testSubjectId = 1;

    @BeforeEach
    void setUp() throws SQLException {
        studentPapersDao = new StudentPapersDaoImpl();
        studentsDao = new StudentsDaoImpl();
        papersDao = new PapersDaoImpl();
        personDao = new PersonDaoImpl();
        subjectsDao = new SubjectsDaoImpl();

        // Insert a test person into the database
        Person person = new Person();
        person.setPersonId(testPersonId);
        person.setName("Test Person");
        personDao.insert(person);

        // Insert a test subject into the database
        Subject subject = new Subject();
        subject.setSubjectId(testSubjectId);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        // Insert a test student into the database
        Student student = new Student();
        student.setStudentId(testStudentId);
        student.setPersonId(testPersonId);
        studentsDao.insert(student);

        // Insert a test paper into the database
        Paper paper = new Paper();
        paper.setPaperId(testPaperId);
        paper.setTitle("Test Paper");
        paper.setSubjectId(testSubjectId);
        papersDao.insert(paper);

        // Insert a test record into the database
        studentPapersDao.insert(testStudentId, testPaperId);
    }

    @AfterEach
    void tearDown() {
        studentPapersDao.deleteByStudentIdAndPaperId(testStudentId, testPaperId);
        studentsDao.deleteById(testStudentId);
        papersDao.deleteById(testPaperId);
        personDao.deleteById(testPersonId);
        subjectsDao.deleteById(testSubjectId);
    }

    @Test
    void insert() {
        // Retrieve the inserted record from the database
        List<Integer> insertedPaperIds = studentPapersDao.findPaperIdsByStudentId(testStudentId);

        // Assert that the retrieved record is not null
        assertNotNull(insertedPaperIds);

        // Assert that the retrieved record has the expected properties
        assertTrue(insertedPaperIds.contains(testPaperId));
    }

    @Test
    void deleteByStudentIdAndPaperId() {
        // Delete the inserted record from the database
        studentPapersDao.deleteByStudentIdAndPaperId(testStudentId, testPaperId);

        // Try to retrieve the deleted record from the database
        List<Integer> deletedPaperIds = studentPapersDao.findPaperIdsByStudentId(testStudentId);

        // Assert that the deleted record cannot be retrieved (is null)
        assertFalse(deletedPaperIds.contains(testPaperId));
    }

    @Test
    void findPaperIdsByStudentId() {
        // Retrieve the inserted record from the database
        List<Integer> foundPaperIds = studentPapersDao.findPaperIdsByStudentId(testStudentId);

        // Assert that the retrieved record is not null
        assertNotNull(foundPaperIds);

        // Assert that the retrieved record has the expected properties
        assertTrue(foundPaperIds.contains(testPaperId));
    }

    @Test
    void findStudentIdsByPaperId() {
        // Retrieve the inserted record from the database
        List<Integer> foundStudentIds = studentPapersDao.findStudentIdsByPaperId(testPaperId);

        // Assert that the retrieved record is not null
        assertNotNull(foundStudentIds);

        // Assert that the retrieved record has the expected properties
        assertTrue(foundStudentIds.contains(testStudentId));
    }
}