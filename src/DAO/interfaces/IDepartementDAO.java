package DAO.interfaces;

import Model.Departement;

import java.sql.SQLException;
import java.util.List;

public interface IDepartementDAO {
    void addDepartement(Departement departement)throws SQLException;
    Departement getDepartementById(int id)throws SQLException;
    Departement getDepartementByName(String name)throws SQLException;
    List<Departement> getAllDepartements()throws SQLException;
    void deleteDepartement(int idDepartement)throws SQLException;
    void updateDepartement (Departement departement) throws SQLException;
}
