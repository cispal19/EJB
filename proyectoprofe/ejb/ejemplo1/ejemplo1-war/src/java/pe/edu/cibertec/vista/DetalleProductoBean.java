package pe.edu.cibertec.vista;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.dto.mdb.ProductoEmailDto;
import pe.edu.cibertec.servicio.ProductoServicio;

@Named
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Resource(mappedName = "java:app/productoEmailQueue")
    private Queue java_appProductoEmailQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    private static final long serialVersionUID = 1L;

    private int id;
    private Producto producto;
    private String correo;

    @EJB
    private ProductoServicio productoServicio;

    public void carga() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            producto = productoServicio.obtenerPorId(id);
        }
    }

    public void enviarEmail() {
        ProductoEmailDto productoEmailDto = new ProductoEmailDto();
        productoEmailDto.setProductoId(producto.getId());
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
