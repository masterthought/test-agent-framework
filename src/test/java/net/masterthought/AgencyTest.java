package net.masterthought;

import net.masterthought.core.Agent;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AgencyTest {

    private Agency agency;

    @Before
    public void setUp(){
        agency = new Agency();
    }

    @Test
    public void shouldFindFromAnyAgent() throws Exception {
       Agent agent1 = agency.createAgent();
       Agent agent2 = agency.createAgent();

        String category = "category1";
        String key = "key1";

        agent1.bearInMind(category,key,"value1");

       assertThat(agency.findFromAnyAgent("category1", "key1", String.class), is("value1"));

    }

    @Test
    public void shouldReturnLatestValueAsFoundFromAnyAgent() throws Exception {
       Agent agent1 = agency.createAgent();
       Agent agent2 = agency.createAgent();

        String category = "category1";
        String key = "key1";

        agent1.bearInMind(category,key,"value1");
        agent2.bearInMind(category,key,"value2");

        assertThat(agency.findFromAnyAgent("category1", "key1", String.class), is("value2"));

    }
}
