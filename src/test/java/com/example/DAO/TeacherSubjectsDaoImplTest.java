package com.example.DAO;

import com.example.model.Teacher;
import com.example.model.Subject;
import com.example.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherSubjectsDaoImplTest {
    private TeacherSubjectsDaoImpl teacherSubjectsDao;
    private TeachersDaoImpl teachersDao;
    private SubjectsDaoImpl subjectsDao;
    private PersonDaoImpl personDao;

    private int testTeacherId = 1000;  // Changed to a larger number
    private int testSubjectId = 1000;  // Changed to a larger number
    private int testPersonId = 1000;  // Changed to a larger number

    @BeforeEach
    void setUp() {
        teacherSubjectsDao = new TeacherSubjectsDaoImpl();
        teachersDao = new TeachersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        personDao = new PersonDaoImpl();

        Person person = new Person();
        person.setPersonId(testPersonId);
        person.setName("Test Person");
        personDao.insert(person);

        Teacher teacher = new Teacher();
        teacher.setTeacherId(testTeacherId);
        teacher.setPersonId(testPersonId);
        teachersDao.insert(teacher);

        Subject subject = new Subject();
        subject.setSubjectId(testSubjectId);
        subjectsDao.insert(subject);

        teacherSubjectsDao.insert(testTeacherId, testSubjectId);
    }

    @AfterEach
    void tearDown() {
        teacherSubjectsDao.deleteByTeacherIdAndSubjectId(testTeacherId, testSubjectId);
        teachersDao.deleteById(testTeacherId);
        subjectsDao.deleteById(testSubjectId);
        personDao.deleteById(testPersonId);
    }

    @Test
    void insert() {
        List<Integer> insertedSubjectIds = teacherSubjectsDao.findSubjectIdsByTeacherId(testTeacherId);
        assertNotNull(insertedSubjectIds);
        assertTrue(insertedSubjectIds.contains(testSubjectId));
    }

    @Test
    void deleteByTeacherIdAndSubjectId() {
        teacherSubjectsDao.deleteByTeacherIdAndSubjectId(testTeacherId, testSubjectId);
        List<Integer> deletedSubjectIds = teacherSubjectsDao.findSubjectIdsByTeacherId(testTeacherId);
        assertFalse(deletedSubjectIds.contains(testSubjectId));
    }

    @Test
    void deleteByTeacherId() {
        teacherSubjectsDao.deleteByTeacherId(testTeacherId);
        List<Integer> deletedSubjectIds = teacherSubjectsDao.findSubjectIdsByTeacherId(testTeacherId);
        assertTrue(deletedSubjectIds.isEmpty());
    }

    @Test
    void deleteBySubjectId() {
        teacherSubjectsDao.deleteBySubjectId(testSubjectId);
        List<Integer> deletedTeacherIds = teacherSubjectsDao.findTeacherIdsBySubjectId(testSubjectId);
        assertTrue(deletedTeacherIds.isEmpty());
    }

    @Test
    void findSubjectIdsByTeacherId() {
        List<Integer> foundSubjectIds = teacherSubjectsDao.findSubjectIdsByTeacherId(testTeacherId);
        assertNotNull(foundSubjectIds);
        assertTrue(foundSubjectIds.contains(testSubjectId));
    }

    @Test
    void findTeacherIdsBySubjectId() {
        List<Integer> foundTeacherIds = teacherSubjectsDao.findTeacherIdsBySubjectId(testSubjectId);
        assertNotNull(foundTeacherIds);
        assertTrue(foundTeacherIds.contains(testTeacherId));
    }
}