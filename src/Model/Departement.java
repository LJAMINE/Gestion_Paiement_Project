package Model;

import java.util.ArrayList;

public class Departement extends Person {

    public int idDepartement;
    public String nom;
    ArrayList<Agent> stockAgents;
//    responsable (Agent)

    public Departement(String name, String prénom, String email, String motDePasse, int idDepartement, String nom) {
        super(name, prénom, email, motDePasse);
        this.idDepartement = idDepartement;
        this.nom = nom;
        this.stockAgents = new ArrayList<>();
    }
}
