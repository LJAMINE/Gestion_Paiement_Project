package Repository;

import DAO.AgentDAO;
import Model.Agent;

import java.sql.SQLException;
import java.util.List;

public class AgentRepositoryImpl implements AgentRepository {

    private AgentDAO agentDAO = new AgentDAO();


    @Override
    public void addAgent(Agent agent) {
        agentDAO.addAgent(agent);
    }

    @Override
    public List<Agent> getAgentsByDepartement(int departementId)throws Exception {
        return agentDAO.getAgentsByDepartement(departementId);
    }

    @Override
    public void updateAgent(Agent agent)throws Exception {
        agentDAO.updateAgent(agent);
    }

    @Override
    public void deleteAgent(int idAgent)throws Exception {
        agentDAO.deleteAgent(idAgent);
    }

    @Override
    public List<Agent> getAllAgents() throws Exception{
        return  agentDAO.getAllAgents();
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
