package com.example.DAO;

import com.example.model.Paper;
import com.example.model.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.model.Question;
import com.example.model.Answer;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionAnswersDaoImplTest {
    private QuestionAnswersDaoImpl questionAnswersDao;
    private QuestionsDaoImpl questionsDao;
    private AnswersDaoImpl answersDao;
    private PapersDaoImpl papersDao;
    private SubjectsDaoImpl subjectsDao;

    private int testQuestionId = 1;
    private int testAnswerId = 1;
    private int testPaperId = 1;
    private int testSubjectId = 1;

    @BeforeEach
    void setUp() throws SQLException {
        questionAnswersDao = new QuestionAnswersDaoImpl();
        questionsDao = new QuestionsDaoImpl();
        answersDao = new AnswersDaoImpl();
        papersDao = new PapersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();

        // Insert a test subject into the database
        Subject subject = new Subject();
        subject.setSubjectId(testSubjectId);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        // Insert a test paper into the database
        Paper paper = new Paper();
        paper.setPaperId(testPaperId);
        paper.setTitle("Test Paper");
        paper.setSubjectId(testSubjectId);
        papersDao.insert(paper);

        // Insert a test question into the database
        Question question = new Question();
        question.setQuestionId(testQuestionId);
        question.setPaperId(testPaperId);
        question.setText("Test Question");
        questionsDao.insert(question);

        // Insert a test answer into the database
        Answer answer = new Answer();
        answer.setAnswerId(testAnswerId);
        answer.setText("Test Answer");
        answersDao.insert(answer);

        // Insert a test record into the database
        questionAnswersDao.insert(testQuestionId, testAnswerId);
    }

    @AfterEach
    void tearDown() {
        questionAnswersDao.deleteByQuestionIdAndAnswerId(testQuestionId, testAnswerId);
        questionsDao.deleteById(testQuestionId);
        answersDao.deleteById(testAnswerId);
        papersDao.deleteById(testPaperId);
        subjectsDao.deleteById(testSubjectId);
    }


    @Test
    void insert() {
        // Retrieve the inserted record from the database
        List<Integer> insertedAnswerIds = questionAnswersDao.findAnswerIdsByQuestionId(testQuestionId);

        // Assert that the retrieved record is not null
        assertNotNull(insertedAnswerIds);

        // Assert that the retrieved record has the expected properties
        assertTrue(insertedAnswerIds.contains(testAnswerId));
    }

    @Test
    void deleteByQuestionIdAndAnswerId() {
        // Delete the inserted record from the database
        questionAnswersDao.deleteByQuestionIdAndAnswerId(testQuestionId, testAnswerId);

        // Try to retrieve the deleted record from the database
        List<Integer> deletedAnswerIds = questionAnswersDao.findAnswerIdsByQuestionId(testQuestionId);

        // Assert that the deleted record cannot be retrieved (is null)
        assertFalse(deletedAnswerIds.contains(testAnswerId));
    }

    @Test
    void findAnswerIdsByQuestionId() {
        // Retrieve the inserted record from the database
        List<Integer> foundAnswerIds = questionAnswersDao.findAnswerIdsByQuestionId(testQuestionId);

        // Assert that the retrieved record is not null
        assertNotNull(foundAnswerIds);

        // Assert that the retrieved record has the expected properties
        assertTrue(foundAnswerIds.contains(testAnswerId));
    }

    @Test
    void findQuestionIdsByAnswerId() {
        // Retrieve the inserted record from the database
        List<Integer> foundQuestionIds = questionAnswersDao.findQuestionIdsByAnswerId(testAnswerId);

        // Assert that the retrieved record is not null
        assertNotNull(foundQuestionIds);

        // Assert that the retrieved record has the expected properties
        assertTrue(foundQuestionIds.contains(testQuestionId));
    }
}