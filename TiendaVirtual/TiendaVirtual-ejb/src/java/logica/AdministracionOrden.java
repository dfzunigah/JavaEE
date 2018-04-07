package logica;

import auditoria.CreacionOrdenInterceptor;
import entidades.Comprador;
import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Orden;
import entidades.Producto;
import excepciones.CreacionOrdenException;
import excepciones.ModificacionProductoException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

/**
 * @author Daniel Zuñiga (04/04/18) @ UEC
 **/
/*Este es como un carrito de modo que sí requiere recordar su estado.*/
/*El Container está por defecto pero es una buena práctica colocarlo.*/
/*Este atributo de transaccionalidad se coloca a nivel de clase y aplica para
  cada método que no tenga un atributo definido. Como el resto de métodos no
  tienen operaciones en DB sino que son en memoria entonces no necesitan un
  contexto operacional, por eso se les coloca el NOT_SUPPORTED. Es peligroso
  colocar el NEVER porque es posible que antes de esto sí exista un contexto y
  entonces boom, pailas.*/
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AdministracionOrden implements AdministracionOrdenLocal {
    /*Estos son los atributos que se guardan en memoria.*/
    private List<Producto> productos;
    private Comprador comprador;
    private InformaciónFactura informacionFactura;
    private InformaciónEnvio informacionEnvio;
    
  /*En caso de que se quiera que el EntityManager sea el mediador usar
    AdministracionPersistenciaJPALocal.
    En caso de que se quiera hacer el manejo a través de SQL directo usar
    AdministracionPersistenciaLocal. Importar sus respectivos módulos para cada caso.*/
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
    /*No me interesa que hay para atrás, creo un nuevo contexto de transacción.
      Por eso se coloca el REQUIRES_NEW.*/
    @Remove
    @Interceptors(CreacionOrdenInterceptor.class)
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public Integer crearOrdenCompra() throws CreacionOrdenException, ModificacionProductoException{
        /*Acá lo que hace con estos dos métodos en guardar a través de AdminPersistencia
          los datos en la DB.*/
        /*Utilizar estás dos líneas en caso de que use interacción directa con SQL.*/
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
        orden.setProductos(productos);
        administracionPersistencia.crearOrden(orden);
        /*Si se quere usar el EntityManager como mediador, comentar esta línea.
          Si se quiere usar SQL directo implementar esta línea.*/
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
