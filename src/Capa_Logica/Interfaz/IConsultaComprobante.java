/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.EComprobante;
import Capa_Entidades.EEstado;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface IConsultaComprobante {
    
            public ArrayList<EEstado> ListaEstadoCompro();
            public ArrayList<EComprobante> ListaxEstado(String estado);
            public ArrayList<EComprobante> ListaxRazonSocial(String razonsocial);
            public ArrayList<EComprobante> ListaxFecha(String inicio, String fin); 
            public ArrayList<EComprobante> ListaxRazonyForma(String razon, String forma);
            public ArrayList<EComprobante> ListaxFechayForma(String inicio,String fin, String forma);
            
    
}
