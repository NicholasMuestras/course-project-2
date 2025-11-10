package org.skypro.exam.service;

import org.skypro.exam.exception.RequestedTooLessException;
import org.skypro.exam.exception.RequestedTooMuchException;
import org.skypro.exam.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class ExaminerService implements ExaminerServiceInterface {

    private final QuestionServiceInterface questionService;

    public ExaminerService(QuestionServiceInterface questionService) {
        this.questionService = questionService;
    }

    public Collection<Question> getRandomQuestions(int amount) {
        if (amount < 1) {
            throw new RequestedTooLessException(amount);
        }

        Integer questionsCountAvailable = this.questionService.count();

        if (amount > questionsCountAvailable) {
            throw new RequestedTooMuchException(questionsCountAvailable, amount);
        }

        Collection<Question> questions = this.questionService.getAll();
        List<Question> questionsShuffled = new ArrayList<>(questions.stream().toList());
        Collections.shuffle(questionsShuffled);

        return questionsShuffled.subList(0, amount);
    }
}
