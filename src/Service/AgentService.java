package Service;

import Model.Agent;
import Repository.AgentRepository;

public class AgentService {
    private AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository){
        this.agentRepository=agentRepository;
    }

    public void addAgent(Agent agent){
        agentRepository.addAgent(agent);
    }
}
