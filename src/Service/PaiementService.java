package Service;

import Model.Agent;
import Model.Paiement;
import Model.TypeAgent;
import Model.TypePaiement;
import Repository.interfaces.PaimentRepository;

import java.util.List;

public class PaiementService {
    private PaimentRepository paimentRepository;

    public PaiementService(PaimentRepository paimentRepository) {
        this.paimentRepository = paimentRepository;
    }

    public void addPaiement(Agent currentUser, Paiement paiement) throws Exception {
        Agent target = paiement.getAgent();


        // DIRECTEUR: only allowed to pay Responsable deparrtement


        if (currentUser.getTypeAgent() == TypeAgent.DIRECTEUR) {
            if (target.getTypeAgent() != TypeAgent.RESPONSABLE_DEPARTEMENT) {
                throw new Exception("Le directeur ne peut ajouter des paiements que pour les responsables de département.");

            }

            // RESPONSABLE_DEPARTEMENT: only allowed to pay OUVRIER/STAGIAIRE in their department

        } else if (currentUser.getTypeAgent()==TypeAgent.RESPONSABLE_DEPARTEMENT) {
            if (target.getDepartement() == null ||
                    target.getDepartement().getIdDepartement() != currentUser.getDepartement().getIdDepartement()) {
                throw new Exception("Vous ne pouvez payer que les agents de votre propre département.");
            }
            if (target.getTypeAgent() != TypeAgent.OUVRIER && target.getTypeAgent() != TypeAgent.STAGIAIRE) {
                throw new Exception("Vous ne pouvez payer que les ouvriers ou stagiaires de votre département.");
            }
        }else {
            throw new Exception("Vous n'avez pas le droit d'ajouter des paiements.");

        }

        // Business rules for payment  , montant .

        if (paiement.getMontant() < 0)
            throw new Exception("Montant negatif interdit.");
        if ((paiement.getTypePaiement() == TypePaiement.BONUS || paiement.getTypePaiement() == TypePaiement.INDEMNITE)) {
            Agent a = paiement.getAgent();
            if (!(a.getTypeAgent() == TypeAgent.RESPONSABLE_DEPARTEMENT || a.getTypeAgent() == TypeAgent.DIRECTEUR)) {
                throw new Exception("just director and responsable are eligible of bonus and indemnit");
            }
            if (!(paiement.isConditionValidee())) {
                throw new Exception("Condition non valid pour bonus/indemnit.");
            }

        }
        paimentRepository.addPaiement(paiement);

    }

    public List<Paiement> getPaiementByAgent(int idAgent)throws Exception{
     return paimentRepository.getPaiementByAgent(idAgent);
    }
}
