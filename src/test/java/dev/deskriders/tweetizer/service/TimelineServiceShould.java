package dev.deskriders.tweetizer.service;

import dev.deskriders.tweetizer.external.TwitterAdapter;
import dev.deskriders.tweetizer.model.Mention;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TimelineServiceShould {

    @Mock
    private TwitterAdapter twitterAdapter;

    @InjectMocks
    private TimelineService timelineService;

    @Test
    void replyForAnyNewMention() {
        // GIVEN
        Mention firstMention = mentionWithStatusId(randomAlphanumeric(10));
        Mention secondMention = mentionWithStatusId(randomAlphanumeric(10));

        List<Mention> mentionList = givenTwitterMentionsTimeline(firstMention, secondMention);

        // WHEN
        List<String> replies = timelineService.processMentions();

        // THEN
        assertThat(replies.size()).isEqualTo(mentionList.size());
        assertThat(replies).allSatisfy(
                reply -> assertThat(reply).isNotEmpty()
        );
        assertThat(replies).containsExactlyInAnyOrder(firstMention.getStatusId(), secondMention.getStatusId());
    }


    private List<Mention> givenTwitterMentionsTimeline(Mention firstMention, Mention secondMention) {
        List<Mention> mentionList = List.of(firstMention, secondMention);
        given(twitterAdapter.getMentionsTimeline()).willReturn(mentionList);
        given(twitterAdapter.postReply(any(Mention.class))).willReturn(firstMention.getStatusId()).willReturn(secondMention.getStatusId());
        return mentionList;
    }

    private Mention mentionWithStatusId(String statusId) {
        return Mention.builder().statusId(statusId).build();
    }
}
