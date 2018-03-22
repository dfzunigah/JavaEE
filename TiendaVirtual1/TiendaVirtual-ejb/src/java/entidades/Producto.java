package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daniel Zuñiga (21/03/18) @ UEC
 **/
@Entity
@NamedQueries({
@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Producto p"),
@NamedQuery(name = "findProductById", query = "SELECT p FROM Producto p WHERE p.id = :idProducto")
})
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(length=30, nullable=false)
    private String nombre;
    
    @Column(length=200)
    private String descripcion;
    private long precio;
    
    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_CREACION")
    private Date fechaCreacion;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="ID_VENDEDOR", nullable=false)
    private Vendedor vendedor;
    
    /*Cuando es @ManyToMany uno elige quién es el dueño de la relación, en caso
      de no colcoar quién es el dueño, como acá, entonces el dueño de la relación
      es el del otro lado.*/
    @ManyToMany
    private List<Categoria> categorias;
    
    /*Este es un campo opcional y sólo va a ser llenado cuando se vincule a una orden.
      Por default es opcional aunque tambien se podría colocar de manera explicita como
      (optional = true)*/
    @ManyToOne
    @JoinColumn(name="ID_ORDEN")
    private Orden orden;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
