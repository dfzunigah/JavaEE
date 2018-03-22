package entidades;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author Daniel Zuñiga (21/03/18) @ UEC
 **/
@Entity
@NamedQueries({
@NamedQuery(name = "findAllBuyers", query = "SELECT c FROM Comprador c")
})
/*De esta manera podemos distinguir entre comprador (C) y vendedor (V).*/
@DiscriminatorValue(value="C")
public class Comprador extends Persona{
    @OneToMany(mappedBy="comprador")
    private List<Orden> ordenes;
    
    @Column(name="CANTIDAD_COMPRAS")
    private int cantidadCompras;

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }
}
