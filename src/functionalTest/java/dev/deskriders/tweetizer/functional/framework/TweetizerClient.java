package dev.deskriders.tweetizer.functional.framework;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TweetizerClient {

    public void timelineCheck() {
        Response response = given()
                .post("/timeline-checks")
                .andReturn();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_ACCEPTED);
    }
}
