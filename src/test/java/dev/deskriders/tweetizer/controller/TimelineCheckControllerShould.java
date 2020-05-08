package dev.deskriders.tweetizer.controller;

import dev.deskriders.tweetizer.service.TwitterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TimelineCheckControllerShould {

    @Mock
    private TwitterService twitterService;

    @InjectMocks
    private TimelineCheckController controller;

    @Test
    void delegateToService() {
        controller.checkTimeline();

        verify(twitterService).processMentions();
    }
}