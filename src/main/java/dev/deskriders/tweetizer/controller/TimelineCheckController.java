package dev.deskriders.tweetizer.controller;

import dev.deskriders.tweetizer.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TimelineCheckController {

    private final TimelineService timelineService;

    @PostMapping("/timeline-checks")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void checkTimeline() {
        timelineService.processMentions();
    }
}
