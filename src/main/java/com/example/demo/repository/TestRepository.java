package com.example.demo.repository;


import java.util.List;

import com.example.demo.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByPublished(boolean published);

    List<Test> findByTitleContaining(String title);
}