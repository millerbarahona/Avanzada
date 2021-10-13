package Modelo.DTO;

import java.io.Serializable;
import java.sql.Date;

public class Pedido implements Serializable {

    private int id_pedido;
    private int id_usu ;
    private int valor_total ;
    private String observaciones, estado;
    Date fecha_hora;

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public int getValor_total() {
        return valor_total;
    }

    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Pedido() {
    }

    public Pedido(int id_usu, Date fecha_hora, int valor_total, String observaciones, String estado) {
        this.id_usu = id_usu;
        this.valor_total = valor_total;
        this.observaciones = observaciones;
        this.estado = estado;
        this.fecha_hora = fecha_hora;
    }

    public Pedido(int id_pedido, int id_usu, int valor_total, String observaciones, String estado, Date fecha_hora) {
        this.id_pedido = id_pedido;
        this.id_usu = id_usu;
        this.valor_total = valor_total;
        this.observaciones = observaciones;
        this.estado = estado;
        this.fecha_hora = fecha_hora;
    }

}
