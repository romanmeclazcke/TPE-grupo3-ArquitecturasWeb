package DAO;

import DTO.ProductoDTO;
import Entities.Factura_Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Factura_ProductoDAO {
    private Connection conn;

    public Factura_ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertFacturaProducto(Factura_Producto fp) {
        //COMPLETAR
    }

    public ProductoDTO getProductoMayorRecaudacion() throws IOException {
        ProductoDTO producto = null;
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT fp.idProducto, p.nombre, SUM(cantidad * valor) AS recaudacion FROM factura_producto fp " +
                    "JOIN producto p USING (idProducto) " +
                    "GROUP BY p.idProducto, p.nombre ORDER BY recaudacion DESC LIMIT 1");


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                int recaudacion = rs.getInt("recaudacion");
                producto = new ProductoDTO(id, nombre, recaudacion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return producto;
    }
}
