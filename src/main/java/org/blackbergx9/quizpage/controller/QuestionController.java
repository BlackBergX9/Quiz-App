package org.blackbergx9.quizpage.controller;

import org.blackbergx9.quizpage.model.Question;
import org.blackbergx9.quizpage.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
//        return "Hello Dunyaa!!";
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getAllQuestionsByCategory(category);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

}