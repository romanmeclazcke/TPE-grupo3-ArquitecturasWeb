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

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertCliente(Cliente c) {
        //COMPLETAR
    }

    public ArrayList<ClienteDTO> getClientes() {
        ArrayList<ClienteDTO> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT idCliente, nombre, email, count(*) AS cantidadFacturas FROM cliente c " +
                    "JOIN factura f USING (idCliente) GROUP BY idCliente, nombre, email ORDER BY cantidadFacturas DESC");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                int cantidadFacturas = rs.getInt("cantidadFacturas");
                ClienteDTO cliente = new ClienteDTO(id, nombre, email, cantidadFacturas);
                list.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
