package net.masterthought;

import net.masterthought.core.Agent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Agency {

    private Queue<Agent> agents = new ConcurrentLinkedDeque<Agent>();

    public Agent createAgent() {
        Agent agent = new Agent();
        agents.add(agent);
        return agent;
    }

    public <CATEGORY, KEY, VALUE> VALUE findFromAnyAgent(CATEGORY category, KEY key, Class<VALUE> clazz) {
        for (Agent agent : agents) {
            return agent.recallFromMemory(category, key, clazz);
        }
        return null;
    }

}
