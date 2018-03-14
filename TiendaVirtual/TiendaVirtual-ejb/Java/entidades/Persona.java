package entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
public class Persona {
    //Si no le colocamos el "optional" entonces
    @Id //Lave primaria
    private String login;
    
    @ManyToOne(optional=false) //Esto describe la relación entre Persona y TipoIdentificación
    //El optional=false es para que sea obligatorio que sea de este tipo la relación
    private TipoIdentificación tipoId;
    
    private String numeroIdentificacion;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    
    /*Con .DATE sólo da la fecha. Si yo quiero sólo hora coloco .TIME,
      si yo quiero fecha y hora coloco .TIMESTAMP*/
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
}
