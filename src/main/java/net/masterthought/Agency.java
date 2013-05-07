package net.masterthought;

import net.masterthought.core.Agent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        List<Agent> l  = new ArrayList<Agent>(agents);
        Collections.sort(l);
        for (Agent agent : l) {
             VALUE fromMemory = agent.recallFromMemory(category, key, clazz);
             if(fromMemory != null)
            return fromMemory;
        }
        return null;
    }

}
