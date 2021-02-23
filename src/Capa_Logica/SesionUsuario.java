/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Entidades.EConexion;
import Capa_Entidades.EUsuario;
import Capa_Panel.Consulta.Reportes.JDCargaReportes;
import java.awt.Frame;
import java.sql.Connection;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 *
 * @author User
 */

public class SesionUsuario {
    
    private SesionUsuario() {
    }

    public static String nuevopass;
    public static Connection con;
    public static EConexion bddatos;
    public static EUsuario misesion;
    public static int nroaregistrar;
    public static Frame fr;
    public static JTextField txtrucpro;
    public static JTextField txtrazonsocial;
    public static JLabel lblTC;
    public static JTextField txtigv;
    public static JTextField txtcomprobante;
    public static JTextField txtrazonref;
    public static JTextField txtfecharef;
    public static JRadioButton rbtretencion;
    public static JRadioButton rbtdetraccion;
    public static JRadioButton rbtninguno;
    public static JDesktopPane dpane;
    
    public static SesionUsuario getInstance() {
        return SesionUsuarioHolder.INSTANCE;
    }

   public static String llamarBarra(Frame fa, Boolean root){
        JDCargaReportes jdcr=new JDCargaReportes(fa, root);
        jdcr.setVisible(true);
        return "listo";
    }
    
     /*
     * @return the sesionusuario
     */
   
    private static class SesionUsuarioHolder {

        private static final SesionUsuario INSTANCE = new SesionUsuario();
    }
    
}
