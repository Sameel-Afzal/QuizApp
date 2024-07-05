package com.sameel.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id    
    private Integer qid;
    private String category;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String difficultyLevel;
    private String rightAnswer;

}
