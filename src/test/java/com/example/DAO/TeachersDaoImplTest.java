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
    private Person person;

    @BeforeEach
    public void setUp() {
        teachersDao = new TeachersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        personDao = new PersonDaoImpl();
        teacherSubjectsDao = new TeacherSubjectsDaoImpl();

        subject = new Subject();
        subject.setSubjectId(1000);  // Changed to a larger number
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        person = new Person();
        person.setPersonId(1000);  // Changed to a larger number
        person.setName("Test Person");
        personDao.insert(person);

        teacher = new Teacher();
        teacher.setTeacherId(1000);  // Changed to a larger number
        teacher.setPersonId(1000);  // Changed to a larger number
        teachersDao.insert(teacher);

        teacherSubjectsDao.insert(1000, 1000);  // Changed to a larger number
    }

    @AfterEach
    public void tearDown() {
        teacherSubjectsDao.deleteByTeacherIdAndSubjectId(1000, 1000);  // Changed to a larger number
        teachersDao.deleteById(1000);  // Changed to a larger number
        subjectsDao.deleteById(1000);  // Changed to a larger number
        personDao.deleteById(1000);  // Changed to a larger number
    }

    @Test
    void insert() {
        Teacher insertedTeacher = teachersDao.findById(1000);  // Changed to a larger number
        assertNotNull(insertedTeacher);
        assertEquals(1000, insertedTeacher.getTeacherId());  // Changed to a larger number
        assertEquals(1000, insertedTeacher.getPersonId());  // Changed to a larger number

        List<Integer> subjectIds = teacherSubjectsDao.findSubjectIdsByTeacherId(1000);  // Changed to a larger number
        assertTrue(subjectIds.contains(1000));  // Changed to a larger number
    }

    @Test
    void deleteById() {
        teachersDao.deleteById(1000);  // Changed to a larger number
        Teacher deletedTeacher = teachersDao.findById(1000);  // Changed to a larger number
        assertNull(deletedTeacher);
    }

    @Test
    void findById() {
        Teacher foundTeacher = teachersDao.findById(1000);  // Changed to a larger number
        assertNotNull(foundTeacher);
        assertEquals(1000, foundTeacher.getTeacherId());  // Changed to a larger number
        assertEquals(1000, foundTeacher.getPersonId());  // Changed to a larger number

        Person associatedPerson = personDao.findById(foundTeacher.getPersonId());
        Subject associatedSubject = subjectsDao.findById(1000);  // Changed to a larger number

        assertEquals("Test Person", associatedPerson.getName());
        assertEquals("Test Subject", associatedSubject.getName());
    }

    @Test
    void findAll() {
        List<Teacher> teachers = teachersDao.findAll();
        assertFalse(teachers.isEmpty());
        assertTrue(teachers.stream().anyMatch(t -> t.getTeacherId() == 1000));  // Changed to a larger number
    }
}