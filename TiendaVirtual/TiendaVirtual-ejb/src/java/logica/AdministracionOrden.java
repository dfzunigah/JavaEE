package logica;

import auditoria.CreacionOrdenInterceptor;
import entidades.Comprador;
import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Orden;
import entidades.Producto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

/**
 * @author Daniel Zuñiga (22/03/18) @ UEC
 **/
/*Este es como un carrito de modo que sí requiere recordar su estado.*/
@Stateful
public class AdministracionOrden implements AdministracionOrdenLocal {
    /*Estos son los atributos que se guardan en memoria.*/
    private List<Producto> productos;
    private Comprador comprador;
    private InformaciónFactura informacionFactura;
    private InformaciónEnvio informacionEnvio;
    
    /*Acá se hace el cambio de PersistenciaLocal a PersistenciaJPALocal*/
    @EJB
    AdministracionPersistenciaJPALocal administracionPersistencia;
    
    public AdministracionOrden(){
        productos = new ArrayList<Producto>();
    }
    
    @Override
    public void adicionarProducto(Producto producto) {
        productos.add(producto);
    }
    
    @Override
    public void adicionarComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    @Override
    public void adicionarInformacionFactura(InformaciónFactura informacionFactura) {
        this.informacionFactura = informacionFactura;
    }

    @Override
    public void adicionarInformacionEnvio(InformaciónEnvio informacionEnvio) {
        this.informacionEnvio = informacionEnvio;
    }
    
    
    /*Acá se identifica el interceptor. Se puede ejecutar más de uno y se colocan
      con comas separadas.
      Se puede colocar a nivel de clase y lo que haría es que intercepta todos
      los métodos de esa clase.*/
    @Remove
    @Interceptors(CreacionOrdenInterceptor.class)
    @Override
    public Integer crearOrdenCompra() {
        /*Acá lo que hace con estos dos métodos en guardar a través de AdminPersistencia
          los datos en la DB.*/
        /*Estas dos líneas se eliminan en la nueva versión*/
        //informacionEnvio.setId(administracionPersistencia.crearInformacionEnvio(informacionEnvio));
        //informacionFactura.setId(administracionPersistencia.crearInformacionFactura(informacionFactura));
        
        /*Estas dos líneas se agregaron en la nueva versión.*/
        administracionPersistencia.crearInformacionEnvio(informacionEnvio);
        administracionPersistencia.crearInformacionFactura(informacionFactura);
        
        Orden orden = new Orden();
        orden.setComprador(comprador);
        orden.setFecha(new Date());
        orden.setInformaciónEnvio(informacionEnvio);
        orden.setInformaciónFactura(informacionFactura);
        administracionPersistencia.crearOrden(orden);
        /*Esta línea se elimina en la nueva versión.*/
        //orden.setId(administracionPersistencia.crearOrden(orden));
        
        /*Cuando la orden ya existe lo único que hago es vincular a los productos
          a la orden.*/
        administracionPersistencia.modificarProductos(productos, orden);
        
        return orden.getId();
        
    }

    @Override
    public void cancelarOrdenCompra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*Cada vez que se ejecute este método se va a crear un log de que la persona
      está haciendo una compra.*/
    @Override
    public Comprador getComprador() {
        return comprador;
    }

    @Override
    public List consultarCarroCompras() {
        return this.productos;
    }

}
