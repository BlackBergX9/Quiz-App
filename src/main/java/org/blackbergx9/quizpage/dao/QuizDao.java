package org.blackbergx9.quizpage.dao;

import org.blackbergx9.quizpage.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {


    Quiz findByTitle(String title);
}
