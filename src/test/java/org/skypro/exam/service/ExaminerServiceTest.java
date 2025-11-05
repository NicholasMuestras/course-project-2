package org.skypro.exam.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.exam.model.question.Question;

import java.util.Collection;
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
                .isInstanceOf(Collection.class)
                .satisfies(collection -> Assertions.assertEquals(2, collection.size()))
                .allSatisfy(object -> assertThat(object).isInstanceOf(Question.class));
    }

    @Test
    void getTooManyRandomQuestions() {
        Assertions.assertThrows(RuntimeException.class, () -> this.examinerService.getRandomQuestions(100500));
    }

    @Test
    void getTooLessRandomQuestions() {
        Assertions.assertThrows(RuntimeException.class, () -> this.examinerService.getRandomQuestions(0));
    }
}
