package Controller;

import Model.Agent;
import Model.Paiement;
import Service.PaiementService;

import java.util.List;

public class PaiementController {
    private PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    public void addPaiement(Agent currentUser, Paiement paiement) throws Exception {
        try {
            paiementService.addPaiement(currentUser, paiement);
            System.out.println("Paiement avec success");

        } catch (Exception e) {
            System.out.println("Error dddd: " + e.getMessage());

        }
    }

    public List<Paiement> getPaiementByAgent(int idAgent)throws Exception{
        return paiementService.getPaiementByAgent(idAgent);
    }
}
