package notificaciones;

import entidades.Orden;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;

/**
 * @author Daniel Zuñiga (03/04/18) @ UEC
 **/
/*Esta clase lo que va a hacer es interceptar el método crearOrden de
  AdminitracionPersistenciaJPA. Por medio de este interceptor se escribe un mensaje.*/
public class NotificacionInterceptor {
    
    /*Acá le dice a cuál factory de Topic debe dirigirse para conectarse al
      topic correcto.*/
    @Inject
    @JMSConnectionFactory("jms/CreacionOrdenTopicFactory")
    private JMSContext context;
    
    /*Acá le dice a que destination se va a conectar.*/
    @Resource(name = "jms/CreacionOrdenTopic")
    private Destination destination;
    
    /*Lo que se va a colocar en el topic es el objecto orden.*/
    @AroundInvoke
    public Object notificarCreacionOrden(InvocationContext ic) throws Exception{
        /*En este caso sólo hay un parámetro, lo que el hace es crear un arreglo
          con el número de parámetros, de ahí que acá se vaya a la posición 0.*/
        Object[] object = ic.getParameters();
        Orden orden = (Orden) object[0];
        
        ObjectMessage message = context.createObjectMessage();
        message.setObject(orden);
        
        JMSProducer producer = context.createProducer();
        producer.send(destination, message);
        
        return ic.proceed();
    }
    
}
