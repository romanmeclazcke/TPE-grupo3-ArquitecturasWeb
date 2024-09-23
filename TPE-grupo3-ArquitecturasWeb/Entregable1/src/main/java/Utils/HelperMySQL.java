package Utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/entregable1";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false); // Transacciones manuales
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropTables() throws SQLException {
        String[] dropQueries = {
                "DROP TABLE IF EXISTS cliente",
                "DROP TABLE IF EXISTS producto",
                "DROP TABLE IF EXISTS factura_producto",
                "DROP TABLE IF EXISTS factura"
        };

        for (String query : dropQueries) {
            this.conn.prepareStatement(query).execute();
        }
        this.conn.commit();
    }

    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                "idCliente INT NOT NULL, " +
                "nombre VARCHAR(500) NOT NULL, " +
                "email VARCHAR(150) NOT NULL, " +
                "PRIMARY KEY (idCliente))";

        String tableFactura = "CREATE TABLE IF NOT EXISTS factura (" +
                "idFactura INT NOT NULL, " +
                "idCliente INT NOT NULL, " +
                "PRIMARY KEY (idFactura), " +
                "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente))";

        String tableProducto = "CREATE TABLE IF NOT EXISTS producto (" +
                "idProducto INT NOT NULL, " +
                "nombre VARCHAR(45) NOT NULL, " +
                "valor FLOAT NOT NULL, " +
                "PRIMARY KEY (idProducto))";

        String tableFacturaProducto = "CREATE TABLE IF NOT EXISTS factura_producto (" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "FOREIGN KEY (idFactura) REFERENCES factura(idFactura), " +
                "FOREIGN KEY (idProducto) REFERENCES producto(idProducto))";

        String[] createQueries = {tableCliente, tableFactura, tableProducto, tableFacturaProducto};

        for (String query : createQueries) {
            this.conn.prepareStatement(query).execute();
        }
        this.conn.commit();
    }

    public void insertAll() throws IOException {
        try {
            this.insertProducto();
            this.insertCliente();
            this.insertFactura();
            this.insertFacturaProducto();
            conn.commit();
            System.out.println("Todo insertado");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();  // Hacer rollback si ocurre un error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void insertFactura() throws IOException {
        String path = "src/main/resources/CSV/facturas.csv";

        try (Reader in = new FileReader(path);
             CSVParser csvParser = CSVFormat.EXCEL.withHeader().parse(in)) {

            for (CSVRecord row : csvParser) {
                String query = "INSERT INTO factura (idFactura, idCliente) VALUES (?,?)";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, Integer.parseInt(row.get(0)));
                    ps.setInt(2, Integer.parseInt(row.get(1)));
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProducto() throws IOException {
        String path = "src/main/resources/CSV/productos.csv";

        try (Reader in = new FileReader(path);
             CSVParser csvParser = CSVFormat.EXCEL.withHeader().parse(in)) {

            for (CSVRecord row : csvParser) {
                String query = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, Integer.parseInt(row.get(0)));
                    ps.setString(2, row.get(1));
                    ps.setFloat(3, Float.parseFloat(row.get(2)));
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFacturaProducto() throws IOException {
        String path = "src/main/resources/CSV/facturas-productos.csv";

        try (Reader in = new FileReader(path);
             CSVParser csvParser = CSVFormat.EXCEL.withHeader().parse(in)) {

            for (CSVRecord row : csvParser) {
                String query = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, Integer.parseInt(row.get(0)));
                    ps.setInt(2, Integer.parseInt(row.get(1)));
                    ps.setInt(3, Integer.parseInt(row.get(2)));
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCliente() throws IOException {
        String path = "src/main/resources/CSV/clientes.csv";

        try (Reader in = new FileReader(path);
             CSVParser csvParser = CSVFormat.EXCEL.withHeader().parse(in)) {

            for (CSVRecord row : csvParser) {
                String query = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, Integer.parseInt(row.get(0)));
                    ps.setString(2, row.get(1));
                    ps.setString(3, row.get(2));
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return this.conn;
    }
}
