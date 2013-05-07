package net.masterthought.tfw.examples;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import net.masterthought.core.Agent;
import org.openqa.selenium.firefox.FirefoxDriver;

import static net.masterthought.objectives.web.CloseObjectives.closeBrowser;

public class MyStepdefs {

    private Agent agent;

    SearchGoogle searchGoogle = new SearchGoogle();


    @Before
    public void setUp(){
        agent = new Agent(new FirefoxDriver());
    }

    @After
    public void tearDown(){
        agent.doThe(closeBrowser());
    }

    @Given("^I search for a term in Google$")
    public void I_search_for_a_term_in_Google() throws Throwable {
        agent.bearInMind("WebSites", "latest", "http://www.google.co.uk");
        agent.doThe(searchGoogle);
    }

}
