
package Modelo.DTO;

import java.io.Serializable;
import java.util.List;


public class Detalle_Pedido implements Serializable{
        
    private int id_pedido,cant_und;
    private int productoId;

    public Detalle_Pedido(int id_pedido,int productoId, int cant_und ) {
        this.id_pedido = id_pedido;
        this.cant_und = cant_und;
        this.productoId = productoId;
    }

    public Detalle_Pedido() {
    }
    
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getCant_und() {
        return cant_und;
    }

    public void setCant_und(int cant_und) {
        this.cant_und = cant_und;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    @Override
    public String toString() {
        return "Detalle_Pedido{" + "id_pedido=" + id_pedido + ", cant_und=" + cant_und + ", productoId=" + productoId + '}';
    }
}
