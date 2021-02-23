/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.EAgenteRetenedores;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface IAgenteRetenedores {
    
    public boolean InsertarAgente (EAgenteRetenedores tipo);
    public boolean ActualizarAgente(EAgenteRetenedores tipo);
    public ArrayList<EAgenteRetenedores> ListaAgente();
    public boolean Eliminar();
    public int VerificarAgente (String ruc);
    public ArrayList<EAgenteRetenedores> ListaFiltro(String letras);
    public int VerificarConsolidado(int codprog, String docprov);
}
