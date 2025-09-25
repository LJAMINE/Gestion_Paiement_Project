package View;

import Controller.DepartementController;
import DAO.AgentDAO;
import Model.Agent;
import Model.Departement;
import Model.TypeAgent;
import Repository.DepartementRepositoryImpl;
import Service.DepartementService;

import java.util.List;
import java.util.Scanner;

public class DepartementMenu {
    public static void main(String[] args) throws Exception {
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

        boolean directorRun = true;

        while (directorRun) {

            System.out.println("\ndepartement Menu :");
            System.out.println("1. create");
            System.out.println("2. delete");
            System.out.println("3. update Departement");
            System.out.println("4. get All Departements");
            System.out.println("0. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("  Create Departement (Director only) ===");
                    System.out.print("Departement name: ");
                    String name = scanner.nextLine();

                    Departement departement = new Departement();
                    departement.setNom(name);

                    controller.addDepartement(departement);
                    break;
                case 2:
                    System.out.print("delete Departement  : ");
                    System.out.print("Departement id: ");
                    int id = scanner.nextInt();
                    controller.deleteDepartement(id);
                    break;
                case 3:
                    System.out.println("3. updateDepartement");

                    System.out.println("id to be updated ");
                    int idupdated = scanner.nextInt();

                    scanner.nextLine();

                    System.out.println("nom to be updated ");
                    String newNom = scanner.nextLine();

                    Departement departementUpdated = new Departement();
                    departementUpdated.setIdDepartement(idupdated);
                    departementUpdated.setNom(newNom);

                    controller.updateDepartement(departementUpdated);


                    break;
                case 4:
                    System.out.println("4. get All Departements");
                    List<Departement> departementss = controller.getAllDepartements();
                    for (Departement dep : departementss) {
                        System.out.println(dep.getIdDepartement() + "  " + dep.getNom());
                    }
                    break;
                case 0:
                    directorRun = false;
                    break;

                default:
                    System.out.println("not found ");
            }

        }


    }
}