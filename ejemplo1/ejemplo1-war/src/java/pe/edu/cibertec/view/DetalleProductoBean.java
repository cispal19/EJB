/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.servicio.ProductoServicio;

/**
 *
 * @author JAdv-MJ
 */
@ManagedBean
@RequestScoped
public class DetalleProductoBean implements Serializable {

    private int id;
    private Producto producto;

    @EJB
    private ProductoServicio productoServicio;

    public void carga() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            producto = productoServicio.obtenerById(id);

        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
