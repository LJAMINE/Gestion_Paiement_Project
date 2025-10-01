package Repository.impl;

import DAO.interfaces.IAgentDAO;
import Exception.DataAccessException;
import Model.Agent;
import Repository.interfaces.AgentRepository;

import java.util.List;

public class AgentRepositoryImpl implements AgentRepository {

    //    private AgentDAO agentDAO = new AgentDAO();
    private final IAgentDAO agentDAO;

    public AgentRepositoryImpl(IAgentDAO agentDAO) {
        this.agentDAO = agentDAO;
    }


    @Override
    public void addAgent(Agent agent) {
        agentDAO.addAgent(agent);
    }

    @Override
    public List<Agent> getAgentsByDepartement(int departementId) throws DataAccessException {
        return agentDAO.getAgentsByDepartement(departementId);
    }

    @Override
    public void updateAgent(Agent agent) throws DataAccessException {
        agentDAO.updateAgent(agent);
    }

    @Override
    public void deleteAgent(int idAgent) throws DataAccessException {
        agentDAO.deleteAgent(idAgent);
    }

    @Override
    public List<Agent> getAllAgents() throws DataAccessException {
        return agentDAO.getAllAgents();
    }

    @Override
    public Agent getAgentByEmailAndPassword(String email, String password) throws DataAccessException {
        return agentDAO.getAgentByEmailAndPassword(email, password);
    }

//
//    @Override
//    public Agent findById(int idAgent) {
//        return agentDAO.getAgentById(idAgent);
//    }
//

//
//    @Override
//    public List<Agent> findByType(String typeAgent) {
//        return  agentDAO.getAgentsByType(typeAgent);
//    }
}
