package View;

import Controller.AgentController;
import Controller.PaiementController;
import DAO.impl.AgentDAOImpl;
import Model.Agent;
import Model.Paiement;
import Model.TypeAgent;
import Model.TypePaiement;
import Repository.impl.PaiementRepositoryImpl;
import Service.PaiementService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ResponsableMenu {

    public static void launch(Agent currentUser, AgentController controller)throws Exception{
        Scanner scanner = new Scanner(System.in);
        PaiementController paiementController =  new PaiementController(new PaiementService(new PaiementRepositoryImpl()));
        boolean agentRun = true;

        AgentDAOImpl agentDAO = new AgentDAOImpl();
        while (agentRun) {

            System.out.println("\nresponsable Menu :");
            System.out.println("1. create");
            System.out.println("2. delete");
            System.out.println("3. update agent");
            System.out.println("4. get All agent");
            System.out.println("5. add paiement to an agent ");
            System.out.println("6. Afficher la moyenne des paiements pour agent");

            System.out.println("0. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("  Create agents (ouvrier ,stagiaire ) ===");
                    System.out.println("Enter Agent name:");
                    String name = scanner.nextLine();

                    if (name==null || name.trim().isEmpty()){
                        System.out.println("pas valide");
                        return;
                    }

                    System.out.println("Enter Agent prenom:");
                    String prenom = scanner.nextLine();

                    if (prenom==null || prenom.trim().isEmpty()){
                        System.out.println("pas valide");
                        return;
                    }

                    System.out.println("Enter Agent email:");
                    String email = scanner.nextLine();

                    if (email==null || email.trim().isEmpty()){
                        System.out.println("pas valide");
                        return;
                    }

                    System.out.println("Enter Agent motDePasse:");
                    String motDePasse = scanner.nextLine();


                    if (motDePasse==null || motDePasse.trim().isEmpty()){
                        System.out.println("pas valide");
                        return;
                    }

                    System.out.println("Enter Agent type (OUVRIER, STAGIAIRE):");
                    String typeInput = scanner.nextLine();
//                    TypeAgent typeAgent = TypeAgent.valueOf(typeInput);

                    TypeAgent typeAgent = null;
                    try {
                        typeAgent = TypeAgent.valueOf(typeInput.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Type d’agent invalide !");
                        return;
                    }


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
                case 5:
                    System.out.println("id of agent ");
                    int idofAgent=scanner.nextInt();
                    scanner.nextLine();

                    Agent agent12=agentDAO.getAgentById(idofAgent);

                    if (agent12 ==null){
                        System.out.println("not found ");
                        break;
                    }

                    System.out.print("Type de paiement (SALAIRE, PRIME, BONUS, INDEMNITE): ");
                    String typePaiemnt=scanner.nextLine();
                    TypePaiement typePaiement ;

                    try {
                        typePaiement = TypePaiement.valueOf(typePaiemnt.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Type de paiement invalide.");
                        break;
                    }
                    System.out.print("montant ");
                    Double montant=scanner.nextDouble();
                    scanner.nextLine();

                    try{
                          if (montant<=0){
                              System.out.println("montant doit etre positif");
                          }
                    } catch (NumberFormatException  e) {
                        System.out.println("Format de montant invalide.");
                        break;
                    }

                    LocalDate date = LocalDate.now();

                    System.out.print("motif ");
                    String motif = scanner.nextLine();

                    boolean conditionValidee = true;

                    Paiement paiement = new Paiement();

                    if (typePaiement == TypePaiement.BONUS || typePaiement == TypePaiement.INDEMNITE) {
                        System.out.print("Condition vallides => O/N):  ");
                        conditionValidee = scanner.nextLine().equalsIgnoreCase("O");
                    } else {

                         conditionValidee = false;
                    }
                    paiement.setConditionValidee(conditionValidee);

                    paiement.setTypePaiement(typePaiement);
                    paiement.setMontant(montant);
                    paiement.setDate(date);
                    paiement.setMotif(motif);
                    paiement.setAgent(agent12);



                    try {

                        System.out.println("''''''''''''''''''''''''''''''''''''''");
                   paiementController.addPaiement(currentUser,paiement);
                        ;
                        System.out.println("''''''''''''''''''''''''''''''''''''''");

//                        System.out.println("Paiement avec success");
                    } catch (Exception e) {
                        System.out.println("error  : " + e.getMessage());
                    }



                    break;

                case 6:


                    System.out.println("give the id of agent ");
                    int idAGENT=scanner.nextInt();
                     paiementController.getPaiementByAgent(idAGENT).stream().mapToDouble(Paiement::getMontant).average().ifPresentOrElse(
                        moyenne -> System.out.println("Moyenne des paiements : " + moyenne),
                        () -> System.out.println("Aucun paiement found  pour calculer la moyenne.")
                );


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
