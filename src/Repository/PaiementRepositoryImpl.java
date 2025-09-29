package Repository;

import DAO.PaiementDAO;
import Model.Paiement;

import java.sql.SQLException;

public class PaiementRepositoryImpl implements PaimentRepository {

    private PaiementDAO paiementDAO = new PaiementDAO();

    @Override
    public void addPaiement(Paiement paiement) throws Exception {
        paiementDAO.addPaiement(paiement);
    }
}
