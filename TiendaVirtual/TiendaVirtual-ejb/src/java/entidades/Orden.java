package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel Zuñiga (13/03/18) @ UEC
 **/
@Entity
public class Orden implements Serializable {
    /*El orden en el que yo defino mis @ no altera el resultado.*/
    @Id
    /*Esta es ña estrategia para que sea incremental secuencial. Otras opciones
      son SEQUENCE, TABLE y AUTO. Sin embargo en otras bases de datos, para hacer
      que el campo sea incremental se requiere SEQUENCE, no este valor. Acá funcionaría
      bien en SQLSever pero no en Oracle. El .TABLE es portable entre cualquier
      base de datos pero puede ofrecer problemas de desempeño pues le toca ir a buscar.
      El .AUTO selecciona alguno de los otros tres atributos en base al proveedor
      de persistencia, es necesario mirar la documentación.*/
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @OneToMany(mappedBy="orden")
    private List<Producto> productos;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="ID_COMPRADOR")
    private Comprador comprador;
    
    @OneToOne(optional=false)
    @JoinColumn(name="ID_INF_FACTURA")
    private InformaciónFactura informaciónFactura;
    
    @OneToOne(optional=false)
    @JoinColumn(name="ID_INF_ENVIO")
    private InformaciónEnvio informaciónEnvio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public InformaciónFactura getInformaciónFactura() {
        return informaciónFactura;
    }

    public void setInformaciónFactura(InformaciónFactura informaciónFactura) {
        this.informaciónFactura = informaciónFactura;
    }

    public InformaciónEnvio getInformaciónEnvio() {
        return informaciónEnvio;
    }

    public void setInformaciónEnvio(InformaciónEnvio informaciónEnvio) {
        this.informaciónEnvio = informaciónEnvio;
    }
}