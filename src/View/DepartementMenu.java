package View;

import Controller.DepartementController;
import DAO.AgentDAO;
import Model.Agent;
import Model.Departement;
import Model.TypeAgent;
import Repository.DepartementRepositoryImpl;
import Service.DepartementService;

import java.util.Scanner;

public class DepartementMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgentDAO agentDAO = new AgentDAO();

//        login--------------------------------------------------

        System.out.println("se connnecter");
        System.out.println("emaill");
        String email = scanner.nextLine();
        System.out.println("mot de passe ");
        String password = scanner.nextLine();

        Agent currentUser = null;

        try {
            currentUser = agentDAO.getgetAgentByEmailAndPassword(email, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (currentUser == null) {
            System.out.println("Email ou  mot de passe incorrect");
            return;

        }


//        role checking----------------------------------------------

        if (currentUser.getTypeAgent() != TypeAgent.DIRECTEUR) {
            System.out.println("access refuse seul directeur peut creer  un departement .");
            return;
        }


        DepartementController controller = new DepartementController(
                new DepartementService(new DepartementRepositoryImpl())
        );

        System.out.println("  Create Departement (Director only) ===");
        System.out.print("Departement name: ");
        String name = scanner.nextLine();



        Departement departement=new Departement();
        departement.setNom(name);

        controller.addDepartement(departement);

    }
}