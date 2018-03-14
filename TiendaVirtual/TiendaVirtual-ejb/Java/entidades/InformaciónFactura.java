package entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
public class InformaciónFactura {
    @Id
    private int id;
    
    private String numeroTarjeta;
    private String codigoTarjeta;
    
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;
}
