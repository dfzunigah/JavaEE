package auditoria;

import entidades.Bitacora;
import java.util.Date;
import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import logica.AdministracionOrdenLocal;
import logica.AdministracionPersistenciaJPALocal;

/**
 * @author Daniel Zuñiga (22/03/18) @ UEC
 **/
public class CreacionOrdenInterceptor {
   
   @EJB
   AdministracionPersistenciaJPALocal administracionPersistencia;
   /*Siempre lanza excepcion, devuelve object, lleva AroundInvoke, invocationContext.
     Lo único modificable acá es el nombre del método.
     La idea de este interceptor es que vaya a la DB y me llene un registro.*/
   @AroundInvoke
   public Object creacionOrden(InvocationContext ic) throws Exception{
       Object object = ic.getTarget();
       AdministracionOrdenLocal administracionOrden = (AdministracionOrdenLocal) object;
       
       Bitacora bitacora = new Bitacora();
       bitacora.setPersona(administracionOrden.getComprador());
       bitacora.setFecha(new Date());
       bitacora.setDescripcion("Creación de nueva orden");
       
       administracionPersistencia.crearBitacora(bitacora);
       
       /*Siempre que se quiera ejecutar el método interceptado, es necesario
         el proceed. Sino pues no se ejecuta. Por ejemplo en validación: Si las
         contraseñas cuadran entonces dele proceed, sino cuadran pues entonces
         no le damos proceed.*/
       return ic.proceed();
   }
}
