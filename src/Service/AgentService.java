package Service;

import Model.Agent;
import Model.TypeAgent;
import Repository.interfaces.AgentRepository;
import Exception.DataAccessException;
import Exception.BusinessException;

import java.util.List;
import java.util.logging.Logger;

public class AgentService {
    private static final Logger logger = Logger.getLogger(AgentService.class.getName());

    private AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public void addAgent(Agent agent) throws DataAccessException,BusinessException {

        logger.info("Attempting to add agent: " + agent.getEmail());

        if (agent.getTypeAgent() == TypeAgent.RESPONSABLE_DEPARTEMENT) {
            List<Agent> agentinDepartament = agentRepository.getAgentsByDepartement(agent.getDepartement().getIdDepartement());
            boolean responsbaleAlreadyFound = agentinDepartament.stream().anyMatch(a -> a.getTypeAgent() == TypeAgent.RESPONSABLE_DEPARTEMENT);

            if (responsbaleAlreadyFound) {
                logger.warning("Failed to add agent: department already has a responsable");
                throw new BusinessException("   departement a deja  un responsable de departement.");
            }
        }
        agentRepository.addAgent(agent);
        logger.info("agent  successfully added: " + agent.getEmail());

    }

    public void updateAgent(Agent agent) throws DataAccessException {
        logger.info("Updating agent with id: " + agent.getIdAgent());
        agentRepository.updateAgent(agent);
        logger.info("agent updated with id "+agent.getIdAgent());
    }

    public void deleteAgent(int idAgent) throws DataAccessException {
        logger.info("deleting  agent with id: " + idAgent);
        agentRepository.deleteAgent(idAgent);
        logger.info("Agent deleted with id: " + idAgent);

    }

    public List<Agent> getAllAgents() throws DataAccessException {
        logger.info("Fetching all agents");
        return agentRepository.getAllAgents();
    }

    public Agent getAgentByEmailAndPassword(String email, String password) throws DataAccessException {
        logger.info("Fetching agent by email: " + email);
        return agentRepository.getAgentByEmailAndPassword(email, password);
    }
}
