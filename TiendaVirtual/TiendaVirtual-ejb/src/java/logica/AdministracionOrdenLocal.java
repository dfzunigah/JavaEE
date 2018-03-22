package logica;

import entidades.Comprador;
import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Producto;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remove;

/**
 * @author Daniel Zuñiga (20/03/18) @ UEC
 **/
@Local
public interface AdministracionOrdenLocal {
    public void adicionarComprador(Comprador comprador);
    
    public void adicionarInformacionFactura(InformaciónFactura informacionFactura);
    
    public void adicionarInformacionEnvio(InformaciónEnvio informacionEnvio);
    
    @Remove
    public Integer crearOrdenCompra();
    
    @Remove
    public void cancelarOrdenCompra();
    
    public void adicionarProducto(Producto producto);
    
    public Comprador getComprador();
    
    public List consultarCarroCompras();
}
