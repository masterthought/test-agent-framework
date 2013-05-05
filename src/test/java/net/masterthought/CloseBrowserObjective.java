package net.masterthought;

import net.masterthought.core.Agent;
import net.masterthought.core.Mission;
import net.masterthought.core.Objective;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class CloseBrowserObjective implements Mission {


    @Override
    public void accomplish(Agent agent) {
        agent.doAndConfirm(new Objective("success") {
            @Override
            public Object complete(Agent agent) {
                WebDriver webDriver = agent.use(WebDriver.class);
                webDriver.quit();
                return "success";
            }

            @Override
            public void completeAndConfirm(Agent agent) {
                String result = (String) complete(agent);
                Assert.assertThat(result, CoreMatchers.is((String) this.expectedResult));
            }
        });

    }

}