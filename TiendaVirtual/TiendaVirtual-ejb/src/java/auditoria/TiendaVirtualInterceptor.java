package auditoria;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Daniel Zuñiga (22/03/18) @ UEC
 **/
public class TiendaVirtualInterceptor {

    @AroundInvoke
    public Object interceptor(InvocationContext ic) throws Exception{
        System.out.println("Se va a ejecutar el método " + ic.getMethod().getName());
        return ic.proceed();
    }
    
}
