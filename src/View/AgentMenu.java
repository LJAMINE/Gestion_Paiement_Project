package View;

import Controller.AgentController;
import Model.Agent;
import Model.Departement;
import Model.TypeAgent;
import Repository.AgentRepositoryImpl;
import Repository.DepartementRepositoryImpl;
import Service.AgentService;
import Service.DepartementService;

import java.util.List;
import java.util.Scanner;

public class AgentMenu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        AgentController controller = new AgentController(new AgentService(new AgentRepositoryImpl()));
        DepartementService departementService = new DepartementService(new DepartementRepositoryImpl());

        boolean agentRun = true;

        while (agentRun) {

            System.out.println("\nresponsable Menu :");
            System.out.println("1. create");
            System.out.println("2. delete");
            System.out.println("3. update agent");
            System.out.println("4. get All agent");
            System.out.println("0. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("  Create agents (ouvrier ,stagiaire, responsable ) ===");
                    System.out.println("Enter Agent name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter Agent prenom:");
                    String prenom = scanner.nextLine();

                    System.out.println("Enter Agent email:");
                    String email = scanner.nextLine();

                    System.out.println("Enter Agent motDePasse:");
                    String motDePasse = scanner.nextLine();

                    System.out.println("Enter Agent type (OUVRIER, RESPONSABLE_DEPARTEMENT, DIRECTEUR, STAGIAIRE):");
                    String typeInput = scanner.nextLine();
                    TypeAgent typeAgent = TypeAgent.valueOf(typeInput);


                    System.out.println("Enter Agent department name:");
                    String departementName = scanner.nextLine();
                    Departement departement;

                    try {
                        departement = departementService.getDepartementByName(departementName);
                        if (departement == null) {
                            System.out.println("Department not found");
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("error  finding department");
                        return;
                    }
                    Agent agent = new Agent();

                    agent.setName(name);
                    agent.setPrenom(prenom);
                    agent.setEmail(email);
                    agent.setMotDePasse(motDePasse);
                    agent.setTypeAgent(typeAgent);
                    agent.setDepartement(departement);

                    controller.addAgent(agent);

                    break;
                case 2:
                    System.out.print("delete agent  : ");

                    break;
                case 3:
                    System.out.println("3. update Agent");

                    System.out.println("id to be updated ");
                    int idupdated = scanner.nextInt();

                    scanner.nextLine();

                    System.out.println("nom to be updated ");
                    String newNom = scanner.nextLine();

                    System.out.println("prenom to be updated ");
                    String newPrenom = scanner.nextLine();

                    System.out.println("mot de pass  to be updated ");
                    String newPassword  = scanner.nextLine();

                    System.out.println("email to be updated ");
                    String newEmail = scanner.nextLine();

                    System.out.println("Type to be updated ");
                    String type = scanner.nextLine();
                    TypeAgent typeAgents = TypeAgent.valueOf(type);

                    System.out.println("Enter new department name:");
                    String departementNames = scanner.nextLine();
                    Departement departements = departementService.getDepartementByName(departementNames);
                    if (departements == null) {
                        System.out.println("Department not found");
                        return;
                    }


                    Agent agentUpdated = new Agent();

                    agentUpdated.setIdAgent(idupdated);
                    agentUpdated.setName(newNom);
                    agentUpdated.setPrenom(newPrenom);
                    agentUpdated.setEmail(newEmail);
                    agentUpdated.setMotDePasse(newPassword);
                    agentUpdated.setTypeAgent(typeAgents);
                    agentUpdated.setDepartement(departements);




                    controller.updateAgent(agentUpdated);

                    break;
                case 4:
                    System.out.println("4. get All Agent");

                    break;
                case 0:
                    agentRun = false;
                    break;

                default:
                    System.out.println("not found ");
            }

        }



    }
}
