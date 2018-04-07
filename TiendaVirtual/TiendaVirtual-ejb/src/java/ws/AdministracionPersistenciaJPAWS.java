package ws;

import entidades.Bitacora;
import entidades.Comprador;
import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Orden;
import entidades.Producto;
import excepciones.CreacionOrdenException;
import excepciones.ModificacionProductoException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import logica.AdministracionPersistenciaJPALocal;

/**
 * @author Daniel Zuñiga (05/04/18) @ UEC
 **/
@WebService(serviceName = "AdministracionPersistenciaJPAWS")
@Stateless
public class AdministracionPersistenciaJPAWS {

    @EJB
    private AdministracionPersistenciaJPALocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "consultarProducto")
    public Producto consultarProducto(@WebParam(name = "idProducto") int idProducto) {
        return ejbRef.consultarProducto(idProducto);
    }

    @WebMethod(operationName = "crearOrden")
    public Integer crearOrden(@WebParam(name = "orden") Orden orden) throws CreacionOrdenException {
        return ejbRef.crearOrden(orden);
    }

    @WebMethod(operationName = "crearInformacionEnvio")
    public Integer crearInformacionEnvio(@WebParam(name = "ie") InformaciónEnvio ie) {
        return ejbRef.crearInformacionEnvio(ie);
    }

    @WebMethod(operationName = "crearInformacionFactura")
    public Integer crearInformacionFactura(@WebParam(name = "infFac") InformaciónFactura infFac) {
        return ejbRef.crearInformacionFactura(infFac);
    }

    @WebMethod(operationName = "modificarProductos")
    public void modificarProductos(@WebParam(name = "productos") List<Producto> productos, @WebParam(name = "orden") Orden orden) throws ModificacionProductoException {
        ejbRef.modificarProductos(productos, orden);
    }

    @WebMethod(operationName = "consultarComprador")
    public Comprador consultarComprador(@WebParam(name = "login") String login) {
        return ejbRef.consultarComprador(login);
    }

    @WebMethod(operationName = "consultarProductos")
    public List<Producto> consultarProductos() {
        return ejbRef.consultarProductos();
    }

    @WebMethod(operationName = "crearBitacora")
    public Integer crearBitacora(@WebParam(name = "bitacora") Bitacora bitacora) {
        return ejbRef.crearBitacora(bitacora);
    }

    @WebMethod(operationName = "consultarCompradores")
    public List<Comprador> consultarCompradores() {
        return ejbRef.consultarCompradores();
    }
}