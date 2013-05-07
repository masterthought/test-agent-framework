package net.masterthought.concurrent;

import groovyx.gpars.actor.DynamicDispatchActor;
import net.masterthought.Agency;
import net.masterthought.core.Agent;
import net.masterthought.core.Mission;

import static net.masterthought.Utils.requires;

public class ConcurrentAgent extends Agent {

    final AgentActor agentActor;

    public ConcurrentAgent() {
        agentActor = new AgentActor(Agency.clone(this));
        agentActor.start();
    }


    public void doThe(Mission... missions) {
        requires(missions);
        try {
            agentActor.sendAndWait(missions);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private class AgentActor extends DynamicDispatchActor {

        final Agent agent;

        public AgentActor(Agent agent){
            this.agent = agent;
        }

        public void onMessage(final Object msg) {
            if (msg instanceof Mission[]) {
                agent.doThe((Mission[]) msg);
                System.out.println("I did the missions");
            }
            replyIfExists("Received set of Missions.");
        }

    }

}

