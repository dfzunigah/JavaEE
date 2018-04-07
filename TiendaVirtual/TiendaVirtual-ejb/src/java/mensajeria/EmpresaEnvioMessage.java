package mensajeria;

import entidades.Orden;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @author Daniel Zuñiga (03/04/18) @ UEC
 **/
/*Este MDB lo que hace es simular una aplicación externa. Siempre que utilizo un
  MDB él recupera el mensaje y luego ejecuta el método onMessage, en el cual se 
  debe programar el procesamiento del mensaje.
  En caso de que haya una excepción en el método onMessage, no pasa nada. Es
  necesario que el onMessage se ejecute bien para que el mensaje se borre de la
  queue o el topic.*/
@MessageDriven(activationConfig = {
    /*Acá se pueden configurar varias cosas: acknowledgeMode (auto-acknowledge o
      dups-ok), la diferencia está en que el primero envía la notificación de éxito
      del onMessage de una mientras que el segundo lo hace en paquetes.
      Sólo para el caso de los Topic: subscriptionDurability (Durable o NonDurable),
      en el primer caso si hay tres consumidores él sólo borra el mensaje hasta
      que los tres (3) lo reciben en cambio el segundo hace el intento pero si no
      llega paila.*/
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/CreacionOrdenTopic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/CreacionOrdenTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/CreacionOrdenTopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
    /*Acá le podría añadir filtros:
    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "Fragile is TRUE")
      Los parámetros de propertyValue son bastantes diversos, funcionan como SELECT de SQL.
      Pero hay que tener cuidado con los filtros pues requieren añadir otra capa
      lógica y reduce la eficiencia del sistema.*/
})
public class EmpresaEnvioMessage implements MessageListener {
    
    public EmpresaEnvioMessage() {}
    
    @Override
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage) message;
            Orden orden = (Orden) objectMessage.getObject();
            System.out.println("Empresa de envío: Se ha recibido la notificación "
                             + "para que los productos sean enviados a la dirección "
                             + orden.getInformaciónEnvio().getDireccion());
        }catch(JMSException ex){
            System.out.println("Error EmpresaEnvioMessage: " + ex);
        }
    }
}