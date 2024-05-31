package com.example.DAO;

import com.example.model.Answer;
import com.example.model.Question;
import com.example.model.Paper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AnswersDaoImplTest {
    private AnswersDaoImpl answersDao;
    private QuestionsDaoImpl questionsDao;
    private PapersDaoImpl papersDao;
    private Answer answer;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize the AnswersDaoImpl object
        answersDao = new AnswersDaoImpl();

        // Create a new Answer object for testing
        answer = new Answer();
        answer.setAnswerId(1000);  // Changed to a larger number
        answer.setText("Test Answer");

        // Initialize the database connection or set up a mock database

        // Insert a test paper into the database
        papersDao = new PapersDaoImpl();
        Paper paper = new Paper();
        paper.setPaperId(1000);  // Changed to a larger number
        papersDao.insert(paper);

        // Insert a test question into the database
        questionsDao = new QuestionsDaoImpl();
        Question question = new Question();
        question.setQuestionId(1000);  // Changed to a larger number
        question.setPaperId(1000);  // Set the PaperID to the ID of the test paper
        questionsDao.insert(question);
    }

    @Test
    public void testInsertAndFindById() throws SQLException {
        // Insert the test Answer object into the database
        answersDao.insert(answer, 1000);  // Changed to a larger number

        // Retrieve the inserted Answer object from the database
        Answer found = answersDao.findById(1000);  // Changed to a larger number

        // Assert that the retrieved Answer object is not null
        assertNotNull(found);

        // Assert that the retrieved Answer object has the expected properties
        assertEquals(1000, found.getAnswerId());  // Changed to a larger number
        assertEquals("Test Answer", found.getText());
    }

    @Test
    public void testDeleteById() throws SQLException {
        // Insert the test Answer object into the database
        answersDao.insert(answer, 1000);  // Changed to a larger number

        // Delete the inserted Answer object from the database
        answersDao.deleteById(1000);  // Changed to a larger number

        // Try to retrieve the deleted Answer object from the database
        Answer found = answersDao.findById(1000);  // Changed to a larger number

        // Assert that the deleted Answer object cannot be retrieved (is null)
        assertNull(found);
    }

    @Test
    public void testUpdate() throws SQLException {
        // Insert the test Answer object into the database
        answersDao.insert(answer, 1000);  // Changed to a larger number

        // Update the properties of the test Answer object
        answer.setText("Updated Answer");

        // Update the Answer object in the database
        answersDao.update(answer);

        // Retrieve the updated Answer object from the database
        Answer found = answersDao.findById(1000);  // Changed to a larger number

        // Assert that the retrieved Answer object has the updated properties
        assertNotNull(found);
        assertEquals("Updated Answer", found.getText());
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Clean up the test data by deleting the test Answer object from the database
        answersDao.deleteById(1000);  // Changed to a larger number
        questionsDao.deleteById(1000);  // Also delete the test Question
        papersDao.deleteById(1000);  // Also delete the test Paper
    }
}