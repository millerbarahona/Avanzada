package Modelo.DAO;

import Modelo.DTO.SesionApp;
import Modelo.DTO.Usuario;
import conexion.ConexionMsql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SesionAppDAO {

    private static final String SQL_READ = "select * from tb_sesion_app where id_usu = ?";
    private static final String SQL_READ_ALL = "select * from tb_sesion_app";
    private static final String SQL_INSERT = "insert into tb_sesion_app (id_usu,correo, clave, estado) VALUES(?,?,?,?)";
    private static final String SQL_DELETE = "delete from tb_sesion_app where id_usu = ?";
    private static final String SQL_LOGIN = " select * from tb_sesion_app where correo=? and clave= ?";
    private static final String SQL_UPDATE = "update tb_usuario set id_usu = ?, correo = ?, clave = ?, estado = ?";
    private static final ConexionMsql cnx = ConexionMsql.getInstance();

    public boolean create(SesionApp nuevo) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, nuevo.getUsuario());
            ps.setString(2, nuevo.getCorreo());
            ps.setString(3, nuevo.getClave());
            ps.setBoolean(4, nuevo.isEstado());

            if (ps.executeUpdate() > 0) {
                System.out.println("se agrego");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return false;
    }

    public List<SesionApp> getSesion() {
        List<SesionApp> listusu = null;
        if (cnx.getcnn() != null) {
            PreparedStatement psmt;
            try {
                psmt = cnx.getcnn().prepareStatement(SQL_READ_ALL);
                ResultSet rs = psmt.executeQuery();
                listusu = new ArrayList<>();
                while (rs.next()) {
                    SesionApp aux = new SesionApp(rs.getInt("id_usu"), rs.getString("correo"), rs.getString("clave"), rs.getBoolean("estado"));
                    listusu.add(aux);
                }

            } catch (SQLException ex) {
                System.out.println("Error obtener: " + ex.getMessage());
            } finally {
                cnx.cerrarConexion();
            }
        }
        return listusu;
    }
    public boolean update(SesionApp upt) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, upt.getUsuario());
            ps.setString(2, upt.getCorreo());
            ps.setString(3, upt.getClave());
            ps.setBoolean(4, upt.isEstado());
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return false;
    }
}
