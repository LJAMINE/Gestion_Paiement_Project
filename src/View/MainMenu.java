package View;

import Controller.AgentController;
import DAO.AgentDAO;
import Model.Agent;
import Model.TypeAgent;
import Repository.AgentRepositoryImpl;
import Service.AgentService;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        AgentController controller = new AgentController(new AgentService(new AgentRepositoryImpl()));
        AgentDAO agentDAO = new AgentDAO();




        //        login--------------------------------------------------

        System.out.println("se connnecter");
        System.out.println("emaill");
        String emailConnecte = scanner.nextLine();
        System.out.println("mot de passe ");
        String passwordConnecte = scanner.nextLine();


        Agent currentUser = agentDAO.getAgentByEmailAndPassword(emailConnecte, passwordConnecte);

        if (currentUser==null){
            System.out.println("email or password are incorrect ");
            return;
        }
        if (currentUser.getTypeAgent() == TypeAgent.DIRECTEUR) {
            DirectorMenu.launch(currentUser);
        } else if (currentUser.getTypeAgent() == TypeAgent.RESPONSABLE_DEPARTEMENT) {
            ResponsableMenu.launch(currentUser, controller);
        } else if (currentUser.getTypeAgent() == TypeAgent.OUVRIER || currentUser.getTypeAgent() == TypeAgent.STAGIAIRE) {
            AgentOSMenu.launch(currentUser);
        } else {
            System.out.println("Aucune permission pour ce menu.");
        }


    }
}
