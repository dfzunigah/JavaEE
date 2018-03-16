package entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
/*Esta clase es no misional, esta es para dar seguridad.*/
@Entity(name="GRUPO_PERSONA")
public class GrupoPersona implements Serializable {
    /*Acá le estamos diciendo que va a embeber ese ID. ¿Cómo sabe cúal Embedded?
      pues con el tipo de clase del ID.*/
    @EmbeddedId
    private GrupoPersonaPK id;
    
    @ManyToOne
    /*Esto hace que mapee la persona como un "login" de manera que no se repita.
      En caso de no colocarlo se va a generar otro campo "persona".*/
    @MapsId("login")
    @JoinColumn(referencedColumnName = "login", nullable = false, name = "LOGIN")
    private Persona persona;

    public GrupoPersonaPK getId() {
        return id;
    }

    public void setId(GrupoPersonaPK id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
