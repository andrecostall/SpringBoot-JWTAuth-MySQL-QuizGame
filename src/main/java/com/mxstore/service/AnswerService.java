package com.mxstore.service;

import java.util.List;

import com.mxstore.entity.Answer;

public interface AnswerService {
	
		List<Answer> getAllAnswers();

		Answer getAnswerById(Long id);

		List<Answer> findAllAnswersByQuestionId(Long id);
		
		Answer addAnswer(Long id, Answer a);

		Answer updateAnswer(Long id, Answer a);

		void deleteAnswer(Long id);

		boolean existsAnswer(Long id);

}

