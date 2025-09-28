package View;

import Controller.AgentController;
import Controller.DepartementController;
import DAO.AgentDAO;
import Model.Agent;
import Model.Departement;
import Model.TypeAgent;
import Repository.AgentRepositoryImpl;
import Repository.DepartementRepositoryImpl;
import Service.AgentService;
import Service.DepartementService;

import java.util.List;
import java.util.Scanner;

public class DirectorMenu {
    public static void launch(Agent currentUser) throws Exception {
        Scanner scanner = new Scanner(System.in);
        AgentDAO agentDAO = new AgentDAO();

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
            System.out.println("5. create/Assign Responsible for a Department");

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
                case 5:
                    System.out.print("Enter department name to create and assign a responsable: ");
                    String departementName=scanner.nextLine();
                    Departement departement12=controller.getDepartementByName(departementName);

                    if (departement12==null){
                        System.out.println("departement not found");
                        break;
                    }
                    List<Agent> agentsInDepartement=agentDAO.getAgentsByDepartement(departement12.getIdDepartement());
                    boolean contientResponsable=agentsInDepartement.stream().anyMatch(a->a.getTypeAgent()==TypeAgent.RESPONSABLE_DEPARTEMENT);
                    if (contientResponsable){
                        System.out.println("this departement has already a responsable");
                        break;
                    }
                    System.out.print("Responsable name: ");
                    String respName = scanner.nextLine();
                    System.out.print("Responsable prenom: ");
                    String respPrenom = scanner.nextLine();
                    System.out.print("Responsable email: ");
                    String respEmail = scanner.nextLine();
                    System.out.print("Responsable password: ");
                    String respPassword = scanner.nextLine();

                    Agent responsable = new Agent();
                    responsable.setName(respName);
                    responsable.setPrenom(respPrenom);
                    responsable.setEmail(respEmail);
                    responsable.setMotDePasse(respPassword);
                    responsable.setTypeAgent(TypeAgent.RESPONSABLE_DEPARTEMENT);
                    responsable.setDepartement(departement12);


                    AgentController agentController=new AgentController(new AgentService(new AgentRepositoryImpl()));

                    try{
                        agentController.addAgent(responsable);
                        System.out.println("responsbale added succefully");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
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