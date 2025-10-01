package View;

import Controller.AgentController;
import DAO.impl.AgentDAOImpl;
import DAO.interfaces.IAgentDAO;
import Model.Agent;
import Model.TypeAgent;
import Repository.impl.AgentRepositoryImpl;
import Service.AgentService;
import Exception.DataAccessException;
import Exception.BusinessException;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws Exception {
        try {
            Scanner scanner = new Scanner(System.in);
            AgentController controller = new AgentController(new AgentService(new AgentRepositoryImpl(new AgentDAOImpl())));

            IAgentDAO agentDAO = new AgentDAOImpl();

            //        login--------------------------------------------------

            System.out.println("se connnecter");
            System.out.println("emaill");
            String emailConnecte = scanner.nextLine();
            System.out.println("mot de passe ");
            String passwordConnecte = scanner.nextLine();


            Agent currentUser = agentDAO.getAgentByEmailAndPassword(emailConnecte, passwordConnecte);

            if (currentUser == null) {
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
        } catch (BusinessException e) {
            System.out.println("Erreur métier : " + e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }


    }
}
