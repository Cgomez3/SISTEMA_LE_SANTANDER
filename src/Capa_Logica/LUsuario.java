/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDUsuario;
import Capa_Entidades.EUsuario;
import Capa_Logica.Interfaz.IUsuario;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LUsuario implements IUsuario{

    BDUsuario bdu=new BDUsuario(SesionUsuario.bddatos.getNombd());
    
    @Override
    public EUsuario Login(String usuario, String contra) {
        return bdu.Login(usuario, contra);
    }

    @Override
    public ArrayList<EUsuario> ListaUsuario() {
        return bdu.ListaUsuario();
    }

    @Override
    public boolean InsertarUsuario(EUsuario objusu) {
        return bdu.InsertarUsuario(objusu);
    }

    @Override
    public boolean ActualizarUsuario(EUsuario objusu) {
        return bdu.ActualizarUsuario(objusu);
    }

    @Override
    public boolean CambioContra(String dni, String contraseña) {
        return bdu.CambioContra(dni, contraseña);
    }
   
    @Override
    public ArrayList<EUsuario> prueba1() {
        return bdu.prueba1();
    }
    

}
