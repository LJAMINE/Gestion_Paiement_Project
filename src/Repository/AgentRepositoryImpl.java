package Repository;

import DAO.AgentDAO;
import Model.Agent;

import java.util.List;

public class AgentRepositoryImpl implements AgentRepository {

    private AgentDAO agentDAO = new AgentDAO();


    @Override
    public void addAgent(Agent agent) {
        agentDAO.addAgent(agent);
    }

//    @Override
//    public void updateAgent(Agent agent) {
//        agentDAO.updateAgent(agent);
//    }
//
//    @Override
//    public void deleteAgent(int idAgent) {
//        agentDAO.deleteAgent(idAgent);
//    }
//
//    @Override
//    public Agent findById(int idAgent) {
//        return agentDAO.getAgentById(idAgent);
//    }
//
//    @Override
//    public List<Agent> findAll() {
//        return agentDAO.getAllAgents() ;
//    }
//
//    @Override
//    public List<Agent> findByType(String typeAgent) {
//        return  agentDAO.getAgentsByType(typeAgent);
//    }
}
