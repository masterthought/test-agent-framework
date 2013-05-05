package net.masterthought;

import net.masterthought.core.Agent;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AcceptanceForGoogle {

    GoToWebsite goToWebsite = new GoToWebsite();
    SearchAtGoogle searchAtGoogle = new SearchAtGoogle();
    CloseBrowserMission closeBrowserMission = new CloseBrowserMission();

    @Test
    public void useDifferentBrowsers() throws Exception {

        Agent googleSearchPerson = new Agent(new FirefoxDriver());
        Agent localProjectUser = new Agent(new FirefoxDriver());
        googleSearchPerson.bearInMind("WebSites", "latest", "http://www.google.co.uk");
        localProjectUser.bearInMind("WebSites", "latest", "http://localhost:8080");
        googleSearchPerson.doThe(goToWebsite);
        localProjectUser.doThe(goToWebsite);
        googleSearchPerson.doThe(closeBrowserMission);
        localProjectUser.doThe(closeBrowserMission);
    }

    @Test
    public void useSameBrowser() throws Exception {
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        Agent recruiter = new Agent(firefoxDriver);
        Agent personLookingForJob = new Agent(firefoxDriver);
        recruiter.bearInMind("WebSites", "latest", "http://www.linkedin.com");
        personLookingForJob.bearInMind("WebSites", "latest", "http://www.jobserve.com");
        recruiter.doThe(goToWebsite);
        personLookingForJob.doThe(goToWebsite);
        recruiter.doThe(closeBrowserMission);
    }

    @Test
    public void goToWebsite() throws Exception {
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        Agent googleSearchPerson = new Agent(firefoxDriver);
        googleSearchPerson.bearInMind("WebSites", "latest", "http://www.google.co.uk");
        googleSearchPerson.doThe(searchAtGoogle);
    }

}
