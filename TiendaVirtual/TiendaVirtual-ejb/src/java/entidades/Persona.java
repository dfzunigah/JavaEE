package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel Zuñiga (13/03/18) @ UEC
 **/
@Entity
/*Otras alternativas son SINGLE_TABLE que es el primer esquema, TABLE_PER_CLASS que
  es el segundo esquema y finalmente JOINED que es el tercer esquema.*/
@Inheritance(strategy = InheritanceType.JOINED)
/*Por defecto para el esquema 1 o 3 se crea un campo por default llamado DTYPE que
  indica qué tipo de hijo es. Nosotros podemos cambiar ese default a través de
  esta manera. Se va a llamar "TIPO_PERSONA", va a ser de tipo String y sólo va
  a tener una longitud de 1 (c-v).*/
@DiscriminatorColumn(name="TIPO_PERSONA", discriminatorType=DiscriminatorType.STRING, length=1)
public class Persona implements Serializable {
    //Si no le colocamos el "optional" entonces queda por defecto
    @Id //Lave primaria
    @Column(length = 12)
    private String login;
    
    /*Esto describe la relación entre Persona y TipoIdentificación. El
      optional=false es para que sea obligatorio que sea de este tipo la relación.
      Pero acá no se hacen validaciones, sencillamente se muestra cómo se relaciona
      con la BD, es decir, si alguien le envía un nulo eso llega hasta la BD y allá
      se totea. Para validar existe un FrameWork llamado Bean Validation que usa
      algunas anotaciones que realizan lo que necestio en validación.*/
    @ManyToOne(optional=false) 
    /*Siempre que sea un campo normal, por ejemplo "nombre" se utiliza @Column, en
      cambio si es una relación se utiliza @JoinColumn.*/
    @JoinColumn(name="TIPO_ID")
    private TipoIdentificacion tipoId;
    
    @Column(length=15, nullable=false, name="NUMERO_IDENTIFICACION")
    private String numeroIdentificacion;
    
    @Column(length=40, nullable=false)
    private String nombre1;
    
    @Column(length=40)
    private String nombre2;
    
    @Column(length=40, nullable=false)
    private String apellido1;
    
    @Column(length=40)
    private String apellido2;
    
    @Column(name="FECHA_NACIMIENTO")
    /*Con .DATE sólo da la fecha. Si yo quiero sólo hora coloco .TIME,
      si yo quiero fecha y hora coloco .TIMESTAMP*/
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Column(nullable=false, length=12)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TipoIdentificacion getTipoId() {
        return tipoId;
    }

    public void setTipoId(TipoIdentificacion tipoId) {
        this.tipoId = tipoId;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
