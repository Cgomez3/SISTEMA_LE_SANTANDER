/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.EDetraccTxt;
import Capa_Entidades.EDetraccion;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface IDetraccion {
    public boolean InsertarDetraccion (EDetraccion tipo);
    public boolean ActualizarDetraccion(EDetraccion tipo);
    public ArrayList<EDetraccion> ListaDetraccion();
    public ArrayList<EDetraccTxt> ListarDetracciones(int codigo);
}
