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

    private int testTeacherId = 1;
    private int testSubjectId = 1;
    private int testPersonId = 1;

    @BeforeEach
    void setUp() {
        teacherSubjectsDao = new TeacherSubjectsDaoImpl();
        teachersDao = new TeachersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        personDao = new PersonDaoImpl();

        // Insert a test person into the database
        Person person = new Person();
        person.setPersonId(testPersonId);
        person.setName("Test Person");
        personDao.insert(person);

        // Insert a test teacher into the database
        Teacher teacher = new Teacher();
        teacher.setTeacherId(testTeacherId);
        teacher.setPersonId(testPersonId);
        teachersDao.insert(teacher);

        // Insert a test subject into the database
        Subject subject = new Subject();
        subject.setSubjectId(testSubjectId);
        subjectsDao.insert(subject);

        // Insert a test record into the database
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
        // Retrieve the inserted record from the database
        List<Integer> insertedSubjectIds = teacherSubjectsDao.findSubjectIdsByTeacherId(testTeacherId);

        // Assert that the retrieved record is not null
        assertNotNull(insertedSubjectIds);

        // Assert that the retrieved record has the expected properties
        assertTrue(insertedSubjectIds.contains(testSubjectId));
    }

    @Test
    void deleteByTeacherIdAndSubjectId() {
        // Delete the inserted record from the database
        teacherSubjectsDao.deleteByTeacherIdAndSubjectId(testTeacherId, testSubjectId);

        // Try to retrieve the deleted record from the database
        List<Integer> deletedSubjectIds = teacherSubjectsDao.findSubjectIdsByTeacherId(testTeacherId);

        // Assert that the deleted record cannot be retrieved (is null)
        assertFalse(deletedSubjectIds.contains(testSubjectId));
    }

    @Test
    void findSubjectIdsByTeacherId() {
        // Retrieve the inserted record from the database
        List<Integer> foundSubjectIds = teacherSubjectsDao.findSubjectIdsByTeacherId(testTeacherId);

        // Assert that the retrieved record is not null
        assertNotNull(foundSubjectIds);

        // Assert that the retrieved record has the expected properties
        assertTrue(foundSubjectIds.contains(testSubjectId));
    }

    @Test
    void findTeacherIdsBySubjectId() {
        // Retrieve the inserted record from the database
        List<Integer> foundTeacherIds = teacherSubjectsDao.findTeacherIdsBySubjectId(testSubjectId);

        // Assert that the retrieved record is not null
        assertNotNull(foundTeacherIds);

        // Assert that the retrieved record has the expected properties
        assertTrue(foundTeacherIds.contains(testTeacherId));
    }
}