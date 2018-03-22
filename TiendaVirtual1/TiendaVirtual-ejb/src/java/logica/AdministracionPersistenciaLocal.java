package logica;

import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Orden;
import entidades.Producto;
import java.util.List;
import javax.ejb.Local;

/**
 * @author Daniel Zuñiga (20/03/18) @ UEC
 **/
@Local
public interface AdministracionPersistenciaLocal {
    /*Va y hace un select en la DB*/
    public Producto consultarProducto(int idProducto);
    /*Hace un insert en la DB. Retorna un integer correspondiente al ID de la orden que acaba de crear.*/
    public Integer crearOrden(Orden orden);
    /*Se hace un insert en sus correspondientes DB y devuelve sus ID.*/
    public Integer crearInformacionEnvio(InformaciónEnvio ie);
    public Integer crearInformacionFactura(InformaciónFactura infFac);
    /*Vincula la orden con la lista de los productos. Genera un update*/
    public void modificarProductos(List<Producto> productos, Orden orden);
    
}
