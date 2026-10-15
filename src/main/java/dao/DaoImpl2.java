package dao;

import org.springframework.stereotype.Component;

@Component("dao2")
public class DaoImpl2 implements IDao {
    @Override
    public double getValue() {
        System.out.println("[DAO] DaoImpl2 retourne 150.0");
        return 150.0;
    }
}
