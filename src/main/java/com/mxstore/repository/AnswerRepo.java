package com.mxstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mxstore.entity.Answer;


@Repository	
public interface AnswerRepo extends JpaRepository<Answer, Long>{

	@Query("SELECT ans FROM Answer ans where ans.question.id = :id")
	public List<Answer> findAllAnswersByQuestionId(@Param("id") Long id);
	
}
