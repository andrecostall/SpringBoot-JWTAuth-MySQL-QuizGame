package com.mxstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mxstore.entity.Question;
import com.mxstore.service.QuestionService;

@RestController
@CrossOrigin(origins = "*")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/auth/javainuse")
	public String sayHello() {
		return "Swagger Hello World";
	}
	
	@GetMapping("question/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable("id") Long id) {
		Question q = questionService.getQuestionById(id);
		return new ResponseEntity<Question>(q, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("questions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		List<Question> list = questionService.getAllQuestions();
		return new ResponseEntity<List<Question>>(list, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("question")
	public ResponseEntity<Question> addQuestion(@RequestBody Question q) {
		Question qnew = questionService.addQuestion(q);
		return new ResponseEntity<Question>(qnew, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("question")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question q) {
		Question qnew = questionService.updateQuestion(q);
		return new ResponseEntity<Question>(qnew, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("question/{id}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long id) {
		questionService.deleteQuestion(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@GetMapping("getlistgame")
	public ResponseEntity<List<Question>> createListQuestionsForGame() {
		List<Question> q = questionService.createListQuestionsForGame();
		return new ResponseEntity<List<Question>>(q, HttpStatus.OK);
	}
	
	@GetMapping("getcounteasy")
	public int getCountEasy() {
		return  questionService.getCountEasyQuestions();		
	}
	
	@GetMapping("getcountmedium")
	public int setCountMedium() {
		return  questionService.getCountMediumQuestions();		
	}
	
	@GetMapping("getcounthard")
	public int setCountHard() {
		return  questionService.getCountHardQuestions();		
	}

	@PostMapping("setcounteasy/{cnt}")
	public void setCountEasy(@PathVariable("cnt") int cnt) {
		questionService.setCountEasyQuestions(cnt);
	}
	
	@PostMapping("setcountmedium/{cnt}")
	public void setCountMedium(@PathVariable("cnt") int cnt) {
		questionService.setCountMediumQuestions(cnt);	
	}
	
	@PostMapping("setcounthard/{cnt}")
	public void setCountHard(@PathVariable("cnt") int cnt) {
		questionService.setCountHardQuestions(cnt);
	}
	
}
