package Model;

import java.time.LocalDate;

public class Paiement {

    private int idPaiement;
    private TypePaiement typePaiement;
    private double montant;
    private LocalDate date;
    private String motif; // motif or event
    private Agent agent; // Association with Agent

    public Paiement() {
        // Default constructor for instantiate without all arguments.
    }
    public Paiement(int idPaiement, TypePaiement typePaiement, double montant, LocalDate date, String motif, Agent agent) {
        this.idPaiement = idPaiement;
        this.typePaiement = typePaiement;
        this.montant = montant;
        this.date = date;
        this.motif = motif;
        this.agent = agent;
    }

    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    public TypePaiement getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
