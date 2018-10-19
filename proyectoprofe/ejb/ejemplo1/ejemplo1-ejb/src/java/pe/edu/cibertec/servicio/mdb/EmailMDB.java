package pe.edu.cibertec.servicio.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.dto.mdb.ProductoEmailDto;
import pe.edu.cibertec.servicio.ProductoServicio;

@JMSDestinationDefinition(name = "java:app/productoEmailQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "productoEmailQueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/productoEmailQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EmailMDB implements MessageListener {

    @EJB
    private ProductoServicio productoServicio;

    public EmailMDB() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            ProductoEmailDto productoEmailDto = message.getBody(ProductoEmailDto.class);
            if (productoEmailDto != null) {
                System.out.println("Procesando email de producto.");
                System.out.println(productoEmailDto.getProductoId());
                System.out.println(productoEmailDto.getCorreo());
                Producto producto = productoServicio.obtenerPorId(productoEmailDto.getProductoId());
                System.out.println("nombre: " + producto.getNombre());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
