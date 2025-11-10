package org.skypro.exam.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.exam.model.question.Question;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class JavaQuestionServiceTest {

    private QuestionStorage questionStorage;
    private QuestionServiceInterface questionService;

    @BeforeEach
    void setUp() {
        this.questionStorage = mock(QuestionStorage.class);
        this.questionService = new JavaQuestionService(this.questionStorage);
    }

    @Test
    void addQuestion() {
        Map<UUID, Question> questions = new HashMap<>();

        Mockito.when(this.questionStorage.getAll())
                .thenReturn(questions);

        Mockito.when(this.questionStorage.add(any()))
                .thenReturn(new Question("someQuestion", "someAnswer"));

        assertThat(this.questionService.add("someQuestion", "someAnswer"))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void getAll() {
        Question testQuestionOne = new Question("Who?", "You!");
        Question testQuestionTwo = new Question("Who?", "You!");

        Map<UUID, Question> questions = new HashMap<>();
        questions.put(testQuestionOne.getId(), testQuestionOne);
        questions.put(testQuestionTwo.getId(), testQuestionTwo);

        Mockito.when(this.questionStorage.getAll())
                .thenReturn(questions);

        assertThat(this.questionService.getAll())
                .isNotNull()
                .isInstanceOf(Collection.class)
                .satisfies(collection -> Assertions.assertEquals(2, collection.size()))
                .allSatisfy(object -> assertThat(object).isInstanceOf(Question.class));
    }

    @Test
    void getCount() {
        Mockito.when(this.questionStorage.count())
                .thenReturn(5);

        assertThat(this.questionService.count())
                .isNotNull()
                .isInstanceOf(Integer.class)
                .isEqualTo(5);
    }
}
