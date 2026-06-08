package org.blackbergx9.quizpage.service;

import lombok.extern.slf4j.Slf4j;
import org.blackbergx9.quizpage.dao.QuestionDao;
import org.blackbergx9.quizpage.dao.QuizDao;
import org.blackbergx9.quizpage.model.Question;
import org.blackbergx9.quizpage.model.QuestionWrapper;
import org.blackbergx9.quizpage.model.Quiz;
import org.blackbergx9.quizpage.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        Quiz  quiz = new Quiz();

        quiz.setTitle(title);
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success: Quiz ID: "+ quizDao.findByTitle(title).getId(), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer quizId) {

        try
        {
            Optional<Quiz> quiz = quizDao.findById(quizId);
            List<QuestionWrapper> questionsAfterWrapping = new ArrayList<>();

            if(quiz.isPresent())
            {
                List<Question> questionsFromDB = quiz.get().getQuestions();

                questionsFromDB.forEach(q -> questionsAfterWrapping.add(new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption_1(),
                    q.getOption_2(),
                    q.getOption_3(),
                    q.getOption_4()
                )));
                return new ResponseEntity<>(questionsAfterWrapping, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(questionsAfterWrapping, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.CONFLICT);
        }
    }


    public synchronized ResponseEntity<Integer> submitQuiz(Integer quizId, List<UserResponse> responsesList) {
        try
        {
            int score = 0;
            Optional<Quiz> quiz = quizDao.findById(quizId);
            if (quiz.isPresent())
            {
                List<Question> DbQuestionList = quiz.get().getQuestions();

                for (UserResponse response : responsesList)
                {
                    for (Question question : DbQuestionList)
                    {
                        if (
                            response.getQuestionId().equals(question.getId()) // Compare Question ID.
                            &&
                            response.getResponse().equals(question.getRightAnswer())    // Compare Answers.
                        ) {
                            score += 1;
                        }

                    }
                }
                return new ResponseEntity<>(score, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(score, HttpStatus.NOT_FOUND);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
