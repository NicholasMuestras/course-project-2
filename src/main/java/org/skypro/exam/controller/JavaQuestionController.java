package org.skypro.exam.controller;

import org.skypro.exam.model.question.Question;
import org.skypro.exam.service.JavaQuestionService;
import org.skypro.exam.service.QuestionServiceInterface;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class JavaQuestionController {

    private QuestionServiceInterface questionService;

    public JavaQuestionController(JavaQuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/exam/java/add")
    public Question addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return this.questionService.add(question, answer);
    }

    @GetMapping("/exam/java/remove")
    public void removeQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        this.questionService.remove(question, answer);
    }

    @GetMapping("/exam/java/find")
    public void find() {

    }

    @GetMapping("/exam/java")
    public Collection<Question> getQuestions() {
        return this.questionService.getAll();
    }
}
