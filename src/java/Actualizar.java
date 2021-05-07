/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Esta se encarga de la conexion con la base de datos
import java.sql.Connection;
import java.sql.DriverManager;
// para poder utilizar insert, update, create, alter, drop
import java.sql.Statement;
//Esta se encarga de generar un objeto para poder realizar las consultas sql
import java.sql.ResultSet;
import javax.servlet.ServletConfig;

/**
 *
 * @author aleja
 */
public class Actualizar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // variables globales
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    // constructor 
    public void init(ServletConfig cfg) throws ServletException{
        
        // como se va a conectar a la base de datos
        String url = "jdbc:mysql:3306//localhost/registro";
                
        String userName = "root";
        String password = "!Chomps123";
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost/registro";    
            con = DriverManager.getConnection(url, userName, password);
            set = con.createStatement();
            
            System.out.println("Conexion Exitosa");
            
        }catch(Exception e){
            System.out.println("Conexion no exitosa");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String nom, appat, apmat, correo, aux;
            int edad = 0;
            
            nom = request.getParameter("nombre2");
            appat = request.getParameter("appat2");
            apmat = request.getParameter("apmat2");
            correo = request.getParameter("correo2");
            aux = request.getParameter("edad2");
            if(aux != ""){
                edad = Integer.parseInt(request.getParameter("edad2"));
            }
            int id;
            id = Integer.parseInt(request.getParameter("ideliminar2"));
            
            
            //vamos a intentar regitrar en la base de datos
            try{
                String q = "update Musuario set nom_usu='" +nom+ "' where id_usu="+id;
                if(nom != ""){
                    set.executeUpdate(q);
                }
                q = "update Musuario set appat_usu='" +appat+ "' where id_usu="+id;
                if(appat != ""){
                    set.executeUpdate(q);
                }
                q = "update Musuario set apmat_usu='" +apmat+ "' where id_usu="+id;
                if(apmat != ""){
                    set.executeUpdate(q);
                }
                q = "update Musuario set correo_usu='" +correo+ "' where id_usu="+id;
                if(correo != ""){
                    set.executeUpdate(q);
                }
                q = "update Musuario set edad_usu='" +edad+ "' where id_usu="+id;
                if(edad != 0){
                    set.executeUpdate(q);
                }
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<link rel='stylesheet' href='./CSS/masterstyle.css'>");
                out.println("<title>Servlet Registro</title>");            
                out.println("</head>");
                out.println("<h1>La actualizacion del usuario fue exitosa</h1>");
                out.println("<body>");
                        if(nom != ""){
                            out.println("<br>");
                            out.println("Tu nuevo nombre es: " + nom);
                        }
                        if(appat != ""){
                            out.println("<br>");
                            out.println("Tu nuevo apellido paterno es: " + appat);
                        }
                        if(apmat != ""){
                            out.println("<br>");
                            out.println("Tu nuevo apellido materno es: " + apmat);
                        }
                        if(edad != 0){
                            out.println("<br>");
                            out.println("Tu nueva edad es: " + edad);
                        }
                        if(correo != ""){
                            out.println("<br>");
                            out.println("Tu nuevo email es: " + correo);
                        }
                out.println("<br>");
                out.println("<br>"
                        + "<a href='index.html'>Regresar al Menu Principal</a>");
                out.println("</body>"); 
                out.println("</html>");
                
                //set.close();
                
            }catch(Exception e){
                System.out.println("Error al registrar en la tabla");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Registro</title>");            
                out.println("</head>");
                out.println("<body>"
                        + "<br>");
                out.println("<h1>La actualizacion del usuario NO fue exitosa, ocurrio un error</h1>"
                        + "<a href='index.html' >Regresar al Menu Principal</a>");
                out.println("</body>"); 
                out.println("</html>");
            }
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
    public void destroy(){
        try{
            con.close();
        }catch(Exception e){
            super.destroy();
        }
    }
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
