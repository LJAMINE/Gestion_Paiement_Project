package Model;

import java.util.ArrayList;

public class Departement {

    private String idDepartement;
    private String nom;
    private Agent responsable;
    private ArrayList<Agent> agents;

    public Departement(String idDepartement, String nom, Agent responsable) {
        this.idDepartement = idDepartement;
        this.nom = nom;
        this.responsable = responsable;
        this.agents = new ArrayList<>();
    }

    public String getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(String idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Agent getResponsable() {
        return responsable;
    }

    public void setResponsable(Agent responsable) {
        this.responsable = responsable;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void setAgents(ArrayList<Agent> agents) {
        this.agents = agents;
    }
}
