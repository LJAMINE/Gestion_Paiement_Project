package Service;

import Model.Agent;
import Model.TypeAgent;
import Repository.AgentRepository;

import java.util.List;

public class AgentService {
    private AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public void addAgent(Agent agent) throws Exception {

        if (agent.getTypeAgent() == TypeAgent.RESPONSABLE_DEPARTEMENT) {
            List<Agent> agentinDepartament = agentRepository.getAgentsByDepartement(agent.getDepartement().getIdDepartement());
            boolean responsbaleAlreadyFound = agentinDepartament.stream().anyMatch(a -> a.getTypeAgent() == TypeAgent.RESPONSABLE_DEPARTEMENT);

            if (responsbaleAlreadyFound) {
                throw new Exception("ce departement deja has responsable de departement ");
            }
        }
        agentRepository.addAgent(agent);
    }

    public void updateAgent(Agent agent) throws Exception {
        agentRepository.updateAgent(agent);
    }

    public void deleteAgent(int idAgent) throws Exception {
        agentRepository.deleteAgent(idAgent);
    }

    public List<Agent> getAllAgents() throws Exception {
        return agentRepository.getAllAgents();
    }
}
