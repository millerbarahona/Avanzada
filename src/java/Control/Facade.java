package Control;

import Modelo.DAO.DetallePedidoDAO;
import Modelo.DAO.PedidoDAO;
import Modelo.DAO.ProductoDAO;
import Modelo.DAO.SesionAppDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.DTO.Detalle_Pedido;
import Modelo.DTO.Pedido;
import Modelo.DTO.Producto;
import Modelo.DTO.SesionApp;
import Modelo.DTO.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Facade {

    public boolean crearUsuario(Usuario objP) {
        boolean rta = false;
        if (objP != null) {
            UsuarioDAO dao = new UsuarioDAO();
            rta = dao.create(objP);
        }
        return rta;
    }

    public List<Usuario> listarU() {
        List<Usuario> list = null;
        UsuarioDAO dao = new UsuarioDAO();
        list = dao.getUsuarios();
        return list;
    }

    public Usuario verUsuario(Usuario item) {
        Usuario objDTO;
        UsuarioDAO objDAO = new UsuarioDAO();
        objDTO = objDAO.read(item);
        return objDTO;
    }

    public boolean actualizarU(Usuario objP) {
        boolean rta = false;
        if (objP != null) {
            UsuarioDAO dao = new UsuarioDAO();
            rta = dao.update(objP);
        }
        return rta;
    }

    public Usuario verUsuarioPorDocto(Usuario item) {
        Usuario objDTO;
        UsuarioDAO objDAO = new UsuarioDAO();
        objDTO = objDAO.read1(item);
        return objDTO;
    }

    public boolean crearSesion(SesionApp nuevo) {
        boolean rta = false;
        if (nuevo != null) {
            SesionAppDAO dao = new SesionAppDAO();
            rta = dao.create(nuevo);
        }
        return rta;
    }
        public boolean actualizarSes(SesionApp objP) {
        boolean rta = false;
        if (objP != null) {
            SesionAppDAO dao = new SesionAppDAO();
            rta = dao.update(objP);
        }
        return rta;
    }

    public boolean eliminarU(Usuario objP) {
        boolean rta = false;
        if (objP != null) {
            UsuarioDAO dao = new UsuarioDAO();
            rta = dao.deleteU(objP);
        }
        return rta;
    }

    public boolean crearProducto(Producto objP) {
        boolean rta = false;
        if (objP != null) {
            ProductoDAO dao = new ProductoDAO();
            rta = dao.create(objP);
        }
        return rta;
    }

    public List<Producto> listarP() {
        List<Producto> list = null;
        ProductoDAO dao = new ProductoDAO();
        list = dao.readAll();
        return list;
    }

    public Producto verProducto(Producto item) {
        Producto objDTO;
        ProductoDAO objDAO = new ProductoDAO();
        objDTO = objDAO.read(item);
        return objDTO;
    }

    public boolean actualizarP(Producto objP) {
        boolean rta = false;
        if (objP != null) {
            ProductoDAO dao = new ProductoDAO();
            rta = dao.update(objP);
        }
        return rta;
    }

    public boolean eliminarP(Producto objP) {
        boolean rta = false;
        if (objP != null) {
            ProductoDAO dao = new ProductoDAO();
            rta = dao.dalete(objP);
        }
        return rta;
    }

    public boolean crearPedi(Pedido nuevo) {
        boolean rta = false;
        if (nuevo != null) {
            PedidoDAO dao = new PedidoDAO();
            rta = dao.create(nuevo);
        }
        return rta;
    }

    public boolean crearDeta(Detalle_Pedido nuevo) {
        boolean rta = false;
        if (nuevo != null) {
            DetallePedidoDAO dao = new DetallePedidoDAO();
            rta = dao.create(nuevo);
        }
        return rta;
    }

    public Pedido verPedido(Pedido item) {
        Pedido objDTO;
        PedidoDAO objDAO = new PedidoDAO();
        objDTO = objDAO.read(item);
        return objDTO;
    }

    public List<Pedido> listarPedi() {
        List<Pedido> list = null;
        PedidoDAO dao = new PedidoDAO();
        list = dao.getPedidos();
        return list;
    }

    public boolean actualizarPedi(Pedido objP) {
        boolean rta = false;
        if (objP != null) {
            PedidoDAO dao = new PedidoDAO();
            rta = dao.update(objP);
        }
        return rta;
    }

    public Pedido pedidoUC(Pedido pe) {
        Pedido objDTO;
        PedidoDAO objDAO = new PedidoDAO();
        objDTO = objDAO.leerCarrito(pe);
        return objDTO;
    }

    public List<String> vector(Producto objP) {
        List<String> list = null;
        list = new ArrayList<>();
        for (int m = 1; m <= objP.getUnidades(); m++) {
            list.add(m + "");
        }
        return list;
    }

    public List<SesionApp> listarSe() {
        List<SesionApp> list = null;
        SesionAppDAO dao = new SesionAppDAO();
        list = dao.getSesion();
        return list;
    }
    private String ClavePriv = "123qAasddGGp7FsA";
    private String VecInicial = "U539DqedpjBf49Bf";

    public SesionApp validacion(SesionApp sesion) {
        List<SesionApp> lista = new ArrayList<>();
        lista = listarSe();
        for (SesionApp i : lista) {
            if (i.getCorreo().equalsIgnoreCase(sesion.getCorreo()) && i.getClave().equalsIgnoreCase(sesion.getClave())) {
                return i;
            }
        }
        return null;
    }

    public String asegurarClave(String clave) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-512");
            System.out.println("clave en bytes sin cifrar" + clave);

            //sha256.update(clave.getBytes("UTF-8"));
            //System.out.println("Clave en bytes cifrada" + sha256.digest());
            //clave= String.format("%064x", new BigInteger(1,sha256.digest()));
            //System.out.println("clave biginteger " + clave);
            //System.out.println("longitud "+ clave.length());
            clave = Base64.getEncoder().encodeToString(sha256.digest());
            System.out.println("clave string b64 " + clave);
            System.out.println("longitud " + clave.length());
        } catch (Exception ex) {
            System.out.println("Error al encriptar");
        }
        return clave;
    }

    public String cifrarAES_CBC(String clave) {
        String textoCifrado = "";
        Cipher objAES;
        try {
            objAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec KeyIni = new SecretKeySpec(this.ClavePriv.getBytes("UTF-8"), "AES");
            IvParameterSpec ivp = new IvParameterSpec(this.VecInicial.getBytes("UTF-8"));
            //incializar el obj chiper objAES en modo cifrado
            objAES.init(Cipher.ENCRYPT_MODE, KeyIni, ivp);
            byte[] byteCifrado = objAES.doFinal(clave.getBytes("UTF-8"));
            textoCifrado = Base64.getEncoder().encodeToString(byteCifrado);
            System.out.println("Cifrado AES: " + textoCifrado);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return textoCifrado;
    }

    public List<Detalle_Pedido> verDetaPedidoByPe(Pedido index) {
        DetallePedidoDAO obj = new DetallePedidoDAO();
        List<Detalle_Pedido> pedi;
        pedi = obj.readByPe(index);
        return pedi;
    }

    public Detalle_Pedido verDetaPedido(Detalle_Pedido index) {
        DetallePedidoDAO obj = new DetallePedidoDAO();
        Detalle_Pedido pedi = new Detalle_Pedido();
        pedi = obj.read(index);
        return pedi;
    }

    public boolean UpdateDetalle(Detalle_Pedido index) {
        DetallePedidoDAO obj = new DetallePedidoDAO();
        return obj.update(index);
    }

    public boolean eliminarDetalle(Detalle_Pedido index) {
        DetallePedidoDAO obj = new DetallePedidoDAO();
        return obj.Delate(index);
    }
}
