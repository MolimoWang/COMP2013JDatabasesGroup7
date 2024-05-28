package com.example.DAO;

import com.example.model.Paper;
import com.example.model.Question;
import com.example.model.Subject;
import com.example.model.Answer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void setUp() {
        questionsDao = new QuestionsDaoImpl();
        papersDao = new PapersDaoImpl();
        subjectsDao = new SubjectsDaoImpl();
        answersDao = new AnswersDaoImpl();
        subject = new Subject();
        subject.setSubjectId(1);
        subject.setName("Test Subject");
        subjectsDao.insert(subject);

        paper = new Paper();
        paper.setPaperId(1);
        paper.setTitle("Test Paper Title");
        paper.setYear(2022);
        paper.setSubjectId(1);
        papersDao.insert(paper);

        answer = new Answer();
        answer.setAnswerId(1);
        answer.setText("Test Answer");
        answersDao.insert(answer);

        question = new Question();
        question.setQuestionId(1);
        question.setPaperId(1);
        question.setText("Test Question");
        question.setAnswerId(1);
        questionsDao.insert(question);
    }

    @Test
    void insert() {
        Question insertedQuestion = questionsDao.findById(1);
        assertNotNull(insertedQuestion);
        assertEquals(1, insertedQuestion.getQuestionId());
        assertEquals(1, insertedQuestion.getPaperId());
        assertEquals("Test Question", insertedQuestion.getText());
        assertEquals(1, insertedQuestion.getAnswerId());
    }

    @Test
    void deleteById() {
        questionsDao.deleteById(1);
        Question deletedQuestion = questionsDao.findById(1);
        assertNull(deletedQuestion);
    }

    @Test
    void findById() {
        Question foundQuestion = questionsDao.findById(1);
        assertNotNull(foundQuestion);
        assertEquals(1, foundQuestion.getQuestionId());
        assertEquals(1, foundQuestion.getPaperId());
        assertEquals("Test Question", foundQuestion.getText());
        assertEquals(1, foundQuestion.getAnswerId());
    }

    @Test
    void findAll() {
        List<Question> questions = questionsDao.findAll();
        assertFalse(questions.isEmpty());
        assertTrue(questions.stream().anyMatch(q -> q.getQuestionId() == 1));
    }

    @Test
    void update() {
        question.setText("Updated Question");
        questionsDao.update(question);
        Question updatedQuestion = questionsDao.findById(1);
        assertEquals("Updated Question", updatedQuestion.getText());
    }

    @AfterEach
    public void tearDown() {
        questionsDao.deleteById(1);
        papersDao.deleteById(1);
        subjectsDao.deleteById(1);
        answersDao.deleteById(1);
    }
}