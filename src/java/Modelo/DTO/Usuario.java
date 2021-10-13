package Modelo.DTO;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id_usu;
    private String nombres, apellidos, tipo_docto, numero_docto, ciudad_residencia, direccion, rol;

    public Usuario() {
    }

    public int getId_usu() {
        return id_usu;
    }

    public Usuario(int id_usu) {
        this.id_usu = id_usu;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipo_docto() {
        return tipo_docto;
    }

    public void setTipo_docto(String tipo_docto) {
        this.tipo_docto = tipo_docto;
    }

    public String getNumero_docto() {
        return numero_docto;
    }

    public void setNumero_docto(String numero_docto) {
        this.numero_docto = numero_docto;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario(int id_usu, String nombres, String apellidos, String tipo_docto, String numero_docto, String ciudad_residencia, String direccion, String rol) {
        this.id_usu = id_usu;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo_docto = tipo_docto;
        this.numero_docto = numero_docto;
        this.ciudad_residencia = ciudad_residencia;
        this.direccion = direccion;
        this.rol = rol;
    }

    public Usuario(String nombres, String apellidos, String tipo_docto, String numero_docto, String ciudad_residencia, String direccion, String rol) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo_docto = tipo_docto;
        this.numero_docto = numero_docto;
        this.ciudad_residencia = ciudad_residencia;
        this.direccion = direccion;
        this.rol = rol;
    }

    public Usuario(String nombres, String apellidos, String tipo_docto, String numero_docto, String ciudad_residencia, String direccion) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo_docto = tipo_docto;
        this.numero_docto = numero_docto;
        this.ciudad_residencia = ciudad_residencia;
        this.direccion = direccion;
    }
}
