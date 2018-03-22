package entidades;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Daniel Zuñiga (13/03/18) @ UEC
 **/
@Entity
@DiscriminatorValue(value="V")
public class Vendedor extends Persona{
    //Acá no necesita el @Id debido a que hereda de persona, sino lo hiciera pailas sale error
    @OneToMany(mappedBy="vendedor")
    private List<Producto> productos;
    
    private int calificacion;

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
