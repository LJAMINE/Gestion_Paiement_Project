package DAO;

import Model.Departement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartementDAO {
    public void addDepartement(Departement departement ) throws SQLException {

        String sql="INSERT INTO departement (nom, responsable_id) VALUES (?,?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, departement.getNom());
            if (departement.getResponsable() != null) {
                ps.setInt(2, departement.getResponsable().getIdAgent());
            } else {
                ps.setNull(2, Types.INTEGER);
            }
            ps.executeUpdate();
        }

    }

    public Departement getDepartementById(int id)throws SQLException{
        String sql="SELECT * FROM departement WHERE idDepartement=?";
        try (Connection conn =DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Departement dpt =new Departement(rs.getInt("idDepartement"),rs.getString("nom"),null);
            return  dpt;
            }
        }

        return null;

    }

    public List<Departement> getAllDepartements() throws SQLException {
        List<Departement> list = new ArrayList<>();
        String sql = "SELECT * FROM Departement";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Departement dpt = new Departement(rs.getInt("idDepartement"), rs.getString("nom"), null);
                list.add(dpt);
            }
        }
        return list;
    }

}
