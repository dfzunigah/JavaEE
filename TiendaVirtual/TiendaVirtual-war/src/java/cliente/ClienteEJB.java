package cliente;

import entidades.Comprador;
import entidades.InformaciónEnvio;
import entidades.InformaciónFactura;
import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.AdministracionOrdenLocal;
import logica.AdministracionPersistenciaJPALocal;
//import logica.AdministracionPersistenciaLocal;

/**
 * @author Daniel Zuñiga (21/03/18) @ UEC
 **/
/*Un servlet permite simular la interfaz. Se caracterizan por:
  1. Se puede invocar directamente desde la URL.
  2. Permite poner código embebido HTML.*/
public class ClienteEJB extends HttpServlet {
    
    /*Acá se le inyectan las dependencias, no hay necesidad de crearlos con "new".*/
    /*Acá se hace el cambio de PersistenciaLocal a PersistenciaJPALocal*/
    @EJB
    AdministracionPersistenciaJPALocal administracionPersistencia;
    
    @EJB
    AdministracionOrdenLocal administracionOrden;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClienteEJB</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteEJB at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            /*Todo esto es simulando lo que haría un usuario.*/
            Producto producto = administracionPersistencia.consultarProducto(1);
            administracionOrden.adicionarProducto(producto);
            producto = new Producto();
            producto = administracionPersistencia.consultarProducto(2);
            administracionOrden.adicionarProducto(producto);
            
            /*Esta línea se agrega en la nueva versión del programa.*/
            Comprador comprador = administracionPersistencia.consultarComprador("maria");
            /*Estas dos línea se reemplazan en la nueva versión.*/
            //Comprador comprador = new Comprador();
            //comprador.setLogin("maria");
            administracionOrden.adicionarComprador(comprador);
            
            InformaciónEnvio informacionEnvio = new InformaciónEnvio();
            informacionEnvio.setCiudad("BOGOTA");
            informacionEnvio.setDepartamento("CUNDINAMARCA");
            informacionEnvio.setPais("COLOMBIA");
            informacionEnvio.setDireccion("CR50 N30-22");
            administracionOrden.adicionarInformacionEnvio(informacionEnvio);
            
            InformaciónFactura informacionFactura = new InformaciónFactura();
            informacionFactura.setCodigoTarjeta("0001");
            informacionFactura.setFechaExpiracion(new Date());
            informacionFactura.setNumeroTarjeta("123456789");
            administracionOrden.adicionarInformacionFactura(informacionFactura);
            
            administracionOrden.crearOrdenCompra();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
