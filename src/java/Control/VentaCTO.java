package Control;

import Control.Facade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.DAO.ProductoDAO;
import Modelo.DTO.Detalle_Pedido;
import Modelo.DTO.Pedido;
import Modelo.DTO.Producto;
import Modelo.DTO.SesionApp;
import Modelo.DTO.Usuario;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import javax.servlet.http.HttpSession;

@WebServlet(name = "VentaCTO", urlPatterns = {"/VentaCTO"})
public class VentaCTO extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equalsIgnoreCase("Venta")) {
            Facade objF = new Facade();
            switch (accion) {
                case "Listar":
                    List<Producto> list = null;
                    list=validarSihay(objF.listarP());
                    request.setAttribute("lista", list);
                    break;
                case "Agregar":

                    int id = Integer.parseInt(request.getParameter("idPro")); //tomando id
                    String cantidad = request.getParameter("cantidades"); // tomando cantidad
                    System.out.println("cantidad " + cantidad);
                    HttpSession sesion = request.getSession();
                    SesionApp usuRegis = (SesionApp) sesion.getAttribute("login"); //tomando id usuario
                    if (this.valiRes(id, Integer.parseInt(cantidad))) {
                        Facade obF = new Facade();
                        Pedido pedido = new Pedido();
                        pedido.setEstado("Carrito");
                        pedido.setId_usu(usuRegis.getUsuario());
                        pedido = this.validar(pedido);
                        System.out.println(pedido.getId_pedido());
                        Detalle_Pedido deta = new Detalle_Pedido(pedido.getId_pedido(), id, Integer.parseInt(cantidad));
                        System.out.println(deta.getProductoId() + " " + deta.getCant_und());
                        Detalle_Pedido pepe = this.validarDetalle(deta);
                    } else {
                        System.out.println("mas de la cuenta");
                    }
                    request.getRequestDispatcher("VentaCTO?menu=Venta&accion=Listar").forward(request, response);
                    break;
                /*
                case "Editar":
                    ProductoDTO edit = new ProductoDTO();
                    edit.setId_p(Integer.parseInt(request.getParameter("id")));
                    edit = objF.verProducto(edit);
                    request.setAttribute("producto", edit);
                    request.getRequestDispatcher("ProductoCTO?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    int id1= Integer.parseInt(request.getParameter("txt_id_pro"));
                    String nombre1= request.getParameter("txt_nombre_pro");
                    String des1=request.getParameter("txt_descripcion_pro");
                    int unid1= Integer.parseInt(request.getParameter("txt_unidades_pro"));
                    int val1= Integer.parseInt(request.getParameter("txt_valor_pro"));
                    ProductoDTO actual = new ProductoDTO(id1,nombre1,des1,unid1,val1);
                    objF.actualizarP(actual);
                    request.getRequestDispatcher("ProductoCTO?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    ProductoDTO elimi= new ProductoDTO ();
                    elimi.setId_p(Integer.parseInt(request.getParameter("id")));
                    objF.eliminarP(elimi);
                    request.getRequestDispatcher("ProductoCTO?menu=Producto&accion=Listar").forward(request, response);
                    break;*/
            }
            request.getRequestDispatcher("VentaVTA.jsp").forward(request, response);
        }
    }

    public Pedido validar(Pedido exis) {
        Facade obF = new Facade();
        Pedido nuevo = obF.pedidoUC(exis);
        if (nuevo != null) {
            System.out.println("existe");
            return nuevo;
        } else {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            exis.setFecha_hora(date);
            exis.setObservaciones("");
            obF.crearPedi(exis); // crear pedido
            System.out.println("se creo");
            nuevo = obF.verPedido(exis);
            return nuevo;
        }
    }

    public boolean valiRes(int id, int cant) {
        Facade obF = new Facade();
        Producto prod = new Producto(id);
        Producto inven = obF.verProducto(prod);
        if (inven.getUnidades() - cant < 0) {
            return false;
        } else {
            /*inven.setUnidades(inven.getUnidades() - cant);
            System.out.println(inven.getUnidades() + " " + inven.getId() + " " + inven.getNombre());
            obF.actualizarP(inven);*/
            return true;
        }
    }

    public Detalle_Pedido validarDetalle(Detalle_Pedido index) {
        Facade obF = new Facade();
        Detalle_Pedido obj1 = new Detalle_Pedido();
        obj1 = obF.verDetaPedido(index);
        if (obj1 == null) {
            System.out.println("no exite  se creo detalle");
            obF.crearDeta(index);
        } else {
            System.out.println("existe se añade producto");
            obj1.setCant_und(index.getCant_und() + obj1.getCant_und());
            System.out.println(index.getCant_und() + " + " + obj1.getCant_und());
            obF.UpdateDetalle(obj1);
        }
        return obj1;
    }

    public void sumarTotal(Detalle_Pedido sumar) {
        Facade obF = new Facade();
        System.out.println(sumar.toString());
        Producto pro = new Producto(sumar.getProductoId());
        Producto aux = obF.verProducto(pro);
        int valor = aux.getValor() * sumar.getCant_und();
        Pedido pedi = new Pedido();
        pedi.setId_usu(sumar.getId_pedido());
        Pedido auxP = obF.verPedido(pedi);
        auxP.setValor_total(valor);
        obF.actualizarPedi(pedi);
    }
    public List<Producto> validarSihay(List<Producto> pro){
        List<Producto> list=null;
        for (int i = 0; i < pro.size(); i++) {
            if(pro.get(i).getUnidades()==0){
                pro.remove(i);
            }
        }
        list= pro;
        return list;
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
