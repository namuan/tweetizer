package dev.deskriders.tweetizer.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Mention {
    String statusId;
    String tweetAuthor;
}
