package com.example.DAO;

import com.example.model.Paper;
import com.example.model.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PapersDaoImplTest {
    private PapersDaoImpl papersDao;
    private SubjectsDaoImpl subjectsDao;
    private Paper paper;
    private Subject subject;

    @BeforeEach
    public void setUp() {
        papersDao = new PapersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);  // Insert a subject record before inserting a paper record

        paper = new Paper();
        paper.setPaperId(1);
        paper.setTitle("Test Paper Title");
        paper.setYear(2022);
        paper.setSubjectId(1);  // This should match the subjectId of the inserted subject record
    }

    @AfterEach
    public void tearDown() {
        papersDao.deleteById(1);
        subjectsDao.deleteById(1);
        subjectsDao.deleteById(2);
    }

    @Test
    public void testInsertAndFindById() {
        papersDao.insert(paper);

        Paper found = papersDao.findById(1);
        assertNotNull(found);
        assertEquals(1, found.getPaperId());
        assertEquals("Test Paper Title", found.getTitle());
        assertEquals(2022, found.getYear());
        assertEquals(1, found.getSubjectId());
    }

    @Test
    public void testDeleteById() {
        papersDao.insert(paper);
        papersDao.deleteById(1);

        Paper found = papersDao.findById(1);
        assertNull(found);
    }

    @Test
    public void testUpdate() {
        papersDao.insert(paper);
        paper.setTitle("Updated Paper Title");
        paper.setYear(2023);
        paper.setSubjectId(2);
        subjectsDao.insert(new Subject(2, "New Subject"));  // Insert a new subject record before updating the paper record
        papersDao.update(paper);

        Paper found = papersDao.findById(1);
        assertNotNull(found);
        assertEquals("Updated Paper Title", found.getTitle());
        assertEquals(2023, found.getYear());
        assertEquals(2, found.getSubjectId());
    }
}