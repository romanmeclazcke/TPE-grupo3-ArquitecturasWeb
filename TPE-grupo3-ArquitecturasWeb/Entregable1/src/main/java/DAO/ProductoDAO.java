package DAO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import Entities.Producto;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection conn;
    private static ProductoDAO instancia;

    private ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertProducto(Producto p) {
        //COMPLETAR
    }

    public static ProductoDAO getInstancia(Connection conn) {
        if (instancia == null) {
            instancia = new ProductoDAO(conn);
        }

        return instancia;
    }

}
