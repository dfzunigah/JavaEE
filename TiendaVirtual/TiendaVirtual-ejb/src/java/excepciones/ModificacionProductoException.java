package excepciones;

import javax.ejb.ApplicationException;

/**
 * @author Daniel Zuñiga (04/04/18) @ UEC
 **/
/*Referenciese a la otra excepción para ver una explicación de esta notación.*/
@ApplicationException(rollback=true)
public class ModificacionProductoException extends Exception{
    
}
