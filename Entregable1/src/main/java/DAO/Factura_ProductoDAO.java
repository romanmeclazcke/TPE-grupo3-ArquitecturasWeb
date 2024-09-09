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
            PreparedStatement ps = conn.prepareStatement("SELECT p.id_producto, p.nombre, SUM(cantidad * valor) AS recaudacion FROM producto p " +
                    "JOIN factura_producto fp USING (id_producto) " +
                    "GROUP BY p.id_producto, p.nombre ORDER BY recaudacion DESC LIMIT 1");


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_producto");
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
