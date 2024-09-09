import DAO.ClienteDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;
import DTO.ClienteDTO;
import DTO.ProductoDTO;
import Utils.HelperMySQL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws IOException, SQLException {
        HelperMySQL hp = new HelperMySQL();
        hp.createTables();
        //hp.insertAll();

        ArrayList<ClienteDTO> aux = new ClienteDAO(hp.getConnection()).getClientes();
        for (ClienteDTO cliente : aux) {
            System.out.println(cliente);
        }


        ProductoDTO producto= new Factura_ProductoDAO(hp.getConnection()).getProductoMayorRecaudacion();
        System.out.println(producto);


    }
}
