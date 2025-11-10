package org.skypro.exam.controller;

import org.skypro.exam.model.question.Question;
import org.skypro.exam.service.ExaminerServiceInterface;
import org.skypro.exam.service.QuestionServiceInterface;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
public class ExamController {
    private ExaminerServiceInterface examinerService;

    public ExamController(ExaminerServiceInterface examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/exam/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return this.examinerService.getRandomQuestions(amount);
    }
}
