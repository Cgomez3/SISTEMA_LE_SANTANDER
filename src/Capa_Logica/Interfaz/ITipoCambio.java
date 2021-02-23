/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.ETipoCambio;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface ITipoCambio {
    
      public boolean InsertarTipoCambio (ETipoCambio tipo);
    public boolean ActualizarTipoCambio(ETipoCambio tipo);
    public ArrayList<ETipoCambio> ListaTipoCambio();
    public Double RecuperarTipoCambio(String fecha);
    
}
