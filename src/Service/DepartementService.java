package Service;

import Model.Agent;
import Model.Departement;
import Model.TypeAgent;
import Repository.DepartementRepository;

import java.util.List;

public class DepartementService {
    private DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public void addDepartement(Departement departement, Agent currentUser) throws Exception {
        if (currentUser.getTypeAgent() != TypeAgent.DIRECTEUR) {
            throw new Exception("Only Directors can create department");

        }

        departementRepository.addDepartement(departement);
    }

    public Departement getDepartementById(int id) throws Exception {
        return departementRepository.getDepartementById(id);
    }

    public List<Departement> getAllDepartements() throws Exception {
        return departementRepository.getAllDepartements();
    }
}
