package Modelo.DAO;

import Modelo.DTO.Usuario;
import conexion.ConexionMsql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String SQL_READ = "select * from tb_usuario where id_usu = ?";
    private static final String SQL_READ_ALL = "select * from tb_usuario";
    private static final String SQL_READ1 = "select * from tb_usuario WHERE num_docto = ?";
    private static final String SQL_INSERT = "insert into tb_usuario (nombres,apellidos,tipo_docto,num_docto,ciudad_residencia,direccion,rol) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_DELETE = "delete from tb_usuario where id_usu = ?";
    private static final String SQL_UPDATE = "update tb_usuario set nombres = ?,apellidos = ?,tipo_docto = ?,num_docto = ?,ciudad_residencia = ?,direccion = ?,rol = ? where id =?";
    private static final ConexionMsql cnx = ConexionMsql.getInstance();

    public boolean create(Usuario nuevo) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_INSERT);
            ps.setString(1, nuevo.getNombres());
            ps.setString(2, nuevo.getApellidos());
            ps.setString(3, nuevo.getTipo_docto());
            ps.setString(4, nuevo.getNumero_docto());
            ps.setString(5, nuevo.getCiudad_residencia());
            ps.setString(6, nuevo.getDireccion());
            ps.setString(7, nuevo.getRol());

            if (ps.executeUpdate() > 0) {
                System.out.println("Se agregó");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return false;
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> listusu = null;
        if (cnx.getcnn() != null) {
            PreparedStatement psmt;
            try {
                psmt = cnx.getcnn().prepareStatement(SQL_READ_ALL);
                ResultSet rs = psmt.executeQuery();
                listusu = new ArrayList<>();
                while (rs.next()) {
                    Usuario aux = new Usuario(rs.getInt("id_usu"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("tipo_docto"), rs.getString("num_docto"), rs.getString("ciudad_residencia"), rs.getString("direccion"), rs.getString("rol"));
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

    public boolean deleteU(Usuario item) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, item.getId_usu());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return false;
    }

    public boolean update(Usuario upt) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_UPDATE);
            ps.setString(1, upt.getNombres());
            ps.setString(2, upt.getApellidos());
            ps.setString(3, upt.getTipo_docto());
            ps.setString(4, upt.getNumero_docto());
            ps.setString(5, upt.getCiudad_residencia());
            ps.setString(6, upt.getDireccion());
            ps.setString(7, upt.getRol());
            ps.setInt(5, upt.getId_usu());

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

    public Usuario read(Usuario filter) {
        Usuario objRes = null;
        PreparedStatement psmt;
        try {
            psmt = cnx.getcnn().prepareStatement(SQL_READ);
            psmt.setInt(1, filter.getId_usu());
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                objRes = new Usuario(rs.getInt("id_usu"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("tipo_docto"), rs.getString("num_docto"), rs.getString("ciudad_residencia"), rs.getString("direccion"), rs.getString("rol"));
            }
        } catch (SQLException ex) {
            System.out.println("Usuario no encontrado: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return objRes;
    }
        public Usuario read1(Usuario filter) {
        Usuario objRes = null;
        PreparedStatement psmt;
        System.out.println("read1para usu");
        try {
            psmt = cnx.getcnn().prepareStatement(SQL_READ1);
            psmt.setString(1, filter.getNumero_docto());
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                System.out.println("Usuario encontrado");
                objRes = new Usuario(rs.getInt("id_usu"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("tipo_docto"), rs.getString("num_docto"), rs.getString("ciudad_residencia"), rs.getString("direccion"), rs.getString("rol"));
            }
        } catch (SQLException ex) {
            System.out.println("Usuario no encontrado: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return objRes;
    }
}
