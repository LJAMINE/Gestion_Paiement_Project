package DAO;

import Model.Departement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartementDAO {
    public void addDepartement(Departement departement) throws SQLException {

        String sql = "INSERT INTO departement (nom) VALUES (?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, departement.getNom());

            ps.executeUpdate();
        }

    }

    public Departement getDepartementById(int id) throws SQLException {
        String sql = "SELECT * FROM departement WHERE idDepartement=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Departement dpt = new Departement(rs.getInt("idDepartement"), rs.getString("nom"));
                return dpt;
            }
        }

        return null;

    }

    public List<Departement> getAllDepartements() throws SQLException {
        List<Departement> list = new ArrayList<>();
        String sql = "SELECT * FROM departement";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Departement dpt = new Departement(rs.getInt("idDepartement"), rs.getString("nom"));
                list.add(dpt);
            }
        }
        return list;
    }


    public void deleteDepartement(int idDepartement) throws SQLException {
        String sql = "DELETE FROM departement WHERE idDepartement= ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDepartement);
            ps.executeUpdate();
        }
        }

    }