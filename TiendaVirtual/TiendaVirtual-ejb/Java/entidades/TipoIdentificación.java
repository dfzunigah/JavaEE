package entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
//Colocando esto se reconoce la clase como una entidad.
//Todas las anotaciones que estamos haciendo son de la JPA API.
@Entity
public class TipoIdentificación {
    //Colocando esto se reconoce este atributo como la llave primaria.
    @Id
    private String id;
    
    private String descripcion;
}
