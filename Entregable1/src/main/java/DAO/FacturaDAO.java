package DAO;

import Entities.Factura;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaDAO {
    private Connection conn;

    public FacturaDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertFactura(Factura factura) {
        //COMPLETAR
    }
}
