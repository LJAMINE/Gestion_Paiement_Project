package DAO;

import Model.Paiement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaiementDAO {

    public void addPaiement(Paiement paiement) throws SQLException {
        String sql = "INSERT INTO paiement (typePaiement, montant, date, motif, conditionValidee, agent_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paiement.getTypePaiement().name());
            ps.setDouble(2, paiement.getMontant());
            ps.setDate(3, java.sql.Date.valueOf(paiement.getDate()));
            ps.setString(4, paiement.getMotif());
            ps.setBoolean(5, paiement.isConditionValidee());
            ps.setInt(6, paiement.getAgent().getIdAgent());

            int rows = ps.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
