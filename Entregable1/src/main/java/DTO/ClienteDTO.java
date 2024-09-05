package DTO;

public class ClienteDTO {
    private int idCliente;
    private String nombre, email;
    private int cantidadFacturas;

    public ClienteDTO(int idCliente, String nombre, String email, int cantidadFacturas) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.cantidadFacturas = cantidadFacturas;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", cantidad de facturas='" + email + '\'' +
                '}';
    }
}
