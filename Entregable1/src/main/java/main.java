import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import Utils.HelperMySQL;

import java.io.IOException;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws IOException, SQLException {
        HelperMySQL hp = new HelperMySQL();
        hp.createTables();
        hp.insertAll();
    }
}
