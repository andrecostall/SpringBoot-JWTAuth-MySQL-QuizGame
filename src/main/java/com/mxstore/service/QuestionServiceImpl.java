package com.mxstore.service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxstore.entity.Answer;
import com.mxstore.entity.Question;
import com.mxstore.repository.QuestionRepo;

@Service
public class QuestionServiceImpl implements QuestionService{
	 
	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	AnswerService answerService;
	
	private int countEasyQuestions = 5;	
	private int countMediumQuestions = 5;	
	private int countHardQuestions = 5;	
	
	@Override
	public int getCountEasyQuestions() {
		return countEasyQuestions;
	}

	@Override
	public void setCountEasyQuestions(int countEasyQuestions) {
		this.countEasyQuestions = countEasyQuestions;
	}

	@Override
	public int getCountMediumQuestions() {
		return countMediumQuestions;
	}

	@Override
	public void setCountMediumQuestions(int countMediumQuestions) {
		this.countMediumQuestions = countMediumQuestions;
	}

	@Override
	public int getCountHardQuestions() {
		return countHardQuestions;
	}

	@Override
	public void setCountHardQuestions(int countHardQuestions) {
		this.countHardQuestions = countHardQuestions;
	}

	@Override
	public List<Question> getAllQuestions() {
		return questionRepo.findAll();
	}

	@Override
	public Question getQuestionById(Long id) {	
		if (!existsQuestion(id)) {
			throw new EntityNotFoundException("not found question with this id"+ id);
		}
		return questionRepo.findById(id).get();
	}

	@Override
	public Question addQuestion(Question q) {	
		if (existsQuestion(q.getId())) {
			throw new EntityExistsException("already exists question with this id" + q.getId());
		}
		 q = questionRepo.save(q);
		 
		for (Answer answer : q.getAnswerCollection()) 
		{
			answerService.addAnswer(q.getId(), answer);
		}
		
		return q;
	}

	@Override
	public Question updateQuestion(Question q) {
		if (!existsQuestion(q.getId())) {
			throw new EntityNotFoundException("not found question with this id" + q.getId());
		}
				
		for (Answer answer : q.getAnswerCollection()) 
		{
			answerService.updateAnswer(q.getId(), answer);
		}
		
		q = questionRepo.save(q);
		
		return q;		
	}

	@Override
	public void deleteQuestion(Long id) {
		//answerService.deleteAnswer(id);
		questionRepo.deleteById(id);
	}

	@Override
	public boolean existsQuestion(Long id) {		
		return questionRepo.existsById(id);
	}

	@Override
	public List<Question> findAllEasyQuestions() {		
		return questionRepo.findAllEasyQuestions();
	}

	@Override
	public List<Question> findAllMediumQuestions() {		
		return questionRepo.findAllMediumQuestions();
	}

	@Override
	public List<Question> findAllHardQuestions() {		
		return questionRepo.findAllHardQuestions();
	}
		
	@Override
	public List<Question> selectQuestions(List<Question> list, int countQuestions){
		List<Question> newList = new LinkedList<Question>();
		
		for(int i = 0; i < countQuestions; i++)
		{ 
			newList.add(list.get(i));
			questionRepo.updateQuestionUsed(list.get(i).getId());
		}
		return newList;
	}
	
	@Override
	public List<Question> createListQuestionsForGame() {			
		List<Question> easyList = new LinkedList<Question>();
		easyList.addAll(findAllEasyQuestions());
		List<Question> mediumList = new LinkedList<Question>();	
		mediumList.addAll(findAllMediumQuestions());
		List<Question> hardList = new LinkedList<Question>();
		hardList.addAll(findAllHardQuestions());
		List<Question> gameList = new LinkedList<Question>();
		gameList.addAll(selectQuestions(easyList, countEasyQuestions));
		gameList.addAll(selectQuestions(mediumList, countMediumQuestions));
		gameList.addAll(selectQuestions(hardList, countHardQuestions));
		
		return gameList;
	}
	
	
	
}
