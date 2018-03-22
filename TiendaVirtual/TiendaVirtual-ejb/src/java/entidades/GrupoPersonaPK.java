package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Daniel Zuñiga (13/03/18) @ UEC
 **/
/*En esta clase se abstraen las llaves primarias (Primary Key - PK). Esto no es
  una entidad. Es una llave primaria que va a ir embebida en otro lado.*/
@Embeddable
public class GrupoPersonaPK implements Serializable {
    @Column(nullable=false, length=12)
    private String login;
    
    @Column(nullable=false, length=12, name= "GRUPO_PERSONA")
    private String grupoPersona;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGrupoPersona() {
        return grupoPersona;
    }

    public void setGrupoPersona(String grupoPersona) {
        this.grupoPersona = grupoPersona;
    }
}
