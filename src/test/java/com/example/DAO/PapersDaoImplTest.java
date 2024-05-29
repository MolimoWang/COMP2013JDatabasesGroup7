package com.example.DAO;

import com.example.model.Paper;
import com.example.model.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PapersDaoImplTest {
    private PapersDaoImpl papersDao;
    private SubjectsDaoImpl subjectsDao;
    private Paper paper;
    private Subject subject;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize the PapersDaoImpl and SubjectsDaoImpl objects
        papersDao = new PapersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();

        // Create a new Subject object for testing
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");

        // Insert the test Subject object into the database
        // This is necessary because the Paper object has a foreign key constraint on the SubjectID
        subjectsDao.insert(subject);

        // Create a new Paper object for testing
        paper = new Paper();
        paper.setPaperId(1);
        paper.setTitle("Test Paper Title");
        paper.setYear(2022);
        paper.setSubjectId(1);  // This should match the SubjectID of the inserted Subject object
        // Insert the test Paper object into the database
        papersDao.insert(paper);
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test data by deleting the test Paper and Subject objects from the database
        papersDao.deleteById(1);
        subjectsDao.deleteById(1);
        subjectsDao.deleteById(2);  // This is necessary if the testUpdate method is run
    }

    @Test
    public void testInsertAndFindById() {
        // Retrieve the inserted Paper object from the database
        Paper found = papersDao.findById(1);

        // Assert that the retrieved Paper object is not null
        assertNotNull(found);

        // Assert that the retrieved Paper object has the expected properties
        assertEquals(1, found.getPaperId());
        assertEquals("Test Paper Title", found.getTitle());
        assertEquals(2022, found.getYear());
        assertEquals(1, found.getSubjectId());
    }

    @Test
    public void testDeleteById() {
        // Delete the inserted Paper object from the database
        papersDao.deleteById(1);

        // Try to retrieve the deleted Paper object from the database
        Paper found = papersDao.findById(1);

        // Assert that the deleted Paper object cannot be retrieved (is null)
        assertNull(found);
    }

    @Test
    public void testUpdate() {
        // Update the properties of the test Paper object
        paper.setTitle("Updated Paper Title");
        paper.setYear(2023);
        paper.setSubjectId(2);

        // Create a new Subject object and insert it into the database
        // This is necessary because the updated Paper object has a different SubjectID
        subjectsDao.insert(new Subject(2, "New Subject"));

        // Update the Paper object in the database
        papersDao.update(paper);

        // Retrieve the updated Paper object from the database
        Paper found = papersDao.findById(1);

        // Assert that the retrieved Paper object has the updated properties
        assertNotNull(found);
        assertEquals("Updated Paper Title", found.getTitle());
        assertEquals(2023, found.getYear());
        assertEquals(2, found.getSubjectId());
    }
}