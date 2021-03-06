package dev.deskriders.tweetizer.contracts;

import org.junit.jupiter.api.Test;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class TwitterApiShould {

    private final Twitter twitter = TwitterFactory.getSingleton();
    private final String status = "@u Replying back with another test";
    private final long inReplyToStatusId = 1231456432L;

    @Test
    void getMentionsTimeline() throws TwitterException {
        // WHEN
        ResponseList<Status> homeTimeline = twitter.getMentionsTimeline();

        // THEN
        assertThat(homeTimeline).isNotEmpty();
    }

    @Test
    void replyBackToMentionedTweet() throws TwitterException {
        // GIVEN
        StatusUpdate messageReplied = new StatusUpdate(status);
        messageReplied.inReplyToStatusId(inReplyToStatusId);

        // WHEN
        Status status = twitter.updateStatus(messageReplied);

        // THEN
        assertThat(status.getText()).isEqualTo(status);
    }
}
