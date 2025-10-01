package Repository.interfaces;

import Model.Agent;
import Exception.DataAccessException;
import java.util.List;

public interface AgentRepository {

    void addAgent(Agent agent);

    List<Agent> getAgentsByDepartement(int departementId) throws DataAccessException;

    void updateAgent(Agent agent) throws DataAccessException;

    void deleteAgent(int idAgent) throws DataAccessException;

    List<Agent> getAllAgents() throws DataAccessException;
    Agent getAgentByEmailAndPassword(String email, String password) throws DataAccessException;


}
