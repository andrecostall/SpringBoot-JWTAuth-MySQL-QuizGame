package com.mxstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mxstore.entity.Question;


@Repository	
public interface QuestionRepo extends JpaRepository<Question, Long>{

	@Query("SELECT q FROM Question q WHERE q.level = 1 and q.used = false")
	public List<Question> findAllEasyQuestions();
	
	@Query("SELECT q FROM Question q WHERE q.level = 2 and q.used = false")
	public List<Question> findAllMediumQuestions();
	
	@Query("SELECT q FROM Question q WHERE q.level = 3 and q.used = false")
	public List<Question> findAllHardQuestions();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Question q SET q.used = true where q.id = :id and q.used = false")
	public int updateQuestionUsed(@Param("id") long id);
}
