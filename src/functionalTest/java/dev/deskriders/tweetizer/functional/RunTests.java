package dev.deskriders.tweetizer.functional;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"pretty", "json:target/cucumber-report.json", "de.monochromata.cucumber.report.PrettyReports:target/pretty-cucumber"},
        features = "src/functionalTest/resources/features"
)
public class RunTests {
}
