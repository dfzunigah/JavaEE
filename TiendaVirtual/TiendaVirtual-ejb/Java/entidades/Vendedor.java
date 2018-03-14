package entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
public class Vendedor extends Persona{
    //Acá no necesita el @Id debido a que hereda de persona, sino lo hiciera pailas sale error
    @OneToMany(mappedBy="vendedor")
    private List<Producto> productos;
    
    private int calificacion;
}
