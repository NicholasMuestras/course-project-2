package org.skypro.exam.service;

import org.skypro.exam.exception.UniqueItemRestrictionException;
import org.skypro.exam.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionServiceInterface {

    private final QuestionStorage questionStorage;
    private final Random randomGenerator = new Random();

    public JavaQuestionService(QuestionStorage questionStorage) {
        this.questionStorage = questionStorage;
        this.generateSomeQuestionsOnInit();
    }

    private void generateSomeQuestionsOnInit() {
        for (int i = 0; i <= 10; i++) {
            this.questionStorage.add(this.getRandomQuestion());
        }
    }

    public Collection<Question> getAll() {
        return List.copyOf(this.questionStorage.getAll().values());
    }

    private Question getRandomQuestion() {
        int index = this.randomGenerator.nextInt(100);

        return new Question("question-" + index, "answer-" + index);
    }

    public Question add(String question, String answer) {
        if (this.find(question, answer).isPresent()) {
            throw new UniqueItemRestrictionException(
                    "Unable to add this Question entity. It's already exists with"
                            + " Question: " + question
                            + " Answer: " + answer
            );
        }

        return this.questionStorage.add(new Question(question, answer));
    }

    public void remove(String question, String answer) {
        Optional<Question> object = this.find(question, answer);
        object.ifPresent(o -> this.questionStorage.remove(o.getId()));
    }

    public Optional<Question> find(String question, String answer) {
        return this.getAll()
                .stream()
                .filter(o -> o.getQuestion().equals(question) && o.getAnswer().equals(answer))
                .findFirst();
    }

    public Integer count() {
        return this.questionStorage.count();
    }
}
