import DAO.ClienteDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import DTO.ClienteDTO;
import DTO.ProductoDTO;
import Factory.DAOFactory;
import Utils.HelperMySQL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws IOException, SQLException {
        HelperMySQL hp = new HelperMySQL();
        hp.createTables();
        hp.insertAll();

        ArrayList<ClienteDTO> aux = DAOFactory.getInstance(1).getClienteDAO().getClientes();

        for (ClienteDTO cliente : aux) {
            System.out.println(cliente);
        }


        ProductoDTO producto= DAOFactory.getInstance(1).getFacturaProductoDAO().getProductoMayorRecaudacion();
        System.out.println(producto);


    }
}
