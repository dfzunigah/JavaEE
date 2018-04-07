package entidades;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Daniel Zuñiga (05/04/18) @ UEC
 **/
@Entity
@NamedQueries({
@NamedQuery(name = "findAllBuyers", query = "SELECT c FROM Comprador c")
})
/*De esta manera podemos distinguir entre comprador (C) y vendedor (V).*/
@DiscriminatorValue(value="C")
@XmlAccessorType(XmlAccessType.FIELD)
public class Comprador extends Persona{
    @XmlTransient
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
