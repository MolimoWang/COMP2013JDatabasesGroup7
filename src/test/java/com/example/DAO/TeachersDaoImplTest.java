package com.example.DAO;

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
    private Teacher teacher;
    private Subject subject;

    @BeforeEach
    public void setUp() {
        teachersDao = new TeachersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        teacher = new Teacher();
        teacher.setTeacherId(1);
        teacher.setName("Test Teacher");
        teacher.setSubjectId(1);
        teachersDao.insert(teacher);
    }

    @AfterEach
    public void tearDown() {
        teachersDao.deleteById(1);
        subjectsDao.deleteById(1);
    }

    @Test
    void insert() {
        Teacher insertedTeacher = teachersDao.findById(1);
        assertNotNull(insertedTeacher);
        assertEquals(1, insertedTeacher.getTeacherId());
        assertEquals("Test Teacher", insertedTeacher.getName());
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
        assertEquals("Test Teacher", foundTeacher.getName());
        assertEquals(1, foundTeacher.getSubjectId());
    }

    @Test
    void findAll() {
        List<Teacher> teachers = teachersDao.findAll();
        assertFalse(teachers.isEmpty());
        assertTrue(teachers.stream().anyMatch(t -> t.getTeacherId() == 1));
    }
}