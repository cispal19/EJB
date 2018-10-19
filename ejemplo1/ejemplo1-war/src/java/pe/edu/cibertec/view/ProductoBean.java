/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.servicio.ProductoServicio;

/**
 *
 * @author JAdv-MJ
 */
@ManagedBean
@ViewScoped
public class ProductoBean implements Serializable{
    
    @EJB
    private ProductoServicio productoServicio;
    
    private List<Producto> listaProducto;
    
    @PostConstruct
    public void init(){
    listaProducto=productoServicio.obtenerTodos();
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }
    
    
    
    
    
}
