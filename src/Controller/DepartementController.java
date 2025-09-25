package Controller;

import Model.Agent;
import Model.Departement;
import Service.DepartementService;

public class DepartementController {
    private DepartementService departementService;

    public DepartementController(DepartementService departementService){
        this.departementService=departementService;
    }

    public void addDepartement(Departement departement){
        try {
            departementService.addDepartement(departement);
            System.out.println("Departement added successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
