package org.blackbergx9.quizpage.dao;

import org.blackbergx9.quizpage.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>
// JpaRepository<Class referring the Database's Table, PrimaryKey Datatype>
{
    // Custom Method using JPA, find Data in columnName & Ignore Case..
    List<Question> findByCategoryIgnoreCase(String category);

    @Query(value= "SELECT * FROM question q WHERE LOWER(q.category)=LOWER(:category) ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
