package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Daniel Zuñiga (13/03/18) @ UEC
 **/
//Colocando esto se reconoce la clase como una entidad.
//Todas las anotaciones que estamos haciendo son de la JPA API.
@Entity
/*Con esta notación le puedo dar un nombre especifíco al header de la DB.
  La notación TABLE permite modificar a lo largo de header.*/
@Table(name="TIPO_IDENTIFICACION")
/*Acá nos da la opción de implementar la "seralización" para poder guardar en
  disco secundario. La idea es que si es serializable podría realizar este proceso
  en otras máquinas. De lo contrario, funciona bien pero sólo local.*/
public class TipoIdentificacion implements Serializable {
    //Colocando esto se reconoce este atributo como la llave primaria.
    @Id
    /*La notación @Column permite modificar a lo largo de atributos.
      De esta manera delimitamos el largo de este atributo.*/
    @Column (length=2)
    private String id;
    
    //El nullable=false indica que acá no se aceptan nulos, siempre tiene que haber algo.
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
