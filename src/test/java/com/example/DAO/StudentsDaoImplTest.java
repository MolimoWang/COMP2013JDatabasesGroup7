package com.example.DAO;

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
    private Student student;
    private Paper paper;
    private Subject subject;

    @BeforeEach
    public void setUp() throws SQLException {
        studentsDao = new StudentsDaoImpl();
        papersDao = new PapersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        paper = new Paper();
        paper.setPaperId(1);
        paper.setTitle("Test Paper Title");
        paper.setYear(2022);
        paper.setSubjectId(1);
        papersDao.insert(paper);

        student = new Student();
        student.setStudentId(1);
        student.setName("Test Student");
        student.setPaperId(1);
        studentsDao.insert(student);
    }

    @AfterEach
    public void tearDown() {
        studentsDao.deleteById(1);
        papersDao.deleteById(1);
        subjectsDao.deleteById(1);
    }

    @Test
    void insert() {
        Student insertedStudent = studentsDao.findById(1);
        assertNotNull(insertedStudent);
        assertEquals(1, insertedStudent.getStudentId());
        assertEquals("Test Student", insertedStudent.getName());
        assertEquals(1, insertedStudent.getPaperId());
    }

    @Test
    void deleteById() {
        studentsDao.deleteById(1);
        Student deletedStudent = studentsDao.findById(1);
        assertNull(deletedStudent);
    }

    @Test
    void findById() {
        Student foundStudent = studentsDao.findById(1);
        assertNotNull(foundStudent);
        assertEquals(1, foundStudent.getStudentId());
        assertEquals("Test Student", foundStudent.getName());
        assertEquals(1, foundStudent.getPaperId());
    }

    @Test
    void findAll() {
        List<Student> students = studentsDao.findAll();
        assertFalse(students.isEmpty());
        assertTrue(students.stream().anyMatch(s -> s.getStudentId() == 1));
    }
}