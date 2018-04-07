package excepciones;

import javax.ejb.ApplicationException;

/**
 * @author Daniel Zuñiga (04/04/18) @ UEC
 **/
/*Esta notación permite hacer una implementación más limpia del rollback sin
  necesidad de ensuciar el código en Administracion. De no colocarlas toca
  colocar el try-catch en el código.*/
@ApplicationException(rollback=true)
public class CreacionOrdenException extends Exception{
    
}
