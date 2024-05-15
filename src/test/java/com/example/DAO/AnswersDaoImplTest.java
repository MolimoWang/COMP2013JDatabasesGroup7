package com.example.DAO;

import com.example.model.Answer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnswersDaoImplTest {
    private AnswersDaoImpl answersDao;
    private Answer answer;

    @BeforeEach
    public void setUp() {
        answersDao = new AnswersDaoImpl();
        answer = new Answer();
        answer.setAnswerId(1);
        answer.setText("Test Answer");
        // 初始化数据库连接，或者设置模拟数据库
    }

    @Test
    public void testInsertAndFindById() {
        answersDao.insert(answer);

        Answer found = answersDao.findById(1);
        assertNotNull(found);
        assertEquals(1, found.getAnswerId());
        assertEquals("Test Answer", found.getText());
    }

    @Test
    public void testDeleteById() {
        answersDao.insert(answer);
        answersDao.deleteById(1);

        Answer found = answersDao.findById(1);
        assertNull(found);
    }

    @Test
    public void testUpdate() {
        answersDao.insert(answer);
        answer.setText("Updated Answer");
        answersDao.update(answer);

        Answer found = answersDao.findById(1);
        assertNotNull(found);
        assertEquals("Updated Answer", found.getText());
    }

    // 其他测试方法...

    @AfterEach
    public void tearDown() {
        // 清理测试数据
        answersDao.deleteById(1);
    }
}