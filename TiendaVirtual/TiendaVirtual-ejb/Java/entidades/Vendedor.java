package entidades;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
@DiscriminatorValue(value="V")
public class Vendedor extends Persona{
    /*No es necesario colocar @Id pues hereda de persona.*/
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
