/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.DTO.Detalle_Pedido;
import Modelo.DTO.Pedido;
import conexion.ConexionMsql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetallePedidoDAO {

    private static final String SQL_READ = "SELECT * FROM tb_detalle_pedido WHERE id_pedido = ?";
    private static final String SQL_READ_IDS = "SELECT * FROM tb_detalle_pedido WHERE id_pedido = ? AND id_prod = ?";
    private static final String SQL_READ_ALL = "select * from tb_detalle_pedido ";
    private static final String SQL_INSERT = "INSERT INTO tb_detalle_pedido (id_pedido, id_prod, cantidad_und) VALUES (?, ?, ?);";
    private static final String SQL_DELETE = "DELETE FROM tb_detalle_pedido WHERE id_pedido = ? AND id_prod = ?";
    private static final String SQL_UPDATE = "UPDATE tb_detalle_pedido SET cantidad_und = ? WHERE id_pedido = ? AND id_prod = ?";
    private static final ConexionMsql cnx = ConexionMsql.getInstance();

    public boolean create(Detalle_Pedido nuevo) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_INSERT);
            ps.setInt(1, nuevo.getId_pedido());
            ps.setInt(2, nuevo.getProductoId());
            ps.setInt(3, nuevo.getCant_und());

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

    public Detalle_Pedido read(Detalle_Pedido index) {
        PreparedStatement psnt;
        Detalle_Pedido obj = null;
        if (cnx != null) {
            try {
                psnt = cnx.getcnn().prepareStatement(SQL_READ_IDS);
                psnt.setInt(1, index.getId_pedido());
                psnt.setInt(2, index.getProductoId());
                ResultSet rs = psnt.executeQuery();
                if (rs.next()) {
                    obj = new Detalle_Pedido(rs.getInt("id_pedido"), rs.getInt("id_prod"), rs.getInt("cantidad_und"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                cnx.cerrarConexion();
            }
        }
        return obj;
    }

    public List<Detalle_Pedido> readByPe(Pedido index) {
        PreparedStatement psnt;
        List<Detalle_Pedido> obj = null;
        if (cnx != null) {
            try {
                psnt = cnx.getcnn().prepareStatement(SQL_READ);
                psnt.setInt(1, index.getId_pedido());
                ResultSet rs = psnt.executeQuery();
                obj = new ArrayList<>();
                while (rs.next()) {
                    Detalle_Pedido aux = new Detalle_Pedido(rs.getInt("id_pedido"), rs.getInt("id_prod"), rs.getInt("cantidad_und"));
                    obj.add(aux);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                cnx.cerrarConexion();
            }
        }
        return obj;
    }

    public boolean update(Detalle_Pedido upt) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_UPDATE);
            ps.setInt(1, upt.getCant_und());
            ps.setInt(2, upt.getId_pedido());
            ps.setInt(3, upt.getProductoId());

            if (ps.executeUpdate() > 0) {
                System.out.println("actualizado");
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return false;
    }

    public boolean Delate(Detalle_Pedido upt) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, upt.getId_pedido());
            ps.setInt(2, upt.getProductoId());

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
