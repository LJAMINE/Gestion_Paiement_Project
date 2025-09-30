package Repository;

import DAO.PaiementDAO;
import Model.Paiement;

import java.sql.SQLException;
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
