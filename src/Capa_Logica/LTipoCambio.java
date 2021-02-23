/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDTipoCambio;
import Capa_Entidades.ETipoCambio;
import Capa_Logica.Interfaz.ITipoCambio;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LTipoCambio implements ITipoCambio{
    
    
    BDTipoCambio bdtc=new BDTipoCambio(SesionUsuario.bddatos.getNombd());
    
    @Override
    public boolean InsertarTipoCambio(ETipoCambio tipo) {
        return bdtc.InsertarTipoCambio(tipo);
    }

    @Override
    public boolean ActualizarTipoCambio(ETipoCambio tipo) {
        return bdtc.ActualizarTipoCambio(tipo);
    }

    @Override
    public ArrayList<ETipoCambio> ListaTipoCambio() {
        return bdtc.ListaTipoCambio();
    }

    @Override
    public Double RecuperarTipoCambio(String fecha) {
        return bdtc.RecuperarTipoCambio(fecha);
    }
}
