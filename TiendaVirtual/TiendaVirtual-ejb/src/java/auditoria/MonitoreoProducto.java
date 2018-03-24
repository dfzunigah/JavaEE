package auditoria;

import entidades.Producto;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;

/**
 * @author Daniel Zuñiga (22/03/18) @ UEC
 **/
public class MonitoreoProducto {
    /*Estas notaciones @Pre/PostUpdate permite ejecutar estos métodos antes de
      que se pasen a la DB. Aquí se podría validar, por ejemplo si da negativo
      o cancelar la ejecucion de la operacion de DB hay que lanzar una excepcion.*/
    
    @PreUpdate
    public void preActualizacionProducto(Producto producto){
        System.out.println("El producto " + producto.getId() + " va a ser "
                           + "asignado a la orden de compra " + producto.getOrden().getId());
    }
    
    @PostUpdate
    public void postActualizacionProducto(Producto producto){
        System.out.println("El producto " + producto.getId() + " fue asignado a "
                           + "la orden de compra " + producto.getOrden().getId());
    }
}
