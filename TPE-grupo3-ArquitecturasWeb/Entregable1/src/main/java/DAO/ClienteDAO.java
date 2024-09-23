package DAO;

import DTO.ClienteDTO;
import Entities.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClienteDAO {
    private Connection conn;
    private static ClienteDAO instancia;

    private ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertCliente(Cliente c) {
        //COMPLETAR
    }

    public ArrayList<ClienteDTO> getClientes() {
        ArrayList<ClienteDTO> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT c.idCliente, c.nombre, c.email, sum(fp.cantidad*p.valor) AS totalFacturado FROM cliente c " +
                    "JOIN factura f USING (idCliente) " +
                            "JOIN factura_producto fp  USING (idFactura)" +
                            "JOIN producto p USING (idProducto) " +
                            "GROUP BY c.idCliente, c.nombre, c.email " +
                            "ORDER BY totalFacturado DESC");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                int totalFacturado = rs.getInt("totalFacturado");
                ClienteDTO cliente = new ClienteDTO(id, nombre, email, totalFacturado);
                list.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static ClienteDAO getInstancia(Connection conn) {
        if (instancia == null) {
            instancia = new ClienteDAO(conn);
        }

        return instancia;
    }
}
