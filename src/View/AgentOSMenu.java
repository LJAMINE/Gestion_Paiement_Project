package View;

import Controller.PaiementController;
import Model.Agent;
import Model.Departement;
import Model.Paiement;
import Model.TypePaiement;
import Repository.PaiementRepositoryImpl;
import Service.PaiementService;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;


public class AgentOSMenu {
    public static void launch(Agent currentUser) throws Exception {
        Scanner scanner = new Scanner(System.in);
        PaiementController paiementController = new PaiementController(new PaiementService(new PaiementRepositoryImpl()));
        boolean run = true;
        while (run) {
            System.out.println("\nMenu Agent:");
            System.out.println("1. Mes informations personnelles");
            System.out.println("2. Mon département");
            System.out.println("3. Voir historique  du  paiement");
            System.out.println("4. Calculer le total de mes paiements");
            System.out.println("5. filtrer les paiement by Type");
            System.out.println("6. biggest montant ");


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
                case 3:
                    List<Paiement> paiements = paiementController.getPaiementByAgent(currentUser.getIdAgent());
                    paiements.forEach(System.out::println);
                    break;

                case 4:
                    double total = paiementController.getPaiementByAgent(currentUser.getIdAgent()).stream().mapToDouble(Paiement::getMontant).sum();

                    System.out.println("total of your paiement is :  " + total);

                    break;

                case 5:
                    System.out.println("entrer type of paiement for filter ");
                    String typeChoisi = scanner.nextLine();

                    TypePaiement typePaiement = TypePaiement.valueOf(typeChoisi);

                    List<Paiement> paiementsFiltred = paiementController.getPaiementByAgent(currentUser.getIdAgent()).stream().filter(p -> p.getTypePaiement() == typePaiement).toList();
                    paiementsFiltred.forEach(System.out::println);
                    break;

                case 6:
                       OptionalDouble maximumMontant =paiementController.getPaiementByAgent(currentUser.getIdAgent()).stream().mapToDouble(Paiement::getMontant).max();

                    System.out.println("max montant is "+maximumMontant);

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
