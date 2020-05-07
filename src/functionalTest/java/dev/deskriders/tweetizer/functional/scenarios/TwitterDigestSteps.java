package dev.deskriders.tweetizer.functional.scenarios;


import dev.deskriders.tweetizer.TweetizerApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TweetizerApplication.class)
@CucumberContextConfiguration
public class TwitterDigestSteps {

    @Given("a tagged tweet")
    public void aTaggedTweet() {

    }

    @When("the tweet is processed")
    public void theTweetIsProcessed() {

    }

    @Then("I should reply back with acknowledgement")
    public void iShouldReplyBackWithAcknowledgement() {

    }
}
