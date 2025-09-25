package Controller;

import Model.Agent;
import Model.Departement;
import Service.DepartementService;

import java.util.ArrayList;
import java.util.List;

public class DepartementController {
    private DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    public void addDepartement(Departement departement) {
        try {
            departementService.addDepartement(departement);
            System.out.println("Departement added successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteDepartement(int idDepartement) throws Exception {
        try {
            departementService.deleteDepartement(idDepartement);
            System.out.println("Departement deleted successfully");
        } catch (Exception e) {
            System.out.println("Error deleting departement: " + e.getMessage());
        }
    }

    public List<Departement> getAllDepartements() {
        try {
            return departementService.getAllDepartements();
        } catch (Exception e) {
            System.out.println("error in getAllDepartements ");
            return new ArrayList<>();
        }
    }

    public void updateDepartement(Departement departement) {
        try {
            departementService.updateDepartement(departement);
            System.out.println("updated successfully");
        } catch (Exception e) {
            System.out.println("error in update");
        }
    }


}
