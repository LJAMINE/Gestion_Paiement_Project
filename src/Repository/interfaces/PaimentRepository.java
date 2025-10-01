package Repository.interfaces;

import Model.Paiement;

import java.util.List;

public interface PaimentRepository {
    public void addPaiement(Paiement paiement) throws Exception;
    public List<Paiement> getPaiementByAgent(int idAgent)throws Exception;


}
