package Factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import Utils.HelperMySQL;
import com.mysql.cj.xdevapi.Client;
import java.sql.SQLException;

public class MySQLDaoFactory extends DAOFactory {
    private static MySQLDaoFactory instance;
    private HelperMySQL helper;

    public MySQLDaoFactory() {
        this.helper = new HelperMySQL();
    }

    public static MySQLDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySQLDaoFactory();
        }

        return instance;
    }

    @Override
    public ClienteDAO getClienteDAO() throws SQLException {
        return ClienteDAO.getInstancia(helper.getConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() throws SQLException {
        return FacturaDAO.getInstancia(helper.getConnection());
    }

    @Override
    public Factura_ProductoDAO getFacturaProductoDAO() throws SQLException {
        return Factura_ProductoDAO.getInstancia(helper.getConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() throws SQLException {
        return ProductoDAO.getInstancia(helper.getConnection());
    }
}