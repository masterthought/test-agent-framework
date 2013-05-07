package net.masterthought.tfw.examples;

import net.masterthought.core.Agent;
import net.masterthought.core.Mission;
import org.openqa.selenium.By;

import static net.masterthought.objectives.web.CloseObjectives.closeBrowser;
import static net.masterthought.objectives.web.FillObjectives.fillIn;
import static net.masterthought.objectives.web.NavigationObjectives.navigateTo;

/**
 * User: kostasmamalis
 */
public class SearchGoogle implements Mission {


    @Override
    public void accomplish(Agent agent) {
        agent.doThe(
                navigateTo(agent.recallFromMemory("WebSites", "latest", String.class)),
                fillIn(By.id("gbqfq"),"test"),
                closeBrowser()
        );
    }
}
