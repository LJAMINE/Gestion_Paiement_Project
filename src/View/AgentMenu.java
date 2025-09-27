package View;

import Controller.AgentController;
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

public class AgentMenu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        AgentController controller = new AgentController(new AgentService(new AgentRepositoryImpl()));
        DepartementService departementService = new DepartementService(new DepartementRepositoryImpl());


        //        login--------------------------------------------------

        System.out.println("se connnecter");
        System.out.println("emaill");
        String emailConnecte = scanner.nextLine();
        System.out.println("mot de passe ");
        String passwordConnecte = scanner.nextLine();

        Agent currentUser = null;
        AgentDAO agentDAO = new AgentDAO();


        try {
            currentUser = agentDAO.getAgentByEmailAndPassword(emailConnecte,passwordConnecte);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (currentUser == null) {
            System.out.println("Email ou  mot de passe incorrect");
            return;

        }

        // Only RESPONSABLE_DEPARTEMENT can access the agent CRUD menu

        if (currentUser.getTypeAgent()!=TypeAgent.RESPONSABLE_DEPARTEMENT){
            System.out.println("pas permission pour entrer ce menu");
        }


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
