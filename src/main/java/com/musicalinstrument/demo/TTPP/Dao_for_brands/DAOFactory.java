package com.musicalinstrument.demo.TTPP.Dao_for_brands;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class DAOFactory {
    public IDAO createDao(EDAOType type) throws NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        if (type == EDAOType.MySQL) {
            return MySQLDAO.getInstance();
        }
        return null;
    }
}
