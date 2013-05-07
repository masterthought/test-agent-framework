package net.masterthought.concurrent;

import groovyx.gpars.actor.DynamicDispatchActor;
import net.masterthought.core.Agent;
import net.masterthought.core.Mission;

import static net.masterthought.Utils.requires;

public class ConcurrentAgent extends Agent {

    final AgentActor agentActor = new AgentActor();

    public ConcurrentAgent() {
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

    public static void main(String[] args) throws Exception {
        final ConcurrentAgent concurrentAgent = new ConcurrentAgent();
        concurrentAgent.doThe(new Mission() {
            @Override
            public void accomplish(Agent agent) {
                System.out.println("I did it!");
            }
        });
    }

    private class AgentActor extends DynamicDispatchActor {


        public void onMessage(final Object msg) {
            if (msg instanceof Mission[]) {
                new Agent().doThe((Mission[]) msg);
                System.out.println("I did the missions");
            }
            replyIfExists("Received set of Missions.");
        }

    }

}

