package View;

import Controller.AgentController;
import Model.Agent;
import Model.TypeAgent;

import java.util.List;
import java.util.Scanner;

public class ResponsableMenu {

    public static void launch(Agent currentUser, AgentController controller)throws Exception{
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("  Create agents (ouvrier ,stagiaire ) ===");
                    System.out.println("Enter Agent name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter Agent prenom:");
                    String prenom = scanner.nextLine();

                    System.out.println("Enter Agent email:");
                    String email = scanner.nextLine();

                    System.out.println("Enter Agent motDePasse:");
                    String motDePasse = scanner.nextLine();

                    System.out.println("Enter Agent type (OUVRIER, STAGIAIRE):");
                    String typeInput = scanner.nextLine();
                    TypeAgent typeAgent = TypeAgent.valueOf(typeInput);


                    System.out.println("-------------------");
                    System.out.println(currentUser);
                    // Only allow OUVRIER or STAGIAIRE

                    if (typeAgent == TypeAgent.RESPONSABLE_DEPARTEMENT || typeAgent == TypeAgent.DIRECTEUR) {
                        System.out.println("you can just create un  ouvrier ou un stagiaire.");
                        break;
                    }
                    Agent agent = new Agent();

                    agent.setName(name);
                    agent.setPrenom(prenom);
                    agent.setEmail(email);
                    agent.setMotDePasse(motDePasse);
                    agent.setTypeAgent(typeAgent);
                    agent.setDepartement(currentUser.getDepartement());

                    controller.addAgent(agent);

                    break;
                case 2:
                    System.out.println("id of agent to be deleted ");
                    int id=scanner.nextInt();
                    controller.deleteAgent(id);
                    break;
                case 3:
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

                    System.out.println("Nouveau type (OUVRIER, STAGIAIRE) ");
                    String type = scanner.nextLine();
                    TypeAgent typeAgents = TypeAgent.valueOf(type);

                    if (typeAgents == TypeAgent.RESPONSABLE_DEPARTEMENT || typeAgents == TypeAgent.DIRECTEUR) {
                        System.out.println("you can update just to  ouvrier ou stagiaire.");
                        break;
                    }


                    Agent agentUpdated = new Agent();

                    agentUpdated.setIdAgent(idupdated);
                    agentUpdated.setName(newNom);
                    agentUpdated.setPrenom(newPrenom);
                    agentUpdated.setEmail(newEmail);
                    agentUpdated.setMotDePasse(newPassword);
                    agentUpdated.setTypeAgent(typeAgents);
                    agentUpdated.setDepartement(currentUser.getDepartement());





                    controller.updateAgent(agentUpdated);

                    break;
                case 4:
                    List<Agent> list=controller.getAllAgents();


                    for (Agent a : list) {
                        if (a.getDepartement().getIdDepartement()==currentUser.getDepartement().getIdDepartement()){
                            System.out.println(
                                    "ID: " + a.getIdAgent() +
                                            ", Nom: " + a.getName() +
                                            ", Prénom: " + a.getPrenom() +
                                            ", Email: " + a.getEmail() +
                                            ", Type: " + a.getTypeAgent() +
                                            ", Département: " + a.getDepartement().getNom());
                        }
                    }

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
