package DAO;

import Model.Agent;
import Model.TypeAgent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            ps.setInt(6, agent.getDepartement().getIdDepartement());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Agent> getAgentsByDepartement(int departementId) throws SQLException {
        List<Agent> agents = new ArrayList<>();
        String sql = "SELECT * FROM agent WHERE departement_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))

        {
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
        }
        return agents;

    }

    public  void  updateAgent (Agent agent)throws SQLException{
        String sql="UPDATE agent SET name = ?, prenom = ?, email = ?, motDePasse = ?, typeAgent = ?, departement_id = ? WHERE idAgent = ?\n ";
        try  (Connection conn = DatabaseConnection.getInstance().getConnection() ;
        PreparedStatement ps=conn.prepareStatement(sql)
        ){
            ps.setString(1,agent.getName());
            ps.setString(2,agent.getPrenom());
            ps.setString(3,agent.getEmail());
            ps.setString(4,agent.getMotDePasse());
            ps.setString(5,agent.getTypeAgent().name());
            ps.setInt(6, agent.getDepartement().getIdDepartement());
            ps.setInt(7, agent.getIdAgent());


            ps.executeUpdate();
        }
    }
}
