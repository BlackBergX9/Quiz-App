package org.blackbergx9.quizpage.service;

import org.blackbergx9.quizpage.model.Question;
import org.blackbergx9.quizpage.dao.QuestionDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {

        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category)
    {
        try {
            return new ResponseEntity<>(questionDao.findByCategoryIgnoreCase(category), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }



}