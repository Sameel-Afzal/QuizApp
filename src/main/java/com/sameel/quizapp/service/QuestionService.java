package com.sameel.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sameel.quizapp.dao.QuestionDao;
import com.sameel.quizapp.model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    
    public ResponseEntity<List<Question>> getAllQuestions() {

        try {
            return new ResponseEntity<>(questionDao.findAll(Sort.by(Direction.ASC, "qid")), HttpStatus.FOUND);
        } catch(Exception e) { e.printStackTrace(); }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }
 
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        try {
            return new ResponseEntity<>(questionDao.findByCategoryOrderByQid(category), HttpStatus.FOUND);
        } catch(Exception e) { e.printStackTrace(); }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateQuestionIfExists(String operation, Question question) {    

        if(operation.equals("add") || operation.equals("update")) {
            try {  
                questionDao.save(question);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            }
            catch(Exception e) {e.printStackTrace();}  
        }
        return new ResponseEntity<>("Invalid operation Assigned", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(Integer qId) {
        
        questionDao.deleteById(qId);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }
}
