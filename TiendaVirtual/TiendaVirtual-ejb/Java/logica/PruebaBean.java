package logica;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Stateless
@LocalBean
public class PruebaBean {

   @PersistenceContext
   EntityManager em;
}
