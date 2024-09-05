package DTO;

public class ProductoDTO {
        private int idProducto;
        private String nombre;
        private float recaudacion;

        public ProductoDTO(int idProducto, String nombre, float recaudacion) {
            this.idProducto = idProducto;
            this.nombre = nombre;
            this.recaudacion = recaudacion;
        }

        public int getIdProducto() {
            return idProducto;
        }

        public String getNombre() {
            return nombre;
        }

        public float getRecaudacion() {
            return recaudacion;
        }

        @Override
        public String toString() {
            return "Producto{" +
                    "idProducto=" + idProducto +
                    ", nombre='" + nombre + '\'' +
                    ", recaudacion=" + recaudacion +
                    '}';
        }
}
