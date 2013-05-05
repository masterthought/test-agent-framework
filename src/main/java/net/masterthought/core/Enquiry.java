package net.masterthought.core;

public class Enquiry {

      public void confirm(Agent agent, Objective... objectives){
          for(Objective objective : objectives){
              objective.completeAndConfirm(agent);
          }
      };
}
