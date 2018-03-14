package entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Daniel Zuñiga (13/04/18) @ UEC
 **/
@Entity
public class InformaciónEnvio {
    @Id
    private int id;
    
    private String pais;
    private String departamento;
    private String ciudad;
    private String direccion;
    
}
