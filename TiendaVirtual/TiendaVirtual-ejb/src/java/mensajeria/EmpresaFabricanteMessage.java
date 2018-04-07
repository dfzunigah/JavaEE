package mensajeria;

import entidades.Orden;
import entidades.Producto;
import java.util.List;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @author Daniel Zuñiga (03/04/18) @ UEC
 **/
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/CreacionOrdenTopic")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/CreacionOrdenTopic")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/CreacionOrdenTopic")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class EmpresaFabricanteMessage implements MessageListener {
    
    public EmpresaFabricanteMessage() {}
    
    @Override
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage) message;
            Orden orden = (Orden) objectMessage.getObject();
            
            List<Producto> productos = orden.getProductos();
            String nombresProductos = "";
            for (int i=0; i<productos.size(); i++){
                Producto producto = productos.get(i);
                if(i==0){
                    nombresProductos = producto.getNombre();
                }else{
                    nombresProductos = nombresProductos + ", " + producto.getNombre();
                }
            }
                    
            System.out.println("Empresa fabricante: Se ha recibido la notificación "
                             + "de venta de los productos: " + nombresProductos + ".");
        }catch(JMSException ex){
            System.out.println("Error EmpresaFabricanteMessage: " + ex);
        }
    }
}