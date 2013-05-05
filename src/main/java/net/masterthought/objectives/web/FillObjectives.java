package net.masterthought.objectives.web;

import net.masterthought.core.Agent;
import net.masterthought.core.Objective;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FillObjectives {

    public static Objective<Boolean> fillIn(final By by, final String value ){
        return new Objective<Boolean>(true) {

            private Boolean result;

            @Override
            public Boolean complete(Agent agent) {
                try {
                    WebDriver webDriver = agent.use(WebDriver.class);
                    webDriver.findElement(by).sendKeys(value);
                    result = expectedResult;
                } catch (Exception e) {
                    result = !expectedResult;
                }
                return result;
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
