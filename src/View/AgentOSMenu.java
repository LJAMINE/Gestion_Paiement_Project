package View;
import Model.Agent;
import Model.Departement;
import java.util.Scanner;



public class AgentOSMenu {
    public static void launch(Agent currentUser) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("\nMenu Agent:");
            System.out.println("1. Mes informations personnelles");
            System.out.println("2. Mon département");
            System.out.println("0. Quitter");

            int choix = Integer.parseInt(scanner.nextLine());
            switch (choix) {
                case 1:
                    System.out.println("Nom: " + currentUser.getName());
                    System.out.println("Prénom: " + currentUser.getPrenom());
                    System.out.println("Email: " + currentUser.getEmail());
                    System.out.println("Type: " + currentUser.getTypeAgent());
                    break;
                case 2:
                    Departement d = currentUser.getDepartement();
                    System.out.println("Département: " + d.getNom());
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Choix inconnu.");
            }
        }
    }
}
