package org.skypro.exam.service;

import org.skypro.exam.model.question.Question;

import java.util.Collection;

public interface ExaminerServiceInterface {

    Collection<Question> getRandomQuestions(int amount);
}
