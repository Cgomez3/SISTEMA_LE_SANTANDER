/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;


import Capa_Entidades.EConexion;
import Capa_Logica.SesionUsuario;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;
 
/**
 *
 * @author Ronald
 */
public class ConexionBD {
    protected String bd="";
    protected Connection cn;
    protected Statement st;
    protected String sql;
    protected ResultSet rs;

    public ConexionBD(String bd) {
        leer_ini();
        this.bd=bd;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Registrado");
            cn= DriverManager.getConnection("jdbc:sqlserver://"+SesionUsuario.bddatos.getIp()+";databasename=" + bd ,SesionUsuario.bddatos.getUsuariobd(),SesionUsuario.bddatos.getPassbd());
            SesionUsuario.con=cn;
            System.out.println("Conexion Establecida");
            st=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Curso establecido");
        } catch (SQLException e) {
            System.out.println("ERROR SQL:"+e.getMessage());
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error de Conexión de BD.");
        }catch(ClassNotFoundException e1){
            System.out.println("ERROR DRIVER:"+e1.getMessage());
        }
    }

    public void leer_ini() {
        try {
//            String Database ="BDLESANTANDER";
//            String IPLocal ="CRISTHIAN-PC\\MSSQLSERVER2012";
//            String Usuario ="sa";
//            String Pass ="SQL2012";
            
            Properties p = new Properties();
            p.load(new FileInputStream("C:\\Sistema Libros Electronicos\\Conexion.ini"));
            EConexion datosconexion = new EConexion();
            datosconexion.setNombd(p.getProperty("Database"));
            datosconexion.setIp(p.getProperty("IPLocal"));
            datosconexion.setUsuariobd(p.getProperty("Usuario"));
            datosconexion.setPassbd(p.getProperty("Pass"));
            datosconexion.setRuta(p.getProperty("Ruta"));
            SesionUsuario.bddatos = datosconexion;
            
//            Properties p = new Properties();
//            p.load(new FileInputStream("C:\\Sistema Libros Electronicos\\Conexion.ini"));
//            EConexion datosconexion = new EConexion();
//            datosconexion.setNombd(Database);
//            datosconexion.setIp(IPLocal);
//            datosconexion.setUsuariobd(Usuario);
//            datosconexion.setPassbd(Pass);
//            SesionUsuario.bddatos = datosconexion;
        } catch (Exception e) {
            System.out.println("Error al leer el archivo .ini:" + e.getMessage());
        }
    }
    
    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
}
