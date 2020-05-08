package dev.deskriders.tweetizer.functional.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
public class WireMockConfiguration {

    @Value("${wiremock.port}")
    private int wireMockPort;

    @Bean
    public WireMockServer wireMockServer() {
        return new WireMockServer(options().port(wireMockPort));
    }
}
