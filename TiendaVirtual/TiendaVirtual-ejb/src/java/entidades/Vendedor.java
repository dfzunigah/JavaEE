package entidades;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Daniel Zuñiga (05/04/18) @ UEC
 **/
@Entity
@DiscriminatorValue(value="V")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vendedor extends Persona{
    //Acá no necesita el @Id debido a que hereda de persona, sino lo hiciera pailas sale error
    @XmlTransient
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
