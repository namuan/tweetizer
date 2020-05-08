package dev.deskriders.tweetizer.external;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Component
@Slf4j
public class TwitterAdapter {

    private final Twitter twitter = TwitterFactory.getSingleton();

    @SneakyThrows
    public ResponseList<Status> getMentionsTimeline() {
        return twitter.getMentionsTimeline();
    }
}
