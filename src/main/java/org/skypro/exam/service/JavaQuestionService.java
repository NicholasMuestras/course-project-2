package org.skypro.exam.service;

import org.skypro.exam.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionServiceInterface {

    private final QuestionStorage questionStorage;

    public JavaQuestionService(QuestionStorage questionStorage) {
        this.questionStorage = questionStorage;
        this.generateSomeQuestionsOnInit();
    }

    private void generateSomeQuestionsOnInit() {
        int counter = 0;

        while (counter <= 10) {
            this.questionStorage.add(this.getRandomQuestion());
            counter++;
        }
    }

    public Collection<Question> getAll() {
        return this.questionStorage.getAll().values();
    }

    public Question getRandomQuestion() {
        Random random = new Random();
        int index = random.nextInt(100);

        return new Question("question-" + index, "answer-" + index);
    }

    public Question add(String question, String answer) {
        if (this.find(question, answer).isPresent()) {
            throw new RuntimeException("Unable to add this item. It's already exists.");
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
