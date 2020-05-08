package dev.deskriders.tweetizer.functional.scenarios;


import dev.deskriders.tweetizer.TweetizerApplication;
import dev.deskriders.tweetizer.functional.framework.FakeTwitter;
import dev.deskriders.tweetizer.functional.framework.TweetizerClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import twitter4j.StatusUpdate;

@SpringBootTest(classes = TweetizerApplication.class)
@CucumberContextConfiguration
public class TwitterDigestSteps {

    private final FakeTwitter fakeTwitter = new FakeTwitter();
    private final TweetizerClient tweetizerClient = new TweetizerClient();

    @Given("a tagged tweet")
    public void aTaggedTweet() {
        StatusUpdate statusUpdate = new StatusUpdate("Hello World");
        fakeTwitter.postStatusWithMention(statusUpdate);
    }

    @When("the tweet is processed")
    public void theTweetIsProcessed() {
        tweetizerClient.timelineCheck();
    }

    @Then("I should reply back with acknowledgement")
    public void iShouldReplyBackWithAcknowledgement() {
        StatusUpdate acknowledgementStatusUpdate = new StatusUpdate("Hello Back");
        fakeTwitter.receivedAn(acknowledgementStatusUpdate);
    }
}
