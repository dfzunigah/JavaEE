package logica;

import entidades.Bitacora;
import entidades.Comprador;
import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Orden;
import entidades.Producto;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Daniel Zuñiga (22/03/18) @ UEC
 **/
@Stateless
public class AdministracionPersistenciaJPA implements AdministracionPersistenciaJPALocal {
    /*Esto reemplaza el manejo de conexión de la DB, lo de pre y post.*/
    @PersistenceContext
    private EntityManager em;
    /*Este es modelo programatico. Pero acá no se sigue la POA.*/
    @Resource
    private TimerService timerService;
    
    /*Con el EntityManager sencillamente sale todo lo que hicimos ayer sale con una línea.
      El método find sirve para hacer busquedas por llave primaria. Por debajo lo de
      la clase del modelo de dominio lo transforme en SQL. En este caso se transforma
      en un "select * from Producto". Si la llave primaria que se busca no existe en la base
      de daots, se retorna null o una entidad vacía.*/
    @Override
    public Producto consultarProducto(int idProducto) {
        /*Query query = em.createNamedQuery("findProductById");
          Query.setParameter("idProducto",1)*/
        return em.find(Producto.class, idProducto);
    }

    @Override
    public Integer crearOrden(Orden orden) {
        /*El "persist" sirve para hacer persistencia, para colocar registros o
          mejor dicho hace un insert.*/
        em.persist(orden);
        /*Línea añadida para Timer programatico. El parametro debe ser serializable.
          Así si yo coloco el Timer */
        timerService.createTimer(15000, orden);
        return orden.getId();
    }
    
    @Timeout
    private void timerCrearOrden(Timer timer){
        Orden orden = (Orden) timer.getInfo();
        System.out.println("Se ha enviado la orden a la dirección " +
                            orden.getInformaciónEnvio().getDireccion());
    }

    @Override
    public Integer crearInformacionEnvio(InformaciónEnvio ie) {
        em.persist(ie);
        return ie.getId();
    }

    @Override
    public Integer crearInformacionFactura(InformaciónFactura infFac) {
        em.persist(infFac);
        return infFac.getId();
    }

    /*El método "merge" permite hacer update sobre un campo.*/
    @Override
    public void modificarProductos(List<Producto> productos, Orden orden) {
        for (int i = 0; i < productos.size(); i++){
            Producto producto = productos.get(i);
            producto.setOrden(orden);
            em.merge(producto);
        }
    }

    @Override
    public Comprador consultarComprador(String login) {
        return em.find(Comprador.class, login);
    }

    @Override
    public List<Producto> consultarProductos() {
        Query query = em.createNamedQuery("findAllProducts");
        List<Producto> productos = query.getResultList();
        return productos;
    }

    @Override
    public Integer crearBitacora(Bitacora bitacora) {
        em.persist(bitacora);
        return bitacora.getId();
    }

    @Override
    public List<Comprador> consultarCompradores() {
        Query query = em.createNamedQuery("findAllBuyers");
        List<Comprador> compradores = query.getResultList();
        return compradores;
    }
    
    @Schedule(second = "*/20", minute="*", hour="*")
    private void timerHora(){
        System.out.println("Hora TiendaVirtual: " + new Date());
    }
    
}
