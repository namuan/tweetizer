package dev.deskriders.tweetizer.functional.framework;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.HttpStatus;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.springframework.util.ResourceUtils.getFile;

public class FakeTwitter {
    public static final int ONCE = 1;
    private final WireMockServer wireMockServer;

    public FakeTwitter(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
        start();
    }

    public void stubRequestToGetMentionsTimeline() throws IOException {
        stubFor(get(urlEqualTo("/statuses/mentions_timeline.json?include_entities=true&tweet_mode=extended")).willReturn(
                aResponse()
                        .withBody(loadResponseFromFile("responses/mentions_timeline.json"))
                        .withStatus(HttpStatus.SC_OK))
        );
    }

    public void stubRequestToUpdateStatus() throws IOException {
        stubFor(post(urlEqualTo("/statuses/update.json")).willReturn(
                aResponse()
                        .withBody(loadResponseFromFile("responses/update.json"))
                        .withStatus(HttpStatus.SC_OK))
        );
    }

    public void verifyRequestReceivedForTweetReply() throws IOException {
        verify(
                ONCE,
                postRequestedFor(urlEqualTo("/statuses/update.json"))
                        .withRequestBody(equalTo(loadResponseFromFile("requests/update.txt")))
        );
    }

    public void start() {
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
        wireMockServer.resetAll();
    }

    public void stop() {
        wireMockServer.stop();
    }

    public String loadResponseFromFile(String filename) throws IOException {
        return readFileToString(getFile(format("classpath:twitter/%s", filename)), UTF_8);
    }
}
