package Modelo.DAO;

import Modelo.DTO.Pedido;
import Modelo.DTO.Usuario;
import conexion.ConexionMsql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private static final String SQL_READ = "select * from tb_pedido where id_usu = ?";
    private static final String SQL_READ_ESTADO = "SELECT * FROM tb_pedido WHERE id_usu = ? AND estado= ?";
    private static final String SQL_READ_ALL = "select * from tb_pedido";
    private static final String SQL_INSERT = "INSERT INTO tb_pedido (id_usu, fecha_hora, valor_total, observaciones, estado)   VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "update tb_pedido set id_usu = ?,valor_total = ?,observaciones = ?,estado = ? where id_pedido = ?";
    private static final ConexionMsql cnx = ConexionMsql.getInstance();

    public boolean create(Pedido nuevo) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_INSERT);
            ps.setInt(1, nuevo.getId_usu());
            ps.setDate(2, nuevo.getFecha_hora());
            ps.setInt(3, nuevo.getValor_total());
            ps.setString(4, nuevo.getObservaciones());
            ps.setString(5, nuevo.getEstado());

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

    public List<Pedido> getPedidos() {
        List<Pedido> listPedi = null;
        if (cnx.getcnn() != null) {
            PreparedStatement psmt;
            try {
                psmt = cnx.getcnn().prepareStatement(SQL_READ_ALL);
                ResultSet rs = psmt.executeQuery();
                listPedi = new ArrayList<>();
                while (rs.next()) {
                    Pedido aux = new Pedido(rs.getInt("id_pedido"), rs.getInt("id_usu"), rs.getInt("valor_total"), rs.getString("observaciones"), rs.getString("estado"), rs.getDate("fecha_hora"));
                    listPedi.add(aux);
                }

            } catch (SQLException ex) {
                System.out.println("Error obtener: " + ex.getMessage());
            } finally {
                cnx.cerrarConexion();
            }
        }
        return listPedi;
    }

    

    public boolean update(Pedido upt) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, upt.getId_usu());
            ps.setInt(2, upt.getValor_total());
            ps.setString(3, upt.getObservaciones());
            ps.setString(4, upt.getEstado());
            ps.setInt(5, upt.getId_pedido());

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
    public Pedido read(Pedido filter) {
        Pedido objRes = null;
        PreparedStatement psmt;
        try {
            psmt = cnx.getcnn().prepareStatement(SQL_READ);
            psmt.setInt(1, filter.getId_usu());
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                objRes = new Pedido(rs.getInt("id_pedido"), rs.getInt("id_usu"), rs.getInt("valor_total"), rs.getString("observaciones"), rs.getString("estado"), rs.getDate("fecha_hora"));
            }
        } catch (SQLException ex) {
            System.out.println("Usuario no encontrado: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return objRes;
    }
    public Pedido leerCarrito(Pedido filter) {
        Pedido objRes = null;
        PreparedStatement psmt;
        try {
            psmt = cnx.getcnn().prepareStatement(SQL_READ_ESTADO);
            psmt.setInt(1, filter.getId_usu());
            psmt.setString(2, filter.getEstado());
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                objRes = new Pedido(rs.getInt("id_pedido"), rs.getInt("id_usu"), rs.getInt("valor_total"), rs.getString("observaciones"), rs.getString("estado"), rs.getDate("fecha_hora"));
            }
        } catch (SQLException ex) {
            System.out.println("Usuario no encontrado: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return objRes;
    }
}
