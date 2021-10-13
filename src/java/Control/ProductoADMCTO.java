/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.DTO.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class ProductoADMCTO extends HttpServlet {

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
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        FacadeProducto obj = new FacadeProducto();
        if (menu.equalsIgnoreCase("Usuario")) {
            switch (accion) {
                case "Listar":
                    List<Producto> lis = obj.listaProducto();
                    request.setAttribute("lista_productos", lis);
                    break;
                case "Agregar":
                    String nombre = request.getParameter("txt_nombre_pro");
                    String des = request.getParameter("txt_descripcion.pro");
                    String imagen = request.getParameter("txt_imagen.pro");
                    int und = Integer.parseInt(request.getParameter("txt_unidades.pro"));
                    int val = Integer.parseInt(request.getParameter("txt_valor.pro"));
                    int iva = Integer.parseInt(request.getParameter("txt_Iva.pro"));
                    Producto nuevo = new Producto(nombre, des, imagen, und, val, iva);
                    obj.create(nuevo);
                    request.getRequestDispatcher("Producto?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    Producto edi = new Producto();
                    edi.setId(Integer.parseInt(request.getParameter("txt_id_pro")));
                    edi = obj.readProducto(edi);
                    request.setAttribute("producto", edi);
                    request.getRequestDispatcher("Producto?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actulizar":

                    String nombre1 = request.getParameter("txt_nombre_pro");
                    String des1 = request.getParameter("txt_descripcion.pro");
                    String imagen1 = request.getParameter("txt_imagen.pro");
                    int und1 = Integer.parseInt(request.getParameter("txt_unidades.pro"));
                    int val1 = Integer.parseInt(request.getParameter("txt_valor.pro"));
                    int iva1 = Integer.parseInt(request.getParameter("txt_Iva.pro"));
                    Producto nuevo1 = new Producto(nombre1, des1, imagen1, und1, val1, iva1);
                    obj.actualizarProducto(nuevo1);
                    request.getRequestDispatcher("ProductoCTO?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":

                    Producto borrar = new Producto();
                    borrar.setId(Integer.parseInt(request.getParameter("txt_id_pro")));
                    obj.dalateProducto(borrar);
                    request.getRequestDispatcher("Producto?menu=Producto&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("ProductoADMVTA.jsp").forward(request, response);
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
