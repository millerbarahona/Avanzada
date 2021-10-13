
package Modelo.DAO;

import Modelo.DTO.Producto;
import conexion.ConexionMsql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ProductoDAO {
    private static final String SQL_READ = "select * from tb_producto where id_pro = ?";
    private static final String SQL_READ_ALL = "select * from tb_producto";
    private static final String SQL_INSERT = "insert into tb_producto (nombre_pro,descripcion_pro,imagen,und_dis,valor_pro,iva_pro) VALUES(?,?,?,?,?,?)";
    private static final String SQL_DELETE = "delete from tb_producto where id_pro = ?";
    private static final String SQL_UPDATE = "update tb_producto set nombre_pro = ?,descripcion_pro = ?,imagen = ?,und_dis = ?,valor_pro = ?,iva_pro = ? where id_pro =?";
    private static final ConexionMsql cnx = ConexionMsql.getInstance();
    
     public boolean create(Producto nuevo) {
        PreparedStatement ps;
        try {
            ps = cnx.getcnn().prepareStatement(SQL_INSERT);
            ps.setString(1, nuevo.getNombre());
            ps.setString(2, nuevo.getDescripcion());
            ps.setString(3, nuevo.getImagen());
            ps.setInt(4, nuevo.getUnidades());
            ps.setInt(5, nuevo.getValor());
            ps.setInt(6, nuevo.getIva());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        } finally {
            cnx.cerrarConexion();
        }
        return false;
    }
    public List<Producto> readAll(){
        List<Producto> lst= null;
        PreparedStatement psnt;
        
        if(cnx!=null){
        try {
            psnt= cnx.getcnn().prepareStatement(SQL_READ_ALL);
            ResultSet rs = psnt.executeQuery();
            lst= new ArrayList<>();
            while (rs.next()) {
                Producto obj= new Producto(rs.getInt("id_pro"),
                rs.getString("nombre_pro"), rs.getString("descripcion_pro"),
                rs.getString("imagen"), rs.getInt("und_dis"), rs.getInt("valor_pro"),
                rs.getInt("iva_pro"));
                lst.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cnx.cerrarConexion();
        }
        }
        return lst;
    }
     public boolean dalete(Producto item){
        PreparedStatement ps;
        try {
            ps= cnx.getcnn().prepareStatement(SQL_DELETE);
            ps.setInt(1, item.getId());
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean update(Producto item){
         try {
            PreparedStatement ps;
            ps=cnx.getcnn().prepareStatement(SQL_UPDATE);
            
            ps.setString(1, item.getNombre());
            ps.setString(2, item.getDescripcion());
            ps.setString(3, item.getImagen());
            ps.setInt(4, item.getUnidades());
            ps.setInt(5, item.getValor());
            ps.setInt(6, item.getIva());
            ps.setInt(7, item.getId());
            
            if (ps.executeUpdate()>0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cnx.cerrarConexion();
        }
        return false;
    }
    
    public Producto read(Producto index){
        Producto pro= null;
        PreparedStatement psnt;
        
        if(cnx!=null){
        try {
            psnt= cnx.getcnn().prepareStatement(SQL_READ);
            System.out.println(index.getId());
            psnt.setInt(1, index.getId());
            ResultSet rs = psnt.executeQuery();
            
            if (rs.next()) {
                pro= new Producto(rs.getInt("id_pro"),
                rs.getString("nombre_pro"), rs.getString("descripcion_pro"),
                rs.getString("imagen"), rs.getInt("und_dis"), rs.getInt("valor_pro"),
                rs.getInt("iva_pro"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cnx.cerrarConexion();
        }
        }
        return pro;
    }

}
