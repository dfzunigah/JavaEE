package entidades;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
/*De esta manera podemos distinguir entre comprador (C) y vendedor (V).*/
@DiscriminatorValue(value="C")
public class Comprador extends Persona{
    /*No es necesario colocar @Id pues hereda de persona.*/
    /*Acá el dueño de la relación es Orden y el atributo que lo relaciona sería
      "comprador" del tipo Comprador.*/
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
