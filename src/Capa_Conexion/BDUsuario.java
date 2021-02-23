/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EUsuario;
import Capa_Logica.Interfaz.IUsuario;
import Capa_Logica.SesionUsuario;
import java.sql.CallableStatement;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDUsuario extends ConexionBD implements IUsuario {

    public BDUsuario(String bd) {
        super(bd);
    }

    @Override
    public EUsuario Login(String usuario, String contra) {    
        String proclcarea = "{call sp_Logeo(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclcarea);
            cs.setString(1, usuario);
            cs.setString(2, contra);
            rs = cs.executeQuery();
            EUsuario objusu=new EUsuario();
            objusu.setUsuario(usuario);
            objusu.setContra(contra);
            objusu.setImportar("NADA");
            while (rs.next()) {
                objusu.setDNI(rs.getString(1));
                objusu.setNombres(rs.getString(2));
                objusu.setApePaterno(rs.getString(3));
                objusu.setApeMaterno(rs.getString(4));
                objusu.setImportar(rs.getString(5));
                objusu.setExportar(rs.getString(6));
                objusu.setSeguridad(rs.getString(7));
            }
            return objusu;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<EUsuario> ListaUsuario() {
        ArrayList<EUsuario> lstusuario=new ArrayList<>();
        String proclcarea = "{call sp_ListarUsuarios()}";
        try {
            CallableStatement cs = cn.prepareCall(proclcarea);
            rs = cs.executeQuery();
            while (rs.next()) {
                EUsuario objusu=new EUsuario();
                objusu.setDNI(rs.getString(1));
                objusu.setNombres(rs.getString(2));
                objusu.setApePaterno(rs.getString(3));
                objusu.setApeMaterno(rs.getString(4));
                objusu.setUsuario(rs.getString(5));
                objusu.setContra(rs.getString(6));
                objusu.setEmail(rs.getString(7));
                objusu.setEstado(rs.getString(8));
                objusu.setImportar(rs.getString(9));
                objusu.setExportar(rs.getString(10));
                objusu.setSeguridad(rs.getString(11));
                lstusuario.add(objusu);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstusuario;
    }

    @Override
    public boolean InsertarUsuario(EUsuario objusu) {
//@dni char(8), @nombres varchar(70), @apepat varchar(50), @apemat varchar(50), @usu varchar(40),
//@clave varchar(50), @email varchar(70), @estado varchar(15), @libroelec char(2), @progpago char(2),
//@seg char(2), @usucrea varchar(40)
        String procInsertUsu = "{call sp_InsertarUsuario(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setString(1, objusu.getDNI());
            cs.setString(2, objusu.getNombres());
            cs.setString(3, objusu.getApePaterno());
            cs.setString(4, objusu.getApeMaterno());
            cs.setString(5, objusu.getUsuario());
            cs.setString(6, objusu.getContra());
            cs.setString(7, objusu.getEmail());
            cs.setString(8, objusu.getEstado());
            cs.setString(9, objusu.getImportar());
            cs.setString(10, objusu.getExportar());
            cs.setString(11, objusu.getSeguridad());
            cs.setString(12, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e+ " : "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean ActualizarUsuario(EUsuario objusu) {
        String procInsertUsu = "{call sp_ActualizarUsuario(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setString(1, objusu.getDNI());
            cs.setString(2, objusu.getNombres());
            cs.setString(3, objusu.getApePaterno());
            cs.setString(4, objusu.getApeMaterno());
            cs.setString(5, objusu.getUsuario());
            cs.setString(6, objusu.getContra());
            cs.setString(7, objusu.getEmail());
            cs.setString(8, objusu.getEstado());
            cs.setString(9, objusu.getImportar());
            cs.setString(10, objusu.getExportar());
            cs.setString(11, objusu.getSeguridad());
            cs.setString(12, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e+ " : "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean CambioContra(String dni, String contraseña) {
        String procInsertUsu = "{call sp_ActualizarContraseña(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setString(1, dni);
            cs.setString(2, contraseña);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e+ " : "+e.getMessage());
            return false;
        }
    }
    

   
    public ArrayList<EUsuario> prueba1() {
        ArrayList<EUsuario> usu123=new ArrayList<>();
        String p = "{call prueba1()}";
        try {
            CallableStatement cs = cn.prepareCall(p);
            rs = cs.executeQuery();
            while (rs.next()) {
                EUsuario objusu=new EUsuario();
                objusu.setDNI(rs.getString(1));
                objusu.setNombres(rs.getString(2));
                objusu.setApePaterno(rs.getString(3));
                objusu.setApeMaterno(rs.getString(4));
                objusu.setUsuario(rs.getString(5));
                objusu.setContra(rs.getString(6));
                objusu.setEmail(rs.getString(7));
                objusu.setEstado(rs.getString(8));
                objusu.setImportar(rs.getString(9));
                objusu.setFormatear(rs.getString(10));
                objusu.setExportar(rs.getString(11));
                objusu.setConsulta(rs.getString(12));
                objusu.setSeguridad(rs.getString(13));
                usu123.add(objusu);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return usu123;
    }
    
    
}
