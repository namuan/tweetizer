package dev.deskriders.tweetizer.external;

import dev.deskriders.tweetizer.model.Mention;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

@Component
@Slf4j
public class TwitterAdapter {

    private final Twitter twitter = TwitterFactory.getSingleton();

    @SneakyThrows
    public List<Mention> getMentionsTimeline() {
        ResponseList<Status> mentionsTimeline = twitter.getMentionsTimeline();
        return mentionsTimeline.stream().map(this::convert).collect(Collectors.toList());
    }

    @SneakyThrows
    public String postReply(Mention mention) {
        log.info("Posting reply for {}", mention);
        String tweetMessage = "@" + mention.getTweetAuthor() + "Here is your roll";
        StatusUpdate replyTweet = new StatusUpdate(tweetMessage);
        replyTweet.inReplyToStatusId(parseLong(mention.getStatusId()));
        Status updatedStatus = twitter.updateStatus(replyTweet);
        log.info("Reply posted. Updated status {}", updatedStatus);
        return String.valueOf(updatedStatus.getId());
    }

    private Mention convert(Status status) {
        return Mention.builder()
                .statusId(String.valueOf(status.getId()))
                .tweetAuthor(status.getUser().getScreenName())
                .build();
    }
}
