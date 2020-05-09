package dev.deskriders.tweetizer.functional.scenarios;


import com.github.tomakehurst.wiremock.WireMockServer;
import dev.deskriders.tweetizer.TweetizerApplication;
import dev.deskriders.tweetizer.functional.framework.FakeTwitter;
import dev.deskriders.tweetizer.functional.framework.TweetizerClient;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT, classes = TweetizerApplication.class)
@CucumberContextConfiguration
@TestPropertySource("classpath:application-test.properties")
public class TwitterDigestSteps {

    private final TweetizerClient tweetizerClient = new TweetizerClient();

    private FakeTwitter fakeTwitter;

    @Autowired
    private WireMockServer wireMockService;

    @Given("a tweet with mention")
    public void aTweetWithMention() throws IOException {
        fakeTwitter.stubRequestToGetMentionsTimeline();
        fakeTwitter.stubRequestToUpdateStatus();
    }

    @When("the tweet is processed")
    public void theTweetIsProcessed() {
        tweetizerClient.timelineCheck();
    }

    @Then("I should reply back with acknowledgement")
    public void iShouldReplyBackWithAcknowledgement() throws IOException {
        fakeTwitter.verifyRequestReceivedForTweetReply();
    }

    @Before
    public void before() {
        fakeTwitter = new FakeTwitter(wireMockService);
    }

    @After
    public void after() {
        fakeTwitter.stop();
    }
}
