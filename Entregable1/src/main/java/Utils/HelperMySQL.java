package Utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
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
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropTables() throws SQLException {
        String dropCliente = "DROP TABLE IF EXISTS cliente";
        String dropFactura = "DROP TABLE IF EXISTS producto";
        String dropFacturaProducto = "DROP TABLE IF EXISTS factura_producto";
        String dropProducto = "DROP TABLE IF EXISTS factura";

        this.conn.prepareStatement(dropCliente).execute();
        this.conn.prepareStatement(dropFactura).execute();
        this.conn.prepareStatement(dropFacturaProducto).execute();
        this.conn.prepareStatement(dropProducto).execute();
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

        this.conn.prepareStatement(tableCliente).execute();
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.prepareStatement(tableFacturaProducto).execute();
        this.conn.commit();
    }


    public void insertAll () throws IOException {
        this.insertProducto();
        this.insertCliente();
        this.insertFacturaProducto();
        this.insertFactura();
    }

    public void insertFactura() throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("../../resources/CSV/facturas.csv")); //CHEQUEAR QUE LA RUTA ESTÁ BIEN
        for(CSVRecord row: parser) {
            String query = "INSERT INTO factura (idFactura, idCliente) VALUES (?,?)";
            PreparedStatement ps = null;

            try {
                ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt((row.get("idFactura"))));
                ps.setInt(2, Integer.parseInt((row.get("idCliente"))));
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertProducto() throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("../../resources/CSV/productos.csv")); //CHEQUEAR QUE LA RUTA ESTÁ BIEN
        for(CSVRecord row: parser) {
            String query = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
            PreparedStatement ps = null;

            try {
                ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt((row.get("idProducto"))));
                ps.setString(2, (row.get("nombre")));
                ps.setFloat(3, Float.parseFloat((row.get("valor"))));
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertFacturaProducto() throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("../../resources/CSV/facturas-productos.csv")); //CHEQUEAR QUE LA RUTA ESTÁ BIEN
        for(CSVRecord row: parser) {
            String query = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?,?,?)";
            PreparedStatement ps = null;

            try {
                ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt((row.get("idFactura"))));
                ps.setInt(2, Integer.parseInt((row.get("idProducto"))));
                ps.setInt(3, Integer.parseInt((row.get("cantidad"))));
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertCliente() throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("../../../resources/CSV/clientes.csv")); //CHEQUEAR QUE LA RUTA ESTÁ BIEN
        for(CSVRecord row: parser) {
            String query = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";
            PreparedStatement ps = null;

            try {
                ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt((row.get("idCliente"))));
                ps.setString(2, (row.get("nombre")));
                ps.setString(3, (row.get("email")));
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws SQLException {
        return this.conn;
    }
}
