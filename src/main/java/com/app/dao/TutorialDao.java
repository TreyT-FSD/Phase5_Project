package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Tutorial;

public interface TutorialDao extends JpaRepository<Tutorial, Long> {}
