package dev.deskriders.tweetizer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimelineCheckController {

    @PostMapping("/timeline-checks")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void checkTimeline() {

    }
}
