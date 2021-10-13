
import Control.Facade;
import Modelo.DTO.Detalle_Pedido;
import Modelo.DTO.Pedido;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class pruebasnombrecambiado {


    public static void main(String[] args) {
                    Facade obF = new Facade();
                     //fecha 
                    //System.out.println(date);
                    Pedido pedido = new Pedido();
                    pedido.setId_pedido(14);
                    pedido.setId_usu(4);
                    pedido.setEstado("Carrito");
                    pedido.setObservaciones("muy caro");
                    pedido.setValor_total(8);
                    obF.actualizarPedi(pedido);
                    /*pedido=obF.pedidoUC(pedido);
                    
                   /* 
                    Detalle_Pedido detalle = new Detalle_Pedido(3, 1, 8); //creando detalle
                    
                    obF.crearDeta(detalle);*/
                   
    }
    
    public static Detalle_Pedido validarDetalle(Detalle_Pedido index){
       Facade obF = new Facade();
       Detalle_Pedido obj1= new Detalle_Pedido();
       obj1=obF.verDetaPedido(index);
        System.out.println(obj1.getCant_und());
       if(obj1==null){
           System.out.println("no exite  se creo detalle");
           obF.crearDeta(index);
       }else{
           System.out.println("existe se añade producto");
           obj1.setCant_und(index.getCant_und()+obj1.getCant_und());
           System.out.println(index.getCant_und()+" + "+ obj1.getCant_und());
           obF.UpdateDetalle(obj1);
       }
        return obj1;
    }
        public static Pedido validar (Pedido exis){
        Facade obF = new Facade();
        Pedido nuevo=obF.pedidoUC(exis);
        if(nuevo!=null){
            System.out.println("existe");
            return nuevo;
        }else{
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            exis.setFecha_hora(date);
            exis.setObservaciones("");
            obF.crearPedi(exis); // crear pedido
            System.out.println("se creo");
             nuevo=obF.verPedido(exis);
             return nuevo;
        }
        
    }
    
}
