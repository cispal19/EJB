/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.servicio.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import pe.edu.cibertec.dominio.dto.mbd.ProductoEmailDto;

/**
 *
 * @author JAdv-MJ
 */
@JMSDestinationDefinition(name = "java:app/productoEmailQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "productoEmailQueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/productoEmailQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EmailMDB implements MessageListener {
    
    public EmailMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
            ProductoEmailDto productoEmailDto=  message.getBody(ProductoEmailDto.class);
            if (productoEmailDto != null) {
                System.out.println("procesando prodcuto de email");
                System.out.println(productoEmailDto.getProductoid());
                System.out.println(productoEmailDto.getCorreo());
                
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
       
    }
    
}
