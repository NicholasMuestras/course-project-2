package org.skypro.exam.model.question;

import java.util.UUID;

public class Question {
    private final UUID id;
    private final String question;
    private final String answer;

    public Question(String question, String answer) {
        this.id = UUID.randomUUID();
        this.question = question;
        this.answer = answer;
    }

    public UUID getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
