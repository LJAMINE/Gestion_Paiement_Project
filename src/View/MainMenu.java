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
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenu {


    private static final Logger logger = Logger.getLogger(MainMenu.class.getName());

    public static void main(String[] args) throws Exception {

        try {
            Scanner scanner = new Scanner(System.in);
            AgentController controller = new AgentController(new AgentService(new AgentRepositoryImpl(new AgentDAOImpl())));

            IAgentDAO agentDAO = new AgentDAOImpl();

            //        login--------------------------------------------------

            System.out.println("se connnecter");
            System.out.println("emaill");
            String emailConnecte = scanner.nextLine();
            if (emailConnecte == null || emailConnecte.trim().isEmpty()) {
                System.out.println("email  pas valide");
                return;
            }
            System.out.println("mot de passe ");
            String passwordConnecte = scanner.nextLine();

            if (passwordConnecte == null || passwordConnecte.trim().isEmpty()) {
                System.out.println("password pas valide");
                return;
            }
            logger.info("Login attempt for email: " + emailConnecte);


            Agent currentUser = agentDAO.getAgentByEmailAndPassword(emailConnecte, passwordConnecte);


            if (currentUser == null) {
                System.out.println("email or password are incorrect ");
                return;
            }
            if (currentUser.getTypeAgent() == TypeAgent.DIRECTEUR) {
                logger.info("Launching DirectorMenu for  "+ currentUser.getEmail());
                DirectorMenu.launch(currentUser);
            } else if (currentUser.getTypeAgent() == TypeAgent.RESPONSABLE_DEPARTEMENT) {
                logger.info("Launching RESPONSABLE_DEPARTEMENT  for " + currentUser.getEmail());
                ResponsableMenu.launch(currentUser, controller);
            } else if (currentUser.getTypeAgent() == TypeAgent.OUVRIER || currentUser.getTypeAgent() == TypeAgent.STAGIAIRE) {
                logger.info("Launching OUVRIER/STAGIAIRE or  for " + currentUser.getEmail());
                AgentOSMenu.launch(currentUser);
            } else {
                System.out.println("Aucune permission pour ce menu.");
            }
        } catch (BusinessException e) {
            logger.warning("Business error: " + e.getMessage());
            System.out.println("Erreur métier : " + e.getMessage());
        } catch (DataAccessException e) {
            logger.severe("Database error: " + e.getMessage());
            System.out.println("Erreur base de données : " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error", e);
            System.out.println("Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }


    }
}
