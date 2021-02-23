/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDBanco;
import Capa_Conexion.BDMoneda;
import Capa_Entidades.EMoneda;
import Capa_Logica.Interfaz.IMoneda;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LMoneda implements IMoneda{
    
        BDMoneda bdmon=new BDMoneda(SesionUsuario.bddatos.getNombd());
    


    @Override
    public ArrayList<EMoneda> ListaMoneda() {
       return bdmon.ListaMoneda();
    }
}
