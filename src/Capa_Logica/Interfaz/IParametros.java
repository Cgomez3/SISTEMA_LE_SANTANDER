/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.EParametro;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface IParametros {
    
    public ArrayList<EParametro> ListaParametros();
    public boolean ActualizarParametro(EParametro objpro);
    public ArrayList<EParametro> ListaBancoPrincipal();
    public ArrayList<EParametro> ListaCuentasPrincipal();
    public int ConsultarCorrelativo();
    public boolean ActualizarCorrelativo(int nuevo);
}
