package entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
public class Producto {
    @Id
    private int id;
    
    private String nombre;
    private String descripcion;
    private long precio;
    
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    
    @ManyToOne(optional=false)
    private Vendedor vendedor;
    
    /*Cuando es @ManyToMany uno elige quién es el dueño de la relación, en caso
      de no colcoar quién es el dueño, como acá, entonces el dueño de la relación
      es el del otro lado.*/
    @ManyToMany
    private List<Categoria> categorias;
    
    /*Este es un campo opcional y sólo va a ser llenado cuando se vincule a una orden.
      Por default es opcional aunque tambien se podría colocar de manera explicita como
      (optional = true)*/
    @ManyToOne
    private Orden orden;
}
