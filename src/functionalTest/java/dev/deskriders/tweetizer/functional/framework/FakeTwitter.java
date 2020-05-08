package dev.deskriders.tweetizer.functional.framework;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import twitter4j.StatusUpdate;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.readFileToString;

public class FakeTwitter {
    public static final int ONCE = 1;
    private final WireMockServer wireMockServer;

    public FakeTwitter(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
        start();
    }

    public void postStatusWithMention(StatusUpdate statusUpdate) throws IOException {
        stubFor(get(urlEqualTo("/statuses/mentions_timeline.json?include_entities=true&tweet_mode=extended")).willReturn(
                aResponse()
                        .withBody(loadResponseFromFile())
                        .withStatus(HttpStatus.SC_OK))
        );
    }

    public void receivedAn(StatusUpdate acknowledgement) {
        verify(
                ONCE,
                postRequestedFor(urlEqualTo("/statuses/update.json"))
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

    public String loadResponseFromFile() throws IOException {
        return readFileToString(ResourceUtils.getFile("classpath:twitter/responses/mentions_timeline.json"), UTF_8);
    }
}
