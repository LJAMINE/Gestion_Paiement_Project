package Service;

import Model.Departement;
import Repository.interfaces.DepartementRepository;

import java.util.List;

public class DepartementService {
    private DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public void addDepartement(Departement departement) throws Exception {
        departementRepository.addDepartement(departement);
    }

    public Departement getDepartementById(int id) throws Exception {
        return departementRepository.getDepartementById(id);
    }

    public Departement getDepartementByName(String name) throws Exception {
        return departementRepository.getDepartementByName(name);
    }


    public List<Departement> getAllDepartements() throws Exception {
        return departementRepository.getAllDepartements();
    }

    public void deleteDepartement(int idDepartement) throws Exception {
        departementRepository.deleteDepartement(idDepartement);
    }

    public void updateDepartement(Departement departement) throws Exception {
        departementRepository.updateDepartement(departement);
    }


}
