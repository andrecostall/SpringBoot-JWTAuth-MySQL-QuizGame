package com.mxstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mxstore.entity.Answer;
import com.mxstore.service.AnswerService;

@RestController
@CrossOrigin(origins = "*")
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	@GetMapping("answer/{id}")
	public ResponseEntity<Answer> getAnswerById(@PathVariable("id") Long id) {
		Answer a = answerService.getAnswerById(id);
		return new ResponseEntity<Answer>(a, HttpStatus.OK);
	}

	@GetMapping("answers")
	public ResponseEntity<List<Answer>> getAllAnswers() {
		List<Answer> list = answerService.getAllAnswers();
		return new ResponseEntity<List<Answer>>(list, HttpStatus.OK);
	}

	@GetMapping("answers/{id}")
	public ResponseEntity<List<Answer>> getAllAnswersByQuestionId(@PathVariable("id") Long id) {
		List<Answer> list = answerService.findAllAnswersByQuestionId(id);
		return new ResponseEntity<List<Answer>>(list, HttpStatus.OK);
	}
	
	/*@PostMapping("answer")
	public ResponseEntity<Answer> addAnswer(@RequestBody Answer a) {
		Answer anew = answerService.addAnswer(a);
		return new ResponseEntity<Answer>(anew, HttpStatus.CREATED);
	}*/

	/*@PutMapping("answer")
	public ResponseEntity<Answer> updateAnswer(@RequestBody Answer a) {
		Answer anew = answerService.updateAnswer(a);
		return new ResponseEntity<Answer>(anew, HttpStatus.OK);
	}
*/
	@DeleteMapping("answer/{id}")
	public ResponseEntity<Void> deleteAnswer(@PathVariable("id") Long id) {
		answerService.deleteAnswer(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
}
