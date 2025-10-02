package DAO.impl;

import DAO.DB.DatabaseConnection;
import DAO.interfaces.IAgentDAO;
import Model.Agent;
import Model.Departement;
import Model.TypeAgent;
import Exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgentDAOImpl implements IAgentDAO {
    private static final Logger logger = Logger.getLogger(AgentDAOImpl.class.getName());


    public void addAgent(Agent agent) {
        logger.info("Adding agent to DB: " + agent.getEmail());

        String sql = "INSERT INTO agent (name, prenom, email, motDePasse, typeAgent, departement_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, agent.getName());
            ps.setString(2, agent.getPrenom());
            ps.setString(3, agent.getEmail());
            ps.setString(4, agent.getMotDePasse());
            ps.setString(5, agent.getTypeAgent().name());
            ps.setInt(6, agent.getDepartement().getIdDepartement());

            ps.executeUpdate();
            logger.info("Agent inserted successfully: " + agent.getEmail());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to add agent: " + agent.getEmail(), e);
            throw new DataAccessException("Failed to add agent", e);
        }
    }

    public Agent getAgentByEmailAndPassword(String email, String password) throws DataAccessException {

        String sql = "SELECT a.*,d.idDepartement,d.nom as departementNom" +
                " FROM agent a JOIN departement d ON" +
                " a.departement_id = d.idDepartement " +
                "WHERE a.email = ? AND a.motDePasse = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Agent agent = new Agent();
                agent.setIdAgent(rs.getInt("idAgent"));
                agent.setName(rs.getString("name"));
                agent.setPrenom(rs.getString("prenom"));
                agent.setEmail(rs.getString("email"));
                agent.setMotDePasse(rs.getString("motDePasse"));
                agent.setTypeAgent(TypeAgent.valueOf(rs.getString("typeAgent")));

                Departement departement = new Departement();
                departement.setIdDepartement(rs.getInt("idDepartement"));
                departement.setNom(rs.getString("departementNom"));
                agent.setDepartement(departement);
                return agent;
            }
            return null;
        } catch (SQLException e) {

            throw new DataAccessException("Failed to fetch agent by email and password", e);
        }

    }

    public List<Agent> getAgentsByDepartement(int departementId) throws DataAccessException {
        List<Agent> agents = new ArrayList<>();
        String sql = "SELECT * FROM agent WHERE departement_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, departementId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Agent agent = new Agent();
                agent.setIdAgent(rs.getInt("idAgent"));
                agent.setName(rs.getString("name"));
                agent.setPrenom(rs.getString("prenom"));
                agent.setEmail(rs.getString("email"));
                agent.setMotDePasse(rs.getString("motDePasse"));
                agent.setTypeAgent(TypeAgent.valueOf(rs.getString("typeAgent")));
                agents.add(agent);
            }
            return agents;
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch agent by departement", e);
        }

    }

    public void updateAgent(Agent agent) throws DataAccessException {
        String sql = "UPDATE agent SET name = ?, prenom = ?, email = ?, motDePasse = ?, typeAgent = ?  WHERE idAgent = ?\n ";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, agent.getName());
            ps.setString(2, agent.getPrenom());
            ps.setString(3, agent.getEmail());
            ps.setString(4, agent.getMotDePasse());
            ps.setString(5, agent.getTypeAgent().name());
            ps.setInt(6, agent.getIdAgent());


            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed  to update agent ", e);
        }
    }

    public void deleteAgent(int idAgent) throws DataAccessException {
        String sql = "DELETE FROM agent WHERE idAgent= ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAgent);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete  agent", e);
        }
    }


    public List<Agent> getAllAgents() throws DataAccessException {
        List<Agent> agents = new ArrayList<>();

        String sql = "SELECT  a.*,d.idDepartement,d.nom as departementNom FROM agent a JOIN departement d ON d.idDepartement=a.departement_id";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Agent agent = new Agent();
                agent.setIdAgent(rs.getInt("idAgent"));
                agent.setName(rs.getString("name"));
                agent.setPrenom(rs.getString("prenom"));
                agent.setEmail(rs.getString("email"));
                agent.setMotDePasse(rs.getString("motDePasse"));
                agent.setTypeAgent(TypeAgent.valueOf(rs.getString("typeAgent")));

//                now departement object
                Departement departement = new Departement();
                departement.setIdDepartement(rs.getInt("idDepartement"));
                departement.setNom(rs.getString("departementNom"));
                agent.setDepartement(departement);

                agents.add(agent);


            }


            return agents;
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch  agents", e);
        }

    }

    public Agent getAgentById(int id) throws SQLException {
        String sql = "SELECT * FROM agent WHERE idAgent=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Agent agent = new Agent();
                agent.setIdAgent(rs.getInt("idAgent"));
                agent.setName(rs.getString("name"));
                agent.setPrenom(rs.getString("prenom"));
                agent.setEmail(rs.getString("email"));
                agent.setMotDePasse(rs.getString("motDePasse"));
                agent.setTypeAgent(TypeAgent.valueOf(rs.getString("typeAgent")));
                int deptId = rs.getInt("departement_id");

                if (!rs.wasNull()) {
                    DepartementDAO departementDAO = new DepartementDAO();
                    Departement departement = departementDAO.getDepartementById(deptId);
                    agent.setDepartement(departement);
                }
                return agent;
            }
        }
        return null;
    }

}
