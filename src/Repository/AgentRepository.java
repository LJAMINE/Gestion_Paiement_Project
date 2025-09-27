package Repository;

import Model.Agent;

import java.util.List;

public interface AgentRepository {

    void addAgent(Agent agent);

    List<Agent> getAgentsByDepartement(int departementId) throws Exception;

    void updateAgent(Agent agent) throws Exception;

    void deleteAgent(int idAgent) throws Exception;

    List<Agent> getAllAgents() throws Exception;


}
