package Service;

import Model.Agent;
import Model.TypeAgent;
import Repository.AgentRepository;
import Exception.DataAccessException;
import Exception.BusinessException;

import java.util.List;

public class AgentService {
    private AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public void addAgent(Agent agent) throws DataAccessException,BusinessException {

        if (agent.getTypeAgent() == TypeAgent.RESPONSABLE_DEPARTEMENT) {
            List<Agent> agentinDepartament = agentRepository.getAgentsByDepartement(agent.getDepartement().getIdDepartement());
            boolean responsbaleAlreadyFound = agentinDepartament.stream().anyMatch(a -> a.getTypeAgent() == TypeAgent.RESPONSABLE_DEPARTEMENT);

            if (responsbaleAlreadyFound) {
                throw new BusinessException(" ce departement a deja  un responsable de departement.");
            }
        }
        agentRepository.addAgent(agent);
    }

    public void updateAgent(Agent agent) throws DataAccessException {
        agentRepository.updateAgent(agent);
    }

    public void deleteAgent(int idAgent) throws DataAccessException {
        agentRepository.deleteAgent(idAgent);
    }

    public List<Agent> getAllAgents() throws DataAccessException {
        return agentRepository.getAllAgents();
    }

    public Agent getAgentByEmailAndPassword(String email, String password) throws DataAccessException {
        return agentRepository.getAgentByEmailAndPassword(email, password);
    }
}
