package pe.edu.cibertec.vista;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.servicio.ProductoServicio;

@Named
@ViewScoped
public class ProductoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private transient ProductoServicio productoServicio;

    private List<Producto> listaProductos;

    @PostConstruct
    public void init() {
        listaProductos = productoServicio.obtenerTodos();
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
