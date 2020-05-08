package dev.deskriders.tweetizer.functional.scenarios;


import dev.deskriders.tweetizer.TweetizerApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TweetizerApplication.class)
@CucumberContextConfiguration
public class TwitterDigestSteps {

}
