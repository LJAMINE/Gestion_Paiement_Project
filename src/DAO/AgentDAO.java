package DAO;

import Model.Agent;
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AgentDAO {
    public void addAgent(Agent agent){
        String sql="INSERT INTO agent (name, prenom, email, motDePasse, typeAgent, departement_id) VALUES (?, ?, ?, ?, ?, ?)";

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
}
