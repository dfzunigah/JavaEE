package logica;

import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Orden;
import entidades.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 * @author Daniel Zuñiga (20/03/18) @ UEC
 **/
/*Este lo que hace es comunicarse con la BD.*/
@Stateless
public class AdministracionPersistencia implements AdministracionPersistenciaLocal {
      
    /*Lo primero que se debe hacer acá en crear y cerrar la conexión con la DB.*/
    private Connection connection;
    /*Para que este datasource referencie al DS que ya creamos. Tiene que tener
      el mismo nombre.*/
    /*Acá la única dependencia que se inyecta a AdministracionPersistencia es DataSource ds,
      porque sólo se inyectan aquellos que estén notados. En el caso de Conecction queda
      referenciado a nulo. Entonces es necesario referenciarlo a la DB haciendo la conexión
      en el postconstruct y borrandola en el predestroy.*/
    @Resource(lookup = "jdbc/tiendaVirtualDB")
    DataSource ds;
    
    /*Esta notación de override indica que va a sobreescribir el método porque ya
      está definido en la intefaz.*/
    @Override
    public Producto consultarProducto(int idProducto) {
        Producto producto = null;
        try {
            String sql = "SELECT ID, NOMBRE FROM PRODUCTO WHERE ID = " + idProducto;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //si la consulta produce resultados se crea objeto producto
            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("ID"));
                producto.setNombre(rs.getString("NOMBRE"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return producto;
    }

    @Override
    public Integer crearOrden(Orden orden) {
        Integer idOrden = null;
        try {
            //inserta registro en la tabla ORDEN
            String sql = "INSERT INTO ORDEN (FECHA, ID_INF_ENVIO, ID_INF_FACTURA, ID_COMPRADOR) "
                    + "VALUES(CURRENT_TIMESTAMP, " + orden.getInformaciónEnvio().getId()
                    + ", " + orden.getInformaciónFactura().getId()
                    + ", '" + orden.getComprador().getLogin() + "')";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

            //se consulta el ID del registro insertado
            sql = "SELECT MAX(ID) AS ID FROM ORDEN";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                idOrden = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println("Error crearOrden " + ex.toString());
        }
        return idOrden;
    }

    @Override
    public Integer crearInformacionEnvio(InformaciónEnvio ie) {
        Integer idInformacionEnvio = null;
        try {
            //inserta registro en la tabla INFORMACION_ENVIO
            String sql = "INSERT INTO INFORMACION_ENVIO (DIRECCION, CIUDAD, PAIS, DEPARTAMENTO) "
                    + "VALUES ('" + ie.getDireccion() + "', '" + ie.getCiudad() + "', "
                    + "'" + ie.getPais() + "', '" + ie.getDepartamento() + "')";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

            //se consulta el ID del registro insertado
            sql = "SELECT MAX(ID) AS ID FROM INFORMACION_ENVIO";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                idInformacionEnvio = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println("Error crearInformacionEnvio " + ex.toString());
        }
        return idInformacionEnvio;
    }

    @Override
    public Integer crearInformacionFactura(InformaciónFactura infFac) {
        Integer idInformacionFactura = null;
        try {
            //inserta registro en la tabla INFORMACION_FACTURA
            String sql = "INSERT INTO INFORMACION_FACTURA " +
                    "(CODIGO_TARJETA, NUMERO_TARJETA, FECHA_EXPIRACION) "
                    + "VALUES ('" + infFac.getCodigoTarjeta() + "', '" + infFac.getNumeroTarjeta() + "', "
                    + "CURRENT_DATE)";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);

            //se consulta el ID del registro insertado
            sql = "SELECT MAX(ID) AS ID FROM INFORMACION_FACTURA";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                idInformacionFactura = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println("Error crearInformacionFactura " + ex.toString());
        }
        return idInformacionFactura;
    }

    @Override
    public void modificarProductos(List<Producto> productos, Orden orden) {
        try {
            //asigna número de orden a los productos comprados
            String sql;
            for (int i = 0; i < productos.size(); i++) {
                Producto producto = productos.get(i);
                sql = "UPDATE PRODUCTO SET ID_ORDEN = " + orden.getId()
                        + " WHERE ID = " + producto.getId();
                Statement st = connection.createStatement();
                st.executeUpdate(sql);
            }

        } catch (SQLException ex) {
            System.out.println("Error modificarProductos " + ex.toString());
        }
    }
    
    /*Se ejecuta despúes de haber creado los objetos e insertado las dependencias.*/
    @PostConstruct
    private void inicializar(){
        try{
            connection = ds.getConnection();
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
    @PreDestroy
    private void limpiar(){
        try{
            connection.close();
            connection = null;
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
}
