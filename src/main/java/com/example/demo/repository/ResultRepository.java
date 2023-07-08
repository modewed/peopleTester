package com.example.demo.repository;


import com.example.demo.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByPublished(boolean published);

    List<Result> findByTitleContaining(String title);

    List<Result> getResultsByTestId(Long id);
}