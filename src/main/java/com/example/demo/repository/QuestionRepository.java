package com.example.demo.repository;


import com.example.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByPublished(boolean published);

    List<Question> findByTitleContaining(String title);

    List<Question> getQuestionsByTestId(Long id);
}