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

    private Question question;
    private Paper paper;
    private Subject subject;
    private Answer answer;

    @BeforeEach
    public void setUp() throws SQLException {
        questionsDao = new QuestionsDaoImpl();
        papersDao = new PapersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        answersDao = new AnswersDaoImpl();

        subject = new Subject();
        subject.setSubjectId(1000);  // Changed to a larger number
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        paper = new Paper();
        paper.setPaperId(1000);  // Changed to a larger number
        paper.setTitle("Test Paper Title");
        paper.setYear(2022);
        paper.setSubjectId(1000);  // Changed to a larger number
        papersDao.insert(paper);

        question = new Question();
        question.setQuestionId(1000);  // Changed to a larger number
        question.setPaperId(1000);  // Changed to a larger number
        question.setText("Test Question");
        questionsDao.insert(question);

        answer = new Answer();
        answer.setAnswerId(1000);  // Changed to a larger number
        answer.setText("Test Answer");
        answersDao.insert(answer, 1000);  // Provide the questionId when inserting the answer
    }

    @Test
    void insert() {
        Question insertedQuestion = questionsDao.findById(1000);  // Changed to a larger number
        assertNotNull(insertedQuestion);
        assertEquals(1000, insertedQuestion.getQuestionId());  // Changed to a larger number
        assertEquals(1000, insertedQuestion.getPaperId());  // Changed to a larger number
        assertEquals("Test Question", insertedQuestion.getText());
    }

    @Test
    void deleteById() {
        questionsDao.deleteById(1000);  // Changed to a larger number
        Question deletedQuestion = questionsDao.findById(1000);  // Changed to a larger number
        assertNull(deletedQuestion);
    }

    @Test
    void findById() {
        Question foundQuestion = questionsDao.findById(1000);  // Changed to a larger number
        assertNotNull(foundQuestion);
        assertEquals(1000, foundQuestion.getQuestionId());  // Changed to a larger number
        assertEquals(1000, foundQuestion.getPaperId());  // Changed to a larger number
        assertEquals("Test Question", foundQuestion.getText());
    }

    @Test
    void findAll() {
        List<Question> questions = questionsDao.findAll();
        assertFalse(questions.isEmpty());
        assertTrue(questions.stream().anyMatch(q -> q.getQuestionId() == 1000));  // Changed to a larger number
    }

    @AfterEach
    public void tearDown() {
        questionsDao.deleteById(1000);  // Changed to a larger number
        papersDao.deleteById(1000);  // Changed to a larger number
        subjectsDao.deleteById(1000);  // Changed to a larger number
        answersDao.deleteById(1000);  // Changed to a larger number
    }
}