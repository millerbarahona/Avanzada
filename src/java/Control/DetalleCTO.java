/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.DTO.Detalle_Pedido;
import Modelo.DTO.Pedido;
import Modelo.DTO.Producto;
import Modelo.DTO.SesionApp;
import Modelo.DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class DetalleCTO extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        Facade obF = new Facade();
        if (menu.equalsIgnoreCase("Producto")) {
            Facade objF = new Facade();
            switch (accion) {
                case "Listar":
                    HttpSession sesion = request.getSession();
                    SesionApp usuRegis = (SesionApp) sesion.getAttribute("login");
                    Pedido pedido = new Pedido();
                    pedido.setEstado("Carrito");
                    pedido.setId_usu(usuRegis.getUsuario());
                    pedido = this.validar(pedido);

                    List<Detalle_Pedido> listapedi = objF.verDetaPedidoByPe(pedido);//trae todos los pedidos con el id del usuario
                    List<Producto> list = null;
                    list = new ArrayList<>();
                    for (Detalle_Pedido i : listapedi) {
                        Producto aux = new Producto();
                        aux.setId(i.getProductoId());
                        list.add(obF.verProducto(aux));//se tare los productos que hay en el pedido
                    }
                    List<Producto> aux1 = list;
                    int total=this.total(listapedi);
                    request.setAttribute("lista", aux1);
                    request.setAttribute("detalle", listapedi);
                    request.setAttribute("total1", total);
                    break;

                case "Eliminar":
                    String id_Pro = request.getParameter("idPro");
                    String id_Ped = request.getParameter("idPed");
                    Detalle_Pedido pedido2 = new Detalle_Pedido();
                    pedido2.setId_pedido(Integer.parseInt(id_Ped));
                    pedido2.setProductoId(Integer.parseInt(id_Pro));
                    obF.eliminarDetalle(pedido2);
                    request.getRequestDispatcher("DetalleCTO?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    int id = Integer.parseInt(request.getParameter("idPro"));
                    String id_Ped1 = request.getParameter("idPed");
                    String cantidad = request.getParameter("cantidades");
                    System.out.println(cantidad+ "cantidadeslptm");
                    VentaCTO vali = new VentaCTO();
                    if (vali.valiRes(id, Integer.parseInt(cantidad))) {
                        Detalle_Pedido pedido1 = new Detalle_Pedido();
                        pedido1.setId_pedido(Integer.parseInt(id_Ped1));
                        pedido1.setCant_und(Integer.parseInt(cantidad));
                        pedido1.setProductoId(id);
                        obF.UpdateDetalle(pedido1);
                    } else {
                        System.out.println("mas de la cuenta");
                    }
                    request.getRequestDispatcher("DetalleCTO?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Comprar":
                    HttpSession sesion2 = request.getSession();
                    SesionApp usuRegis2 = (SesionApp) sesion2.getAttribute("login");
                    Pedido pedido3 = new Pedido();
                    pedido3.setEstado("Carrito");
                    pedido3.setId_usu(usuRegis2.getUsuario());
                    pedido3 = this.validar(pedido3);
                    ////traer el pedido
                    List<Detalle_Pedido> listapedi2 = objF.verDetaPedidoByPe(pedido3);//trae todos los pedidos con el id del usuario
                    List<Producto> list2 = null;
                    list2 = new ArrayList<>();
                    int totalf = total(listapedi2);
                    for (Detalle_Pedido i : listapedi2) {
                        Producto aux = new Producto();
                        aux.setId(i.getProductoId());
                        aux=obF.verProducto(aux);
                        aux.setUnidades(aux.getUnidades()-i.getCant_und());
                        obF.actualizarP(aux);
                        obF.eliminarDetalle(i);
                    }
                    String observacion = request.getParameter("observaciones");
                    Pedido pedido4 =new Pedido();
                    pedido4=pedido3;
                    pedido4.setObservaciones(observacion);
                    pedido4.setEstado("En verificacion");
                    pedido4.setValor_total(totalf);
                    
                    System.out.println("id pedido "+pedido4.getId_pedido()+" valor total"+pedido4.getValor_total()+" id us "+pedido4);
                    obF.actualizarPedi(pedido4);
                    break;
            }
            request.getRequestDispatcher("DetallePedidoVTA.jsp").forward(request, response);
        }
    }

    public Pedido validar(Pedido exis) {
        Facade obF = new Facade();
        Pedido nuevo = obF.pedidoUC(exis);
        if (nuevo != null) {
            return nuevo;
        } else {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            exis.setFecha_hora(date);
            exis.setObservaciones("");
            obF.crearPedi(exis); // crear pedido
            nuevo = obF.verPedido(exis);
            return nuevo;
        }
    }
    public int total(List<Detalle_Pedido> listapedi) {
        Facade obF = new Facade();
        int total=0;
        Producto pro=null;
               pro= new Producto();
        for (Detalle_Pedido i : listapedi) {
            Producto aux = new Producto();
            aux.setId(i.getProductoId());
            pro=obF.verProducto(aux);
            total= total+((pro.getValor()+(pro.getId()*pro.getValor())/100)*i.getCant_und());//se tare los productos que hay en el pedido
        }
        return total;
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
