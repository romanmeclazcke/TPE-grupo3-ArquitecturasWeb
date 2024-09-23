package Factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;

import java.sql.SQLException;

public abstract class DAOFactory  {
    public static DAOFactory getInstance(int whichFactory) {
        switch (whichFactory) {
            case 1:
                return MySQLDaoFactory.getInstance();
            //case 2:
            //  return new DerbyDaoFactory();
            default:
                return null;
        }
    }
    public abstract ClienteDAO getClienteDAO() throws SQLException;
    public abstract FacturaDAO getFacturaDAO() throws SQLException;
    public abstract Factura_ProductoDAO getFacturaProductoDAO() throws SQLException;
    public abstract ProductoDAO getProductoDAO() throws SQLException;

}