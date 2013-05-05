package net.masterthought.objectives.web;

import net.masterthought.core.Agent;
import net.masterthought.core.Objective;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class CloseObjectives {

    public static Objective<String> closeBrowser() {
        return new Objective<String>("success") {

            private String result;

            @Override
            public String complete(Agent agent) {
                try {
                    WebDriver webDriver = agent.use(WebDriver.class);
                    webDriver.quit();
                    result = expectedResult;
                } catch (Exception e) {
                    result = "fail";
                }
                return result;
            }

            @Override
            public void completeAndConfirm(Agent agent) {
                Assert.assertThat(result, CoreMatchers.is(this.expectedResult));
            }

            @Override
            public String getResult() {
                return result;
            }
        };
    }

    public static Objective<Boolean> closeWindow() {


        return new Objective<Boolean>(true) {

            private Boolean result;

            @Override
            public Boolean complete(Agent agent) {
                try {
                    WebDriver webDriver = agent.use(WebDriver.class);
                    webDriver.close();
                    result = expectedResult;
                } catch (Exception e) {
                    result = !expectedResult;
                }
                return result;
            }

            @Override
            public void completeAndConfirm(Agent agent) {
                Assert.assertThat(result, CoreMatchers.is(this.expectedResult));
            }

            @Override
            public Boolean getResult() {
                return result;
            }
        };
    }
}
