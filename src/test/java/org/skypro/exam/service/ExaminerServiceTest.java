package org.skypro.exam.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.exam.exception.RequestedTooLessException;
import org.skypro.exam.exception.RequestedTooMuchException;
import org.skypro.exam.model.question.Question;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


public class ExaminerServiceTest {

    private ExaminerServiceInterface examinerService;
    private QuestionServiceInterface questionService;

    @BeforeEach
    void setUp() {
        this.questionService = mock(QuestionServiceInterface.class);
        this.examinerService = new ExaminerService(this.questionService);

        Mockito.when(this.questionService.count())
                .thenReturn(2);

        Mockito.when(this.questionService.getAll())
                .thenReturn(
                        Stream.of(
                                new Question("Who?", "You!"),
                                new Question("When?", "Now!")
                        ).collect(Collectors.toUnmodifiableSet())
                );
    }

    @Test
    void getSomeRandomQuestions() {
        assertThat(this.examinerService.getRandomQuestions(2))
                .isNotNull()
                .satisfies(collection -> assertThat(collection).hasSize(2))
                .allSatisfy(object -> assertThat(object).isNotNull().hasNoNullFieldsOrProperties());
    }

    @Test
    void getTooManyRandomQuestions() {
        Assertions.assertThrows(RequestedTooMuchException.class, () -> this.examinerService.getRandomQuestions(100500));
    }

    @Test
    void getTooLessRandomQuestions() {
        Assertions.assertThrows(RequestedTooLessException.class, () -> this.examinerService.getRandomQuestions(0));
    }
}
