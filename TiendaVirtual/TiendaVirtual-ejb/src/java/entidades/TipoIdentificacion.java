package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Daniel Zuñiga (13/03/18) @ UEC
 **/
@Entity
@Table(name="TIPO_IDENTIFICACION")
public class TipoIdentificacion implements Serializable {
    @Id
    @Column (length=2)
    private String id;
    
    @Column(nullable=false, length=30)
    private String descripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
