package com.safari.selfservice.controller;


import com.safari.selfservice.models.FeedBack;
import com.safari.selfservice.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/feedback/")
public class FeedbackController {
    FeedBackService feedBackService;

    @Autowired
    public FeedbackController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @PostMapping
    public ResponseEntity<Boolean> createFeedback(@RequestBody FeedBack feedBack) {
        if (feedBack == null) {
            return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        } else {
            boolean feedback = feedBackService.createFeedback(feedBack);
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        }
    }
    @GetMapping
    public ResponseEntity<List<FeedBack>> getFeedbacks() {
        List<FeedBack> feedbacks = feedBackService.getFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.BAD_REQUEST);
    }
}
