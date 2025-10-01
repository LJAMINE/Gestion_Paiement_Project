package DAO.impl;

import DAO.DB.DatabaseConnection;
import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Exception.DataAccessException;


public class PaiementDAO {

    public void addPaiement(Paiement paiement) throws DataAccessException {
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
//            System.out.println("Rows inserted: " + rows);
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de l'ajout du paiement.", e);

        }
    }

    public List<Paiement> getPaiementByAgent(int idAgent) {
        String sql = "SELECT * FROM paiement WHERE agent_id= ?";
        List<Paiement> paiements = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAgent);

            ResultSet rs = ps.executeQuery();

            while  (rs.next()) {
                Paiement paiement = new Paiement();
                paiement.setIdPaiement(rs.getInt("idPaiement"));
                paiement.setTypePaiement(TypePaiement.valueOf(rs.getString("typePaiement")));
                paiement.setMontant(rs.getDouble("montant"));
                paiement.setDate(rs.getDate("date").toLocalDate());
                paiement.setMotif(rs.getString("motif"));
                Object condvali = rs.getObject("conditionValidee");
                if (condvali != null) {
                    paiement.setConditionValidee(rs.getBoolean("conditionValidee"));
                }
            paiements.add(paiement);
            }

            return paiements;
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch  ", e);
        }


     }
}
