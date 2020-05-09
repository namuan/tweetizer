package dev.deskriders.tweetizer.service;

import dev.deskriders.tweetizer.external.TwitterAdapter;
import dev.deskriders.tweetizer.model.Mention;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TimelineService {
    private final TwitterAdapter twitterAdapter;

    public List<String> processMentions() {
        List<Mention> mentionsTimeline = twitterAdapter.getMentionsTimeline();
        log.info("Received total mentions: {}", mentionsTimeline.size());

        return mentionsTimeline.stream()
                .map(twitterAdapter::postReply)
                .collect(Collectors.toList());
    }
}
