package com.example.DAO;

import com.example.model.Paper;
import com.example.model.Question;
import com.example.model.Subject;
import com.example.model.Answer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionsDaoImplTest {
    private QuestionsDaoImpl questionsDao;
    private PapersDaoImpl papersDao;
    private SubjectsDaoImpl subjectsDao;
    private AnswersDaoImpl answersDao;
    private QuestionAnswersDaoImpl questionAnswersDao;  // Add this line

    private Question question;
    private Paper paper;
    private Subject subject;
    private Answer answer;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize the DAO objects
        questionsDao = new QuestionsDaoImpl();
        papersDao = new PapersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        answersDao = new AnswersDaoImpl();
        questionAnswersDao = new QuestionAnswersDaoImpl();  // Add this line

        // Create and insert a test Subject object into the database
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        // Create and insert a test Paper object into the database
        paper = new Paper();
        paper.setPaperId(1);
        paper.setTitle("Test Paper Title");
        paper.setYear(2022);
        paper.setSubjectId(1);  // This should match the SubjectID of the inserted Subject object
        papersDao.insert(paper);

        // Create and insert a test Answer object into the database
        answer = new Answer();
        answer.setAnswerId(1);
        answer.setText("Test Answer");
        answersDao.insert(answer);

        // Create and insert a test Question object into the database
        question = new Question();
        question.setQuestionId(1);
        question.setPaperId(1);  // This should match the PaperID of the inserted Paper object
        question.setText("Test Question");
        question.setAnswerId(1);  // This should match the AnswerID of the inserted Answer object
        questionsDao.insert(question);

        // Insert the Question-Answer relationship into the database
        questionAnswersDao.insert(1, 1);  // Add this line
    }

    @Test
    void insert() {
        // Retrieve the inserted Question object from the database
        Question insertedQuestion = questionsDao.findById(1);

        // Assert that the retrieved Question object is not null
        assertNotNull(insertedQuestion);

        // Assert that the retrieved Question object has the expected properties
        assertEquals(1, insertedQuestion.getQuestionId());
        assertEquals(1, insertedQuestion.getPaperId());
        assertEquals("Test Question", insertedQuestion.getText());

        // Retrieve the associated Answer IDs from the database
        List<Integer> answerIds = questionAnswersDao.findAnswerIdsByQuestionId(1);

        // Assert that the retrieved Answer IDs include the expected Answer ID
        assertTrue(answerIds.contains(1));
    }

    @Test
    void deleteById() {
        // Delete the inserted Question object from the database
        questionsDao.deleteById(1);

        // Try to retrieve the deleted Question object from the database
        Question deletedQuestion = questionsDao.findById(1);

        // Assert that the deleted Question object cannot be retrieved (is null)
        assertNull(deletedQuestion);
    }

    @Test
    void findById() {
        // Retrieve the inserted Question object from the database
        Question foundQuestion = questionsDao.findById(1);

        // Assert that the retrieved Question object is not null
        assertNotNull(foundQuestion);

        // Assert that the retrieved Question object has the expected properties
        assertEquals(1, foundQuestion.getQuestionId());
        assertEquals(1, foundQuestion.getPaperId());
        assertEquals("Test Question", foundQuestion.getText());

        // Retrieve the associated Answer IDs from the database
        List<Integer> answerIds = questionAnswersDao.findAnswerIdsByQuestionId(1);

        // Assert that the retrieved Answer IDs include the expected Answer ID
        assertTrue(answerIds.contains(1));
    }

    @Test
    void findAll() {
        // Retrieve all Question objects from the database
        List<Question> questions = questionsDao.findAll();

        // Assert that the retrieved list of Question objects is not empty
        assertFalse(questions.isEmpty());

        // Assert that the retrieved list of Question objects contains the inserted Question object
        assertTrue(questions.stream().anyMatch(q -> q.getQuestionId() == 1));
    }

    @Test
    void update() {
        // Update the properties of the test Question object
        question.setText("Updated Question");

        // Update the Question object in the database
        questionsDao.update(question);

        // Retrieve the updated Question object from the database
        Question updatedQuestion = questionsDao.findById(1);

        // Assert that the retrieved Question object has the updated properties
        assertEquals("Updated Question", updatedQuestion.getText());
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test data by deleting the test Question, Paper, Subject, and Answer objects from the database
        questionAnswersDao.deleteByQuestionIdAndAnswerId(1, 1);  // Add this line
        questionsDao.deleteById(1);
        papersDao.deleteById(1);
        subjectsDao.deleteById(1);
        answersDao.deleteById(1);
    }
}