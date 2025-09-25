package DAO;

import Model.Agent;
import Model.TypeAgent;

import java.sql.*;

public class AgentDAO {



    public void addAgent(Agent agent) {
        String sql = "INSERT INTO agent (name, prenom, email, motDePasse, typeAgent, departement_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, agent.getName());
            ps.setString(2, agent.getPrenom());
            ps.setString(3, agent.getEmail());
            ps.setString(4, agent.getMotDePasse());
            ps.setString(5, agent.getTypeAgent().name());
            if (agent.getDepartement() != null) {
                ps.setInt(6, agent.getDepartement().getIdDepartement());
            } else {
                ps.setNull(6, Types.INTEGER);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Or handle with a custom exception
        }
    }

    public Agent getgetAgentByEmailAndPassword(String email, String password) throws SQLException {

        String sql = "SELECT * FROM agent WHERE email = ? AND motDePasse = ?";
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
                return agent;
            }
        }
        return null;

    }
}
