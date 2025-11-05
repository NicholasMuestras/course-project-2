package org.skypro.exam.service;

import org.skypro.exam.model.question.Question;

import java.util.Collection;
import java.util.Optional;

public interface QuestionServiceInterface {

    Question add(String question, String answer);

    void remove(String question, String answer);

    Optional<Question> find(String Question, String Answer);

    Integer count();

    Collection<Question> getAll();
}
