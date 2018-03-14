package entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
public class Orden {
    @Id
    private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @OneToMany(mappedBy="orden")
    private List<Producto> productos;
    
    @ManyToOne(optional=false)
    private Comprador comprador;
    
    @OneToOne(optional=false)
    private InformaciónFactura informaciónFactura;
    
    @OneToOne(optional=false)
    private InformaciónEnvio informaciónEnvio;
}
