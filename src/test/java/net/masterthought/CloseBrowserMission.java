package net.masterthought;

import net.masterthought.core.Agent;
import net.masterthought.core.Mission;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static net.masterthought.objectives.web.ClickObjectives.clickOn;
import static net.masterthought.objectives.web.CloseObjectives.closeBrowser;
import static net.masterthought.objectives.web.WaitObjectives.waitFor;

public class CloseBrowserMission implements Mission{


    @Override
    public void accomplish(Agent agent) {
        agent.doThe(
                closeBrowser()
        );
    }
}
