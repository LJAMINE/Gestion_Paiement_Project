package Controller;

import Model.Agent;
import Service.AgentService;

public class AgentController {
    private AgentService agentService;

    public AgentController (AgentService agentService){
        this.agentService=agentService;
    }

    public void addAgent(Agent agent){
        try {
            agentService.addAgent(agent);
            System.out.println("agent added successfully");
        } catch (Exception e) {
            System.out.println("error in adding agent "+ e.getMessage());        }
    }
}