/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.DTO.SesionApp;
import Modelo.DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javafx.scene.AccessibleAttribute;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "UsuarioCTO", urlPatterns = {"/UsuarioCTO"})
public class UsuarioCTO extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equalsIgnoreCase("Usuario")) {
            Facade objF = new Facade();
            switch (accion) { 
                case "Listar":
                    request.getRequestDispatcher("Registrar.jsp").forward(request, response);
                    break;
                case "Registrar":
                    System.out.println("entra a registrar");
                    String nombres = request.getParameter("txt_nombres");
                    String apellidos = request.getParameter("txt_apellidos");
                    String tipoDoc = request.getParameter("txt_tipo");
                    String numDoc = request.getParameter("txt_n_iden");
                    String correo = request.getParameter("txt_correo");
                    String clave = request.getParameter("txt_clave");
                    clave= objF.cifrarAES_CBC(clave);
                    String ciudad = request.getParameter("txt_ciudad");
                    String direccion = request.getParameter("txt_direccion");
                    Usuario usuario = new Usuario(nombres, apellidos, tipoDoc, numDoc, ciudad, direccion,"comprador");
                    objF.crearUsuario(usuario);              
                    System.out.println(usuario.getNumero_docto()+" docUsu");
                    usuario=objF.verUsuarioPorDocto(usuario);
                    System.out.println(usuario.getId_usu()+" id usu");
                    SesionApp sesion = new SesionApp(usuario.getId_usu(), correo, clave, true);
                    objF.crearSesion(sesion);
                    
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
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
