/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDBanco;
import Capa_Entidades.EBanco;
import Capa_Logica.Interfaz.IBanco;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LBanco implements IBanco{
    
    
    BDBanco bdban=new BDBanco(SesionUsuario.bddatos.getNombd());

    @Override
    public ArrayList<EBanco> ListaBanco() {
       return bdban.ListaBanco();
    }
    
}
