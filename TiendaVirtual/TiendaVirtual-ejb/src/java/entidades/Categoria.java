package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author Daniel Zuñiga (13/03/18) @ UEC
 **/
@Entity
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(length=100)
    private String descripcion;
    
    /*En una relación ManyToMany siempre hay un dueño, uno elige quién es.
      Pero siempre debe haber un dueño, en este caso como en "Categorias"
      no se colocó la notación entonces acá se coloca.*/
    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
