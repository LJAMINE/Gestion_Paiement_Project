package Repository;

import Model.Departement;

import java.util.List;

public interface DepartementRepository {

    void addDepartement(Departement departement) throws Exception;

    Departement getDepartementById(int id) throws Exception;

    List<Departement> getAllDepartements() throws Exception;
}
