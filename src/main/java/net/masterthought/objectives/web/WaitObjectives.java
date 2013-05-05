package net.masterthought.objectives.web;

import net.masterthought.core.Agent;
import net.masterthought.core.Objective;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitObjectives {

    public static Objective<Boolean> waitFor(final Long millis) {


        return new Objective<Boolean>(true) {

            private Boolean result;

            @Override
            public Boolean complete(Agent agent) {
                try {
                    Thread.sleep(millis);
                    result = expectedResult;
                } catch (InterruptedException e) {
                    throw new RuntimeException("Interrupted wait statements: " + e.getMessage());
                }
                return true;
            }

            @Override
            public void completeAndConfirm(Agent agent) {
                Assert.assertTrue(complete(agent));
            }

            @Override
            public Boolean getResult() {
                return result;
            }
        };
    }

    public static Objective<Boolean> waitFor(final ExpectedCondition... expectedConditions) {

        return new Objective<Boolean>(true) {

            private Boolean result;

            @Override
            public Boolean complete(Agent agent) {
                WebDriver webDriver = agent.use(WebDriver.class);
                FluentWait<WebDriver> webDriverFluentWait = new WebDriverWait(webDriver, 10000).pollingEvery(1, TimeUnit.SECONDS).withMessage("Condition not met.");
                for(ExpectedCondition expectedCondition : expectedConditions){
                    if(webDriverFluentWait.until(expectedCondition) == null) {
                        result = expectedResult;
                        return expectedResult;
                    }
                }
                return !expectedResult;
            }

            @Override
            public void completeAndConfirm(Agent agent) {
                Assert.assertTrue(complete(agent));
            }

            @Override
            public Boolean getResult() {
                return result;
            }
        };
    }


}
