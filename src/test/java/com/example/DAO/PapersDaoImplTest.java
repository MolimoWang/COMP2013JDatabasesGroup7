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
        subject.setSubjectId(1000);  // Changed to a larger number
        subject.setName("Test Subject");

        // Insert the test Subject object into the database
        subjectsDao.insert(subject);

        // Create a new Paper object for testing
        paper = new Paper();
        paper.setPaperId(1000);  // Changed to a larger number
        paper.setTitle("Test Paper Title");
        paper.setYear(2022);
        paper.setSubjectId(1000);  // This should match the SubjectID of the inserted Subject object

        // Insert the test Paper object into the database
        papersDao.insert(paper);
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test data by deleting the test Paper and Subject objects from the database
        papersDao.deleteById(1000);  // Changed to a larger number
        subjectsDao.deleteById(1000);  // Changed to a larger number
        subjectsDao.deleteById(2000);  // Changed to a larger number
    }

    @Test
    public void testInsertAndFindById() {
        // Retrieve the inserted Paper object from the database
        Paper found = papersDao.findById(1000);  // Changed to a larger number

        // Assert that the retrieved Paper object is not null
        assertNotNull(found);

        // Assert that the retrieved Paper object has the expected properties
        assertEquals(1000, found.getPaperId());  // Changed to a larger number
        assertEquals("Test Paper Title", found.getTitle());
        assertEquals(2022, found.getYear());
        assertEquals(1000, found.getSubjectId());  // Changed to a larger number
    }

    @Test
    public void testDeleteById() {
        // Delete the inserted Paper object from the database
        papersDao.deleteById(1000);  // Changed to a larger number

        // Try to retrieve the deleted Paper object from the database
        Paper found = papersDao.findById(1000);  // Changed to a larger number

        // Assert that the deleted Paper object cannot be retrieved (is null)
        assertNull(found);
    }

    @Test
    public void testUpdate() {
        // Update the properties of the test Paper object
        paper.setTitle("Updated Paper Title");
        paper.setYear(2023);
        paper.setSubjectId(2000);  // Changed to a larger number

        // Create a new Subject object and insert it into the database
        subjectsDao.insert(new Subject(2000, "New Subject"));  // Changed to a larger number

        // Update the Paper object in the database
        papersDao.update(paper);

        // Retrieve the updated Paper object from the database
        Paper found = papersDao.findById(1000);  // Changed to a larger number

        // Assert that the retrieved Paper object has the updated properties
        assertNotNull(found);
        assertEquals("Updated Paper Title", found.getTitle());
        assertEquals(2023, found.getYear());
        assertEquals(2000, found.getSubjectId());  // Changed to a larger number
    }
}