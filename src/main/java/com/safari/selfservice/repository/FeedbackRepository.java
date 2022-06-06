package com.safari.selfservice.repository;

import com.safari.selfservice.models.FeedBack;
import com.safari.selfservice.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedBack, Integer> { }
