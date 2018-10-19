package pe.edu.cibertec.dto.mdb;

import java.io.Serializable;

public class ProductoEmailDto implements Serializable {

    private int productoId;
    private String correo;

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
