package entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
public class Comprador extends Persona{
    /*Siempre que exista una relación bidireccional, siempre hay que establecer
      quién es el dueño de la relación. Si esa relación es muchos a 1, el dueño de
      la relación es el lado muchos. Por ejemplo, acá el dueño de la relación es
      Orden y el atributo que lo relaciona sería "comprador" del tipo Comprador.*/
    @OneToMany(mappedBy="comprador")
    private List<Orden> ordenes;
    
    private int cantidadCompras;
}
