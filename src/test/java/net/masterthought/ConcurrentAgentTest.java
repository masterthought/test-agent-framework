package net.masterthought;

import net.masterthought.concurrent.ConcurrentAgent;
import net.masterthought.core.Agent;
import net.masterthought.core.Mission;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ConcurrentAgentTest {

    private class AnotherDummy {
        public String getTest() { return test; }
        private String test;
        public AnotherDummy() {}
        private AnotherDummy(String test) {  this.test = test;  }
    }

    @Test
    public void heShouldBeAbleToRememberAndRecallStringFromTheSameCategoryWithDifferentKeys() throws Exception {
        ConcurrentAgent agent = new ConcurrentAgent();
        agent.bearInMind("my_category", "my_first_key", new AnotherDummy());
        agent.bearInMind("my_category", "my_second_key", new AnotherDummy("testValue"));
        AnotherDummy anotherDummy = agent.recallFromMemory("my_category", "my_first_key", AnotherDummy.class);
        AnotherDummy anotherDummy2 = agent.recallFromMemory("my_category", "my_second_key", AnotherDummy.class);
        assertThat(anotherDummy, notNullValue());
        assertThat(anotherDummy.getTest(), nullValue());
        assertThat(anotherDummy2, notNullValue());
        assertThat(anotherDummy2.getTest(), is("testValue"));
    }

    private enum CATEGORIES {CATEGORY1, CATEGORY2}

    private enum KEYS {KEY1, KEY2}

    @Test
    public void heShouldBeAbleToRememberAndRecallEnumFromTheSameCategoryWithDifferentKeys() throws Exception {

        ConcurrentAgent agent = new ConcurrentAgent();
        agent.bearInMind(CATEGORIES.CATEGORY1, KEYS.KEY1, new AnotherDummy());
        agent.bearInMind(CATEGORIES.CATEGORY1, KEYS.KEY2, new AnotherDummy("testValue"));
        AnotherDummy anotherDummy = agent.recallFromMemory(CATEGORIES.CATEGORY1, KEYS.KEY1, AnotherDummy.class);
        AnotherDummy anotherDummy2 = agent.recallFromMemory(CATEGORIES.CATEGORY1, KEYS.KEY2, AnotherDummy.class);
        assertThat(anotherDummy, notNullValue());
        assertThat(anotherDummy.getTest(), nullValue());
        assertThat(anotherDummy2, notNullValue());
        assertThat(anotherDummy2.getTest(), is("testValue"));
    }

    @Test
    public void heShouldBeAbleToRememberAndRecallFromTheSameObjectCategoryWithDifferentKeys() throws Exception {
        ConcurrentAgent agent = new ConcurrentAgent();
        AnotherDummy common_object_category = new AnotherDummy();
        AnotherDummy another_common_object_category = new AnotherDummy("secondOne");
        agent.bearInMind(common_object_category, KEYS.KEY1, new AnotherDummy());
        agent.bearInMind(common_object_category, KEYS.KEY2, new AnotherDummy("testValue"));
        agent.bearInMind(another_common_object_category, KEYS.KEY1, new AnotherDummy());
        agent.bearInMind(another_common_object_category, KEYS.KEY2, new AnotherDummy("testValue2"));
        AnotherDummy anotherDummy = agent.recallFromMemory(common_object_category, KEYS.KEY1, AnotherDummy.class);
        AnotherDummy anotherDummy2 = agent.recallFromMemory(common_object_category, KEYS.KEY2, AnotherDummy.class);
        AnotherDummy anotherDummy3 = agent.recallFromMemory(another_common_object_category, KEYS.KEY1, AnotherDummy.class);
        AnotherDummy anotherDummy4 = agent.recallFromMemory(another_common_object_category, KEYS.KEY2, AnotherDummy.class);
        assertThat(anotherDummy, notNullValue());
        assertThat(anotherDummy.getTest(), nullValue());
        assertThat(anotherDummy2, notNullValue());
        assertThat(anotherDummy2.getTest(), is("testValue"));
        assertThat(anotherDummy3, notNullValue());
        assertThat(anotherDummy3.getTest(), nullValue());
        assertThat(anotherDummy4, notNullValue());
        assertThat(anotherDummy4.getTest(), is("testValue2"));
    }

    @Test
    public void heShouldBeAbleToOverrideStuffInHisMemory() throws Exception {

        Agent agent = new ConcurrentAgent();
        AnotherDummy common = new AnotherDummy();
        agent.bearInMind(common, KEYS.KEY1, new AnotherDummy());
        AnotherDummy anotherDummy = agent.recallFromMemory(common, KEYS.KEY1, AnotherDummy.class);
        assertThat(anotherDummy, notNullValue());
        assertThat(anotherDummy.getTest(), nullValue());
        //override
        agent.bearInMind(common, KEYS.KEY1, new AnotherDummy("testValue"));
        AnotherDummy anotherDummy2 = agent.recallFromMemory(common, KEYS.KEY1, AnotherDummy.class);
        assertThat(anotherDummy2, notNullValue());
        assertThat(anotherDummy2.getTest(), is("testValue"));
    }


    private class SecretMission implements Mission {

        @Override
        public void accomplish(Agent agent) {
            WebDriver webDriver = agent.use(WebDriver.class);
            webDriver.get("http://www.google.co.uk");
            webDriver.quit();
        }
    }

    @Test
    public void heShouldBeAbleToUseSkill() throws Exception {
        ConcurrentAgent agent = new ConcurrentAgent();
        agent.obtain(new FirefoxDriver());
        agent.doThe(new SecretMission());
    }
}
