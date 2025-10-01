package Repository.impl;

import DAO.impl.PaiementDAO;
import Model.Paiement;
import Repository.interfaces.PaimentRepository;

import java.util.List;

public class PaiementRepositoryImpl implements PaimentRepository {

    private PaiementDAO paiementDAO = new PaiementDAO();

    @Override
    public void addPaiement(Paiement paiement) throws Exception {
        paiementDAO.addPaiement(paiement);
    }

    @Override
    public List<Paiement> getPaiementByAgent(int idAgent) throws Exception {
        return paiementDAO.getPaiementByAgent(idAgent);
    }
}
