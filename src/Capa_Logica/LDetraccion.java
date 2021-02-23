/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDDetraccion;
import Capa_Entidades.EDetraccTxt;
import Capa_Entidades.EDetraccion;
import Capa_Logica.Interfaz.IDetraccion;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LDetraccion implements IDetraccion{
    
      BDDetraccion bddetra=new BDDetraccion(SesionUsuario.bddatos.getNombd());

    @Override
    public boolean InsertarDetraccion(EDetraccion tipo) {
        return bddetra.InsertarDetraccion(tipo);
    }

    @Override
    public boolean ActualizarDetraccion(EDetraccion tipo) {
        return bddetra.ActualizarDetraccion(tipo);
    }

    @Override
    public ArrayList<EDetraccion> ListaDetraccion() {
        return bddetra.ListaDetraccion();
    }

    @Override
    public ArrayList<EDetraccTxt> ListarDetracciones(int codigo) {
        return bddetra.ListarDetracciones(codigo);
    }
    
}
