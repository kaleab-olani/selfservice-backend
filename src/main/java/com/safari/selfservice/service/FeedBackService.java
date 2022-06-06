package com.safari.selfservice.service;

import com.safari.selfservice.models.FeedBack;
import com.safari.selfservice.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackService {
    private FeedbackRepository feedbackRepository;
    @Autowired
    public FeedBackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<FeedBack> getFeedbacks() {
        return feedbackRepository.findAll();
    }

    public boolean createFeedback(FeedBack feedBack) {
        if (feedBack == null) return false;
        feedbackRepository.save(feedBack);
        return true;
    }
}
