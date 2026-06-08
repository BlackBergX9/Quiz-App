package org.blackbergx9.quizpage.controller;

import org.blackbergx9.quizpage.model.Question;
import org.blackbergx9.quizpage.model.QuestionWrapper;
import org.blackbergx9.quizpage.model.Quiz;
import org.blackbergx9.quizpage.model.UserResponse;
import org.blackbergx9.quizpage.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(
        @RequestParam String category,
        @RequestParam int numQ,
        @RequestParam String title
    ) {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer quizId)
    {
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(
        @PathVariable("id") Integer quizId,
        @RequestBody List<UserResponse> responses   // remember to match the json objects names to the Model Object Variable names.
    ) {

        return quizService.submitQuiz(quizId, responses);
    }
}
