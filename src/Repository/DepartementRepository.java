package Repository;

import Model.Departement;

import java.util.List;

public interface DepartementRepository {

    void addDepartement(Departement departement) throws Exception;

    Departement getDepartementById(int id) throws Exception;

    Departement getDepartementByName(String name) throws Exception;


    List<Departement> getAllDepartements() throws Exception;

    void deleteDepartement(int idDepartement)throws  Exception;

    void updateDepartement(Departement departement)throws Exception;
}
