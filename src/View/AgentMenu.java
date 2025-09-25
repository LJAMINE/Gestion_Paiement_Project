package View;

import Controller.AgentController;
import Model.Agent;
import Model.Departement;
import Model.TypeAgent;
import Repository.AgentRepositoryImpl;
import Repository.DepartementRepositoryImpl;
import Service.AgentService;
import Service.DepartementService;

import java.util.Scanner;

public class AgentMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AgentController controller = new AgentController(new AgentService(new AgentRepositoryImpl()));
        DepartementService departementService = new DepartementService(new DepartementRepositoryImpl());


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


    }
}
