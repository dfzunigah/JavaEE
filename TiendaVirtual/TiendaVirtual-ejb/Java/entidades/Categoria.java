package entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
public class Categoria {
    @Id
    private int id;
    
    private String descripcion;
    
    /*Acá es necesario colocar el mappedBy porque al otro lado no lo colocamos.*/
    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos;
}
