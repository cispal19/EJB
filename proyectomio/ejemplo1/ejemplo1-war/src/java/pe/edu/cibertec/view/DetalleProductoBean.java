/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.view;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.dominio.dto.mbd.ProductoEmailDto;
import pe.edu.cibertec.servicio.ProductoServicio;

/**
 *
 * @author JAdv-MJ
 */
@ManagedBean
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Resource(mappedName = "java:app/productoEmailQueue")
    private Queue java_appProductoEmailQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
   
    
    private static final long serialVersionUID=1L;

    private int id;
    private Producto producto;
    private String correo;
    

    @EJB
    private ProductoServicio productoServicio;

    public void carga() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            producto = productoServicio.obtenerById(id);

        }

    }
    
    public void enviarEmail(){
        ProductoEmailDto productoEmailDto = new ProductoEmailDto();
        productoEmailDto.setProductoid(producto.getId());
        productoEmailDto.setCorreo(correo);
        
        context.createProducer().send(java_appProductoEmailQueue, productoEmailDto);
        
    
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    private void sendJMSMessageToProductoEmailQueue(String messageData) {
        context.createProducer().send(java_appProductoEmailQueue, messageData);
    }
    
    

}
