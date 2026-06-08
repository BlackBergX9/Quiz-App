package org.blackbergx9.quizpage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;        // In Spring,
    private String difficultyLevel; // Capital letter convert into underscore followedBy lowercase Letter,

    private String questionTitle;   // T → _t

    private String option_1;        // but Numbers have no casing, so it will not convert it.
    private String option_2;        // Need to type same name as Database Column.
    private String option_3;
    private String option_4;

    private String rightAnswer;
}