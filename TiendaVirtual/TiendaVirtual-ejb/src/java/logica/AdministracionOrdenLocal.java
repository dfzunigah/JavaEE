package logica;

import entidades.Comprador;
import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Producto;
import excepciones.CreacionOrdenException;
import excepciones.ModificacionProductoException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remove;

/**
 * @author Daniel Zuñiga (04/04/18) @ UEC
 **/
@Local
public interface AdministracionOrdenLocal {
    public void adicionarComprador(Comprador comprador);
    
    public void adicionarInformacionFactura(InformaciónFactura informacionFactura);
    
    public void adicionarInformacionEnvio(InformaciónEnvio informacionEnvio);
    
    /*Estos métodos indican que después de ejecutarse, el objeto se destruya.*/
    /*Como en este método se ejecutan los dos métodos entonces cada una puede
      lanzar un error por eso coloco el error desde acá.*/
    @Remove
    public Integer crearOrdenCompra() throws CreacionOrdenException, ModificacionProductoException;
    
    @Remove
    public void cancelarOrdenCompra();
    
    public void adicionarProducto(Producto producto);
    
    public Comprador getComprador();
    
    public List consultarCarroCompras();
}
