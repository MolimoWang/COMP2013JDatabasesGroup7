//package com.example.DAO;
//
//import com.example.model.Answer;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AnswersDaoImplTest {
//    private AnswersDaoImpl answersDao;
//    private Answer answer;
//
//    @BeforeEach
//    public void setUp() {
//        // Initialize the AnswersDaoImpl object
//        answersDao = new AnswersDaoImpl();
//
//        // Create a new Answer object for testing
//        answer = new Answer();
//        answer.setAnswerId(1);
//        answer.setText("Test Answer");
//
//        // Initialize the database connection or set up a mock database
//    }
//
//    @Test
//    public void testInsertAndFindById() {
//        // Insert the test Answer object into the database
//        answersDao.insert(answer);
//
//        // Retrieve the inserted Answer object from the database
//        Answer found = answersDao.findById(1);
//
//        // Assert that the retrieved Answer object is not null
//        assertNotNull(found);
//
//        // Assert that the retrieved Answer object has the expected properties
//        assertEquals(1, found.getAnswerId());
//        assertEquals("Test Answer", found.getText());
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Insert the test Answer object into the database
//        answersDao.insert(answer);
//
//        // Delete the inserted Answer object from the database
//        answersDao.deleteById(1);
//
//        // Try to retrieve the deleted Answer object from the database
//        Answer found = answersDao.findById(1);
//
//        // Assert that the deleted Answer object cannot be retrieved (is null)
//        assertNull(found);
//    }
//
//    @Test
//    public void testUpdate() {
//        // Insert the test Answer object into the database
//        answersDao.insert(answer);
//
//        // Update the properties of the test Answer object
//        answer.setText("Updated Answer");
//
//        // Update the Answer object in the database
//        answersDao.update(answer);
//
//        // Retrieve the updated Answer object from the database
//        Answer found = answersDao.findById(1);
//
//        // Assert that the retrieved Answer object has the updated properties
//        assertNotNull(found);
//        assertEquals("Updated Answer", found.getText());
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Clean up the test data by deleting the test Answer object from the database
//        answersDao.deleteById(1);
//    }
//}