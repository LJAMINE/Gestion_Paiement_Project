package Repository;

import DAO.DepartementDAO;
import Model.Departement;

import java.util.List;

public class DepartementRepositoryImpl implements DepartementRepository {

    private DepartementDAO departementDAO =new DepartementDAO();


    @Override
    public void addDepartement(Departement departement) throws Exception {
        departementDAO.addDepartement(departement);
    }

    @Override
    public Departement getDepartementById(int id) throws Exception {
        return departementDAO.getDepartementById(id);
    }

    @Override
    public List<Departement> getAllDepartements() throws Exception {
        return departementDAO.getAllDepartements();
    }
}
