package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {
    @Autowired
    // Utiliser "dao" pour DaoImpl (100 -> 200)
    // Utiliser "dao2" pour DaoImpl2 (150 -> 300)
    @Qualifier("dao")
    private IDao dao;

    @Override
    public double calcul() {
        double result = dao.getValue() * 2;
        System.out.println("[METIER] Résultat du calcul : " + result);
        return result;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
