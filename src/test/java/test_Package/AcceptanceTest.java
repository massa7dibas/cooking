package test_Package;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;



@RunWith(Cucumber.class)
@CucumberOptions(features="Features",plugin={"html:target/cucumber/wikipedia.html"},monochrome=true,snippets=SnippetType.CAMELCASE,glue={"test_Package"})


public class AcceptanceTest {


}