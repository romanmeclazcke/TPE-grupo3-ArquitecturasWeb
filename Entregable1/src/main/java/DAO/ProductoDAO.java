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

    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertProducto(Producto p) {
        //COMPLETAR
    }

}
