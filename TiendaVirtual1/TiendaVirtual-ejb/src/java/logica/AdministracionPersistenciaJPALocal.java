package logica;

import entidades.Bitacora;
import entidades.Comprador;
import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Orden;
import entidades.Producto;
import java.util.List;
import javax.ejb.Local;

/**
 * @author Daniel Zuñiga (21/03/18) @ UEC
 **/
@Local
public interface AdministracionPersistenciaJPALocal {

    public Producto consultarProducto(int idProducto);

    public Integer crearOrden(Orden orden);

    public Integer crearInformacionEnvio(InformaciónEnvio ie);

    public Integer crearInformacionFactura(InformaciónFactura infFac);

    public void modificarProductos(List<Producto> productos, Orden orden);
    
    public Comprador consultarComprador(String login);
    
    public List<Producto> consultarProductos();
    
    public Integer crearBitacora(Bitacora bitacora);
    
    public List<Comprador> consultarCompradores();
}
