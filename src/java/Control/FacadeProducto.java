/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.DAO.ProductoDAO;
import Modelo.DTO.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FacadeProducto {
    public boolean actualizarProducto(Producto obj){
        ProductoDAO pp = new  ProductoDAO();
        if(obj!=null){
            return pp.update(obj);
        }
        return false;
    }
    public boolean create(Producto obj){
        ProductoDAO pp = new  ProductoDAO();
        if(obj!=null){
            return pp.create(obj);
        }
        return false;
    }
    public boolean dalateProducto(Producto obj){
        ProductoDAO pp = new  ProductoDAO();
        if(obj!=null){
            return pp.dalete(obj);
        }
        return false;
    }
    public List<Producto> listaProducto(){
        List<Producto> lista= new ArrayList<>();
        ProductoDAO pp = new  ProductoDAO();
        lista=pp.readAll();
        return lista;
    }
    public Producto readProducto(Producto index){
        Producto obj = new Producto();
        ProductoDAO pp = new  ProductoDAO();
        if(index!=null){
            obj=pp.read(index);
        }
        return obj;
    }
}
