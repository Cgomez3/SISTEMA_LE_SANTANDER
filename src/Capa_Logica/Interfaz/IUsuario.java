/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.EUsuario;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface IUsuario {
    public EUsuario Login(String usuario, String contra);
    public ArrayList<EUsuario> ListaUsuario();
    public boolean InsertarUsuario(EUsuario objusu);
    public boolean ActualizarUsuario(EUsuario objusu);
    public boolean CambioContra(String dni, String contraseña);
    public ArrayList<EUsuario> prueba1();
}
