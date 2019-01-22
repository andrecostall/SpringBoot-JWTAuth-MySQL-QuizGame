package com.mxstore.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxstore.entity.Answer;
import com.mxstore.entity.Question;
import com.mxstore.repository.AnswerRepo;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	AnswerRepo answerRepo;
	
	@Autowired
	QuestionService questionService;
	
	public List<Answer> getAllAnswers() {
		return answerRepo.findAll();
	}

	public Answer getAnswerById(Long id) {	
		if (!existsAnswer(id)) {
			throw new EntityNotFoundException("not found answer with this id");
		}
		return answerRepo.findById(id).get();
	}

	public List<Answer> findAllAnswersByQuestionId(Long id) {		
		return answerRepo.findAllAnswersByQuestionId(id);
	}

	public Answer addAnswer(Long id, Answer a) {	
		if (existsAnswer(a.getId())) {
			throw new EntityExistsException("already exists answer with this id");
		}
		Question q = questionService.getQuestionById(id);
		a.setQuestion(q);
		a = answerRepo.save(a);
		return a;
	}

	public Answer updateAnswer(Long id, Answer a) {
		if (!existsAnswer(a.getId())) {
			throw new EntityNotFoundException("not found answer with this id");
		}
		Question q = questionService.getQuestionById(id);
		a.setQuestion(q);
		answerRepo.save(a);
		return a;
	}

	public void deleteAnswer(Long id) {
		/*List<Answer> lst = answerRepo.findAllAnswersByQuestionId(id);
		for (Answer answer : lst){
		answerRepo.deleteById(answer.getId());
		}*/
		answerRepo.deleteById(id);
	}

	public boolean existsAnswer(Long id) {		
		return answerRepo.existsById(id);
	}
	
}
