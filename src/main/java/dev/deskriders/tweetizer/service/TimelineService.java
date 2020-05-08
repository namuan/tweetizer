package dev.deskriders.tweetizer.service;

import dev.deskriders.tweetizer.external.TwitterAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twitter4j.ResponseList;
import twitter4j.Status;

@Service
@Slf4j
@RequiredArgsConstructor
public class TimelineService {
    private final TwitterAdapter twitterAdapter;

    public void processMentions() {
        ResponseList<Status> mentionsTimeline = twitterAdapter.getMentionsTimeline();
        log.info("Received Mentions: {}", mentionsTimeline);
    }
}
