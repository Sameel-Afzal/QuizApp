package com.sameel.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sameel.quizapp.model.QuestionWrapper;
import com.sameel.quizapp.model.Response;
import com.sameel.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
    
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam Integer numOfQs, @RequestParam String title) {
        return quizService.createQuiz(category, numOfQs, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("id") Integer quizId) {

        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitResponse(@PathVariable("id") Integer quizId, @RequestBody List<Response> userResponses) {
        Integer result = quizService.calculateResult(quizId, userResponses);
        if(result < 0)
            return new ResponseEntity<>("Invalid URL", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>("Your Score: " + result, HttpStatus.OK);
    }
}
