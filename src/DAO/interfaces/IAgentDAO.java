package DAO.interfaces;

import Model.Agent;
import Exception.DataAccessException;

import java.sql.SQLException;
import java.util.List;


public interface IAgentDAO {

    void addAgent(Agent agent);
    Agent getAgentByEmailAndPassword(String email,String password) throws DataAccessException;
    List<Agent> getAgentsByDepartement(int departementId) throws DataAccessException;
    void updateAgent(Agent agent) throws DataAccessException;
    void deleteAgent(int idAgent) throws DataAccessException;
    List<Agent> getAllAgents() throws DataAccessException;
    Agent getAgentById(int id) throws SQLException;
}
