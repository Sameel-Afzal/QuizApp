package com.sameel.quizapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sameel.quizapp.model.Question;
import com.sameel.quizapp.service.QuestionService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    
    @GetMapping("getAll")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PutMapping("{operation}")
    public ResponseEntity<String> updateQuestion(@PathVariable String operation, @RequestBody Question question) {
            return questionService.updateQuestionIfExists(operation, question);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteQuestion(@RequestBody String qId) {
        return questionService.deleteQuestion(Integer.parseInt(qId));
    }
}

