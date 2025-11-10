package org.skypro.exam.service;

import org.skypro.exam.model.question.Question;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class QuestionStorage {
    private final Map<UUID, Question> questions = new HashMap<>();

    public Map<UUID, Question> getAll() {
        return this.questions;
    }

    public Question add(Question question) {
        this.questions.put(question.getId(), question);

        return question;
    }

    public UUID remove(UUID id) {
        this.questions.remove(id);

        return id;
    }

    public Integer count() {
        return this.questions.size();
    }
}
