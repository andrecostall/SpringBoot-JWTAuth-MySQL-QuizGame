package com.mxstore.service;

import java.util.List;

import com.mxstore.entity.Question;

public interface QuestionService {
	
	int getCountEasyQuestions();

	void setCountEasyQuestions(int countEasyQuestions);

	int getCountMediumQuestions();

	void setCountMediumQuestions(int countMediumQuestions);

	int getCountHardQuestions();

	void setCountHardQuestions(int countHardQuestions);

	List<Question> getAllQuestions();
	
	List<Question> findAllEasyQuestions();
	
	List<Question> findAllMediumQuestions();
	
	List<Question> findAllHardQuestions();
	
	List<Question> createListQuestionsForGame();
	
	List<Question> selectQuestions(List<Question> list, int countQuestions);

	Question getQuestionById(Long id);
	
	Question addQuestion(Question a);

	Question updateQuestion(Question a);

	void deleteQuestion(Long id);

	boolean existsQuestion(Long id);

}