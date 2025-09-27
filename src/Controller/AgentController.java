package Controller;

import Model.Agent;
import Model.Departement;
import Service.AgentService;

import java.util.List;

public class AgentController {
    private AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    public void addAgent(Agent agent) {
        try {
            agentService.addAgent(agent);
            System.out.println("agent added successfully");
        } catch (Exception e) {
            System.out.println("error in adding agent " + e.getMessage());
        }
    }

    public void updateAgent(Agent agent) {

        try {
            agentService.updateAgent(agent);
            System.out.println("updated successfully");

        } catch (Exception e) {
            System.out.println("updated issue " + e.getMessage());
        }
    }

    public void deleteAgent(int idAgent) {
        try {
            agentService.deleteAgent(idAgent);
            System.out.println("deleted successfully");

        } catch (Exception e) {
            System.out.println("deleted issue " + e.getMessage());
        }
    }


    public List<Agent> getAllAgents(){
        try{
          return   agentService.getAllAgents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}


